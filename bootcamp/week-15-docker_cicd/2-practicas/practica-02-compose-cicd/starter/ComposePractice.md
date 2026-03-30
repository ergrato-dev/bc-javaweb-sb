# Práctica Docker Compose y CI/CD — Guía Paso a Paso

---

## PASO 1: docker-compose.yml

Crea `docker-compose.yml` en la raíz del proyecto:

```yaml
services:
  api:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      SPRING_PROFILES_ACTIVE: prod
      DATABASE_URL: jdbc:postgresql://db:5432/${POSTGRES_DB}
      DB_USERNAME: ${POSTGRES_USER}
      DB_PASSWORD: ${POSTGRES_PASSWORD}
    depends_on:
      db:
        condition: service_healthy
    restart: unless-stopped
    networks:
      - app-network

  db:
    image: postgres:17-alpine
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U ${POSTGRES_USER} -d ${POSTGRES_DB}"]
      interval: 10s
      timeout: 5s
      retries: 5
    ports:
      - "5432:5432"
    networks:
      - app-network

volumes:
  postgres_data:

networks:
  app-network:
    driver: bridge
```

---

## PASO 2: Archivo .env.example y .env

Crea `.env.example` (se sube a git como plantilla):

```bash
# .env.example — copiar a .env y rellenar
POSTGRES_DB=myapp_db
POSTGRES_USER=myapp_user
POSTGRES_PASSWORD=change_me_in_production
```

Crea `.env` (NO se sube a git):

```bash
# .env — valores reales de desarrollo local
POSTGRES_DB=myapp_db
POSTGRES_USER=myapp_user
POSTGRES_PASSWORD=dev_password_123
```

Agrega a `.gitignore`:
```
.env
```

---

## PASO 3: Comandos de debug

```bash
# Ver estado de los servicios
docker compose ps

# Logs de la API en tiempo real
docker compose logs -f api

# Conectar a la base de datos
docker compose exec db psql -U myapp_user -d myapp_db

# Ejecutar comando en la API
docker compose exec api sh

# Rebuild y restart solo la API
docker compose up --build api

# Detener todo
docker compose down

# Detener y eliminar datos (reset completo)
docker compose down -v
```

---

## PASO 4: GitHub Actions workflow

Crea el directorio y archivo `.github/workflows/ci.yml`:

```yaml
name: CI — Build and Test

on:
  push:
    branches: [main]
  pull_request:
    branches: [main]

jobs:
  build-and-test:
    runs-on: ubuntu-latest

    steps:
      - name: Checkout code
        uses: actions/checkout@v4

      - name: Set up Java 21
        uses: actions/setup-java@v4
        with:
          java-version: '21'
          distribution: 'temurin'
          cache: maven

      - name: Build and test
        run: ./mvnw verify --no-transfer-progress

      - name: Upload test results
        uses: actions/upload-artifact@v4
        if: always()
        with:
          name: test-results
          path: target/surefire-reports/

  docker-build:
    needs: build-and-test
    runs-on: ubuntu-latest
    if: github.ref == 'refs/heads/main'

    steps:
      - uses: actions/checkout@v4

      - name: Set up Docker Buildx
        uses: docker/setup-buildx-action@v3

      - name: Build Docker image (smoke test)
        uses: docker/build-push-action@v5
        with:
          context: .
          push: false  # solo verificar que la imagen se construye correctamente
          cache-from: type=gha
          cache-to: type=gha,mode=max
```

**Para habilitar push a Docker Hub** (opcional, requiere secrets configurados):
- Ve a tu repositorio en GitHub
- Settings → Secrets and variables → Actions
- Agrega: `DOCKERHUB_USERNAME` y `DOCKERHUB_TOKEN`
- Cambia `push: false` a `push: true` y agrega `tags:`
