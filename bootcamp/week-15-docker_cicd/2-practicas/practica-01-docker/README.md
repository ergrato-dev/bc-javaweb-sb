# Práctica 01 — Crear Dockerfile Multi-stage

## Objetivos

- Escribir un `Dockerfile` de dos etapas para una app Spring Boot
- Construir y ejecutar la imagen localmente
- Comparar tamaño de imagen single-stage vs multi-stage
- Verificar que la app no corre como root

## Instrucciones

### Paso 1: Crear .dockerignore

Descomenta la sección **PASO 1** en `starter/DockerPractice.md`.

Crea el archivo `.dockerignore` para excluir archivos innecesarios del contexto de build. Sin este archivo, Docker copiaría `target/`, `.git/`, etc., haciendo el build más lento.

### Paso 2: Crear Dockerfile básico (una etapa)

Descomenta la sección **PASO 2**. Construye y mide el tamaño de la imagen:

```bash
docker build -t mi-api-basic -f Dockerfile.basic .
docker images mi-api-basic
```

### Paso 3: Crear Dockerfile multi-stage

Descomenta la sección **PASO 3**. Construye y compara el tamaño:

```bash
docker build -t mi-api .
docker images mi-api
# La imagen multi-stage debería ser ~40% más pequeña
```

### Paso 4: Verificar seguridad — usuario no-root

Descomenta la sección **PASO 4**. Verifica que el proceso no corre como root:

```bash
docker run --rm mi-api whoami
# Debe mostrar: spring (no root)
```

## Comandos de Referencia

```bash
# Construir imagen
docker build -t <nombre>:<tag> .

# Ejecutar contenedor
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=dev <nombre>

# Inspeccionar imagen (ver capas y metadatos)
docker inspect <nombre>

# Ver historial de capas y sus tamaños
docker history <nombre>
```
