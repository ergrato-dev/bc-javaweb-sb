# Dockerfile y Multi-stage Build para Spring Boot

## ¿Por Qué Dockerizar una App Spring Boot?

Docker empaqueta la aplicación + su runtime (JVM) en una imagen portátil. El mismo contenedor corre en laptop, servidor CI y producción — **eliminando "funciona en mi máquina"**.

```
Sin Docker:
  Dev: Java 21 ✅  →  CI: Java 17 ❌  →  Prod: Java 11 ❌  (conflictos)

Con Docker:
  Dev: imagen ✅  →  CI: misma imagen ✅  →  Prod: misma imagen ✅
```

## Dockerfile Básico (Una Sola Etapa)

```dockerfile
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copiar el JAR generado por Maven
COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Problema:** La imagen contiene el JAR completo (con el compilador Maven incluido si construiste mal). Solución: multi-stage build.

## Multi-stage Build (Recomendado para Producción)

```dockerfile
# ==========================================
# Etapa 1: BUILD — compilar con Maven
# ==========================================
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /build

# Copiar primero solo el pom.xml para aprovechar el cache de capas Docker
# Si pom.xml no cambia, Maven no re-descarga dependencias
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .
RUN ./mvnw dependency:go-offline -q

# Ahora copiar el código fuente
COPY src/ src/

# Compilar y empaquetar (skip tests — los tests corren en CI por separado)
RUN ./mvnw package -DskipTests -q

# ==========================================
# Etapa 2: RUNTIME — imagen mínima solo con JRE
# ==========================================
FROM eclipse-temurin:21-jre-alpine AS runtime

# Crear usuario no-root (seguridad: nunca correr como root en producción)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

# Copiar solo el JAR — SIN el código fuente ni herramientas de build
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

# Opciones JVM para entornos con recursos limitados (contenedores)
ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-jar", "app.jar"]
```

**Ventajas del multi-stage:**
- La imagen final es ~200MB vs ~500MB de la imagen de build
- El código fuente y Maven NO están en la imagen de producción
- El usuario `spring` previene escalada de privilegios

## .dockerignore

```
# No copiar archivos innecesarios al contexto de build
target/
.git/
.github/
*.md
.env
```

## Comandos Esenciales

```bash
# Construir la imagen
docker build -t mi-api:latest .

# Construir con tag de versión
docker build -t mi-api:1.0.0 .

# Ejecutar el contenedor
docker run -p 8080:8080 mi-api:latest

# Con variables de entorno
docker run -p 8080:8080 \
  -e DATABASE_URL=jdbc:postgresql://host:5432/db \
  -e SPRING_PROFILES_ACTIVE=prod \
  mi-api:latest

# Ver logs
docker logs <container_id> -f

# Ver imágenes locales
docker images

# Limpiar imágenes no usadas
docker image prune
```

## Perfiles Spring por Entorno

```yaml
# application.yml — configuración base
spring:
  profiles:
    active: ${SPRING_PROFILES_ACTIVE:dev}

---
# application-dev.yml — H2 en memoria
spring:
  config:
    activate:
      on-profile: dev
  datasource:
    url: jdbc:h2:mem:devdb
  jpa:
    show-sql: true

---
# application-prod.yml — PostgreSQL real
spring:
  config:
    activate:
      on-profile: prod
  datasource:
    url: ${DATABASE_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  jpa:
    show-sql: false
```

## ✅ Checklist de Verificación

- [ ] `.dockerignore` existe y excluye `target/`, `.git/`, archivos sensibles
- [ ] Multi-stage build separando compilación de runtime
- [ ] Usuario no-root en la imagen final
- [ ] Variables de entorno para configuraciones sensibles (nunca hardcodeadas en la imagen)
- [ ] `EXPOSE 8080` documentado aunque no restringe puertos
- [ ] `--from=builder` copia solo el JAR final
