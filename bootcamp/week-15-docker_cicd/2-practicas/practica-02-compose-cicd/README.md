# Práctica 02 — Docker Compose y GitHub Actions CI/CD

## Objetivos

- Crear un `docker-compose.yml` que orqueste API + PostgreSQL
- Configurar un archivo `.env` para variables sensibles
- Crear un workflow de GitHub Actions que compile, teste y construya la imagen

## Instrucciones

### Paso 1: Crear docker-compose.yml

Descomenta la sección **PASO 1** del `starter/ComposePractice.md`.

Verifica que todos los servicios levantan:

```bash
docker compose up --build
# Esperar a que aparezca: Started XXXApplication in X seconds
```

### Paso 2: Configurar .env

Descomenta la sección **PASO 2** y crea el archivo `.env`:

```bash
cp .env.example .env
# Editar .env con valores de desarrollo
```

Verifica que no commiteas `.env`:
```bash
cat .gitignore | grep .env
# Debe aparecer .env
```

### Paso 3: Debug con Docker Compose

Descomenta la sección **PASO 3** y practica los comandos de debug:

```bash
# Conectar a la BD dentro del contenedor
docker compose exec db psql -U myapp_user -d myapp_db

# Ver variables de entorno del contenedor API
docker compose exec api env | grep SPRING
```

### Paso 4: Crear workflow de GitHub Actions

Descomenta la sección **PASO 4** y crea el archivo `.github/workflows/ci.yml`.

Haz un push y verifica que el workflow se ejecuta en GitHub → Actions.

## Verificación

```bash
# La API responde en el puerto 8080
curl http://localhost:8080/actuator/health
# {"status":"UP"}

# Swagger UI disponible
open http://localhost:8080/swagger-ui.html
```
