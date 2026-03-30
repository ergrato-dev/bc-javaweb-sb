# GitHub Actions — CI/CD para Spring Boot

## ¿Qué es CI/CD?

| Término | Significado | Qué hace |
|---------|-------------|---------|
| **CI** | Continuous Integration | Verificar automáticamente que el código funciona al hacer push |
| **CD** | Continuous Deployment | Desplegar automáticamente a producción si CI pasa |

```
Push a GitHub
    ↓
GitHub Actions CI:
  1. Compilar con Maven
  2. Ejecutar tests (JUnit + Testcontainers)
  3. Verificar cobertura JaCoCo
  4. Construir imagen Docker
  5. Push a Docker Hub (si es rama main)
    ↓
Deploy automático en Railway / Fly.io / Render
```

## Pipeline CI — `.github/workflows/ci.yml`

```yaml
name: CI — Build and Test

# Trigger: ejecutar en push a main o en pull requests
on:
  push:
    branches: [main]
  pull_request:
    branches: [main]

jobs:
  build-and-test:
    runs-on: ubuntu-latest

    services:
      # PostgreSQL para tests de integración con Testcontainers
      # (Testcontainers levanta su propio contenedor, esto es alternativo)
      postgres:
        image: postgres:17-alpine
        env:
          POSTGRES_DB: testdb
          POSTGRES_USER: test
          POSTGRES_PASSWORD: test
        ports:
          - 5432:5432
        options: >-
          --health-cmd pg_isready
          --health-interval 10s
          --health-timeout 5s
          --health-retries 5

    steps:
      # 1. Checkout del código
      - name: Checkout code
        uses: actions/checkout@v4

      # 2. Configurar Java 21
      - name: Set up Java 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
          cache: maven  # ← cache de dependencias Maven entre runs

      # 3. Compilar y ejecutar tests
      - name: Build and test
        run: ./mvnw verify --no-transfer-progress
        # verify ejecuta: compile → test → integration-test → verify

      # 4. Publicar reporte de tests (visible en GitHub Actions UI)
      - name: Publish test results
        uses: dorny/test-reporter@v1
        if: always()  # publicar aunque los tests fallen
        with:
          name: JUnit Tests
          path: target/surefire-reports/*.xml
          reporter: java-junit

      # 5. Publicar reporte de cobertura JaCoCo
      - name: Upload JaCoCo coverage
        uses: codecov/codecov-action@v5
        with:
          files: target/site/jacoco/jacoco.xml
```

## Pipeline CD — Build y Push de Imagen Docker

```yaml
  # Este job solo corre en push a main (no en PRs)
  docker-build-push:
    needs: build-and-test  # esperar que los tests pasen
    runs-on: ubuntu-latest
    if: github.ref == 'refs/heads/main'

    steps:
      - uses: actions/checkout@v4

      - name: Log in to Docker Hub
        uses: docker/login-action@v3
        with:
          username: ${{ secrets.DOCKERHUB_USERNAME }}
          password: ${{ secrets.DOCKERHUB_TOKEN }}

      - name: Build and push Docker image
        uses: docker/build-push-action@v5
        with:
          context: .
          push: true
          tags: |
            ${{ secrets.DOCKERHUB_USERNAME }}/mi-api:latest
            ${{ secrets.DOCKERHUB_USERNAME }}/mi-api:${{ github.sha }}
          cache-from: type=gha   # cache de capas Docker en GitHub Actions
          cache-to: type=gha,mode=max
```

## GitHub Secrets — Variables Sensibles

En GitHub → Settings → Secrets and variables → Actions:

| Secret | Valor |
|--------|-------|
| `DOCKERHUB_USERNAME` | tu usuario de Docker Hub |
| `DOCKERHUB_TOKEN` | token generado en Docker Hub (no la contraseña) |
| `DATABASE_URL` | URL de la BD en producción |

```yaml
# Usar secrets en el workflow:
env:
  DATABASE_URL: ${{ secrets.DATABASE_URL }}
```

## Deploy a Railway (Ejemplo)

Railway detecta el `Dockerfile` y despliega automáticamente.

```yaml
  deploy:
    needs: docker-build-push
    runs-on: ubuntu-latest
    steps:
      - name: Deploy to Railway
        uses: bervProject/railway-deploy@main
        with:
          railway_token: ${{ secrets.RAILWAY_TOKEN }}
          service: mi-api
```

## ✅ Checklist de Verificación

- [ ] `.github/workflows/ci.yml` existe en el repositorio
- [ ] Los secrets están configurados en GitHub (no en el código)
- [ ] El workflow ejecuta `mvn verify` (no solo `test`) para incluir tests de integración
- [ ] Cache de Maven configurado con `cache: maven` para acelerar builds
- [ ] Los jobs de CD dependen del job de CI (`needs: build-and-test`)
- [ ] Docker Hub TOKEN usado (no contraseña) — los tokens son revocables
