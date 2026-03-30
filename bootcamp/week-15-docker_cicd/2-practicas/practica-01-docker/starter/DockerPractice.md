# Práctica Docker — Guía Paso a Paso

Esta práctica se hace directamente con archivos Docker, no con código Java.
Sigue cada paso y descomenta (copia) el contenido indicado.

---

## PASO 1: Crear .dockerignore

Crea un archivo llamado `.dockerignore` en la raíz de tu proyecto Spring Boot con este contenido:

```
# .dockerignore
target/
.git/
.github/
*.md
.env
*.log
.mvn/wrapper/maven-wrapper.jar
```

**Por qué:** Docker envía todo el directorio como "contexto de build".
Sin `.dockerignore`, incluyendo `target/` que puede pesar 200MB+.

---

## PASO 2: Dockerfile básico (una sola etapa)

Crea un archivo `Dockerfile.basic` en la raíz del proyecto:

```dockerfile
# Dockerfile.basic — imagen básica (una sola etapa)
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Instalar Maven
RUN apk add --no-cache maven

# Copiar todo el proyecto
COPY . .

# Compilar
RUN mvn package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/*.jar"]
```

Construye y anota el tamaño:
```bash
docker build -t mi-api-basic -f Dockerfile.basic .
docker images mi-api-basic
# El SIZE será ~500MB-700MB
```

---

## PASO 3: Dockerfile multi-stage

Crea un `Dockerfile` (sin extensión) en la raíz del proyecto:

```dockerfile
# Dockerfile — multi-stage build

# ==========================================
# Etapa 1: BUILD
# ==========================================
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /build

# Cache de dependencias Maven (se regenera solo si pom.xml cambia)
COPY pom.xml .
COPY .mvn/ .mvn/
COPY mvnw .
RUN chmod +x mvnw && ./mvnw dependency:go-offline -q

COPY src/ src/
RUN ./mvnw package -DskipTests -q

# ==========================================
# Etapa 2: RUNTIME
# ==========================================
FROM eclipse-temurin:21-jre-alpine AS runtime

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-jar", "app.jar"]
```

Construye y compara:
```bash
docker build -t mi-api .
docker images
# mi-api debería ser ~200MB (60% más pequeña que mi-api-basic)
```

---

## PASO 4: Verificar usuario no-root

```bash
# El contenedor debe correr como "spring", no como "root"
docker run --rm mi-api whoami
# Output esperado: spring

# Verificar que no puede escribir fuera de /app
docker run --rm mi-api touch /etc/test 2>&1
# Output esperado: touch: /etc/test: Permission denied
```

---

## PASO 5: Ejecutar con variables de entorno

```bash
docker run -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=dev \
  mi-api

# Verificar que responde
curl http://localhost:8080/actuator/health
```
