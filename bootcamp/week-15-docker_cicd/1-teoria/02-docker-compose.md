# Docker Compose — Orquestar API + PostgreSQL + Redis

## ¿Qué es Docker Compose?

Docker Compose define múltiples contenedores (servicios) en un solo archivo `docker-compose.yml`. Con un solo comando levantas toda la infraestructura necesaria para el desarrollo local.

```bash
docker compose up --build   # construir imágenes y levantar todos los servicios
docker compose down         # detener y eliminar contenedores
docker compose down -v      # también eliminar volúmenes (DB data)
docker compose logs -f api  # ver logs en tiempo real del servicio "api"
```

## Estructura del Proyecto con Docker Compose

```
proyecto/
├── docker-compose.yml      ← orquestación para desarrollo
├── docker-compose.prod.yml ← variantes para producción
├── Dockerfile              ← imagen de la aplicación
├── .env.example            ← plantilla de variables (se comparte en git)
├── .env                    ← valores reales (en .gitignore)
└── src/
```

## docker-compose.yml Completo

```yaml
services:
  # ==========================================
  # API — la aplicación Spring Boot
  # ==========================================
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
      REDIS_HOST: redis
    depends_on:
      db:
        condition: service_healthy  # esperar a que PostgreSQL esté listo
    restart: unless-stopped
    networks:
      - app-network

  # ==========================================
  # PostgreSQL 17
  # ==========================================
  db:
    image: postgres:17-alpine
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    volumes:
      - postgres_data:/var/lib/postgresql/data  # persistencia de datos
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U ${POSTGRES_USER} -d ${POSTGRES_DB}"]
      interval: 10s
      timeout: 5s
      retries: 5
    ports:
      - "5432:5432"  # exponer para conectar con herramientas externas (DBeaver, etc.)
    networks:
      - app-network

  # ==========================================
  # Redis — para caché en producción
  # ==========================================
  redis:
    image: redis:7-alpine
    command: redis-server --appendonly yes
    volumes:
      - redis_data:/data
    ports:
      - "6379:6379"
    networks:
      - app-network

volumes:
  postgres_data:
  redis_data:

networks:
  app-network:
    driver: bridge
```

## .env.example (Plantilla — Compartir en Git)

```bash
# .env.example — copiar a .env y rellenar valores reales
POSTGRES_DB=myapp_db
POSTGRES_USER=myapp_user
POSTGRES_PASSWORD=change_this_in_production
```

## Variables de Entorno en Spring Boot

```yaml
# application-prod.yml
spring:
  datasource:
    url: ${DATABASE_URL}          # obligatorio — falla si no existe
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  data:
    redis:
      host: ${REDIS_HOST:localhost}  # valor por defecto si no está definida
      port: ${REDIS_PORT:6379}
```

## Comandos de Debug con Docker Compose

```bash
# Ejecutar comandos dentro del contenedor de la API
docker compose exec api sh

# Conectar a PostgreSQL directamente
docker compose exec db psql -U myapp_user -d myapp_db

# Ver el estado de los servicios
docker compose ps

# Reiniciar solo un servicio
docker compose restart api

# Rebuild solo la imagen de la API (sin tocar la BD)
docker compose up --build api
```

## ✅ Checklist de Verificación

- [ ] `.env` está en `.gitignore`
- [ ] `.env.example` con valores de ejemplo está en git
- [ ] `depends_on` con `service_healthy` para no arrancar antes que la BD
- [ ] `healthcheck` en el servicio de BD
- [ ] Volúmenes nombrados para persistir datos de PostgreSQL y Redis
- [ ] Red `app-network` bridge para comunicación entre contenedores
