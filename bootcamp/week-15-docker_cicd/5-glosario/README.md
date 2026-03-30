# Glosario — Semana 15: Docker y CI/CD

## C

**CI (Continuous Integration)**
Práctica de integrar y verificar automáticamente el código cada vez que se hace un push. Incluye: compilar, ejecutar tests, revisar cobertura, construir la imagen Docker.

**CD (Continuous Deployment)**
Extensión de CI que despliega automáticamente a producción si el pipeline de CI pasa. Elimina despliegues manuales y reduce el tiempo entre código y producción.

**Container (Contenedor)**
Unidad ligera y portátil que empaqueta una aplicación con todas sus dependencias (JVM, librerías). A diferencia de una VM, comparte el kernel del sistema operativo anfitrión.

---

## D

**`.dockerignore`**
Archivo que le dice a Docker qué archivos NO incluir en el contexto de build. Equivalente al `.gitignore`. Reduce el tamaño del contexto y acelera los builds.

**`Dockerfile`**
Archivo de texto con instrucciones para construir una imagen Docker. Cada instrucción (`FROM`, `COPY`, `RUN`, etc.) crea una capa en la imagen.

**`docker compose`**
Herramienta para definir y ejecutar múltiples contenedores Docker. El archivo `docker-compose.yml` describe los servicios, redes y volúmenes necesarios.

**`depends_on`**
Clave de `docker-compose.yml` que define el orden de inicio de servicios. Con `condition: service_healthy`, espera a que el servicio tenga un healthcheck exitoso antes de arrancar el servicio dependiente.

---

## E

**`.env`**
Archivo local con variables de entorno sensibles. NUNCA se sube a git. Se usa con `docker compose` para sustituir variables en el `docker-compose.yml`.

**`.env.example`**
Plantilla del `.env` con claves pero sin valores reales. Sí se sube a git para documentar qué variables son necesarias.

---

## G

**GitHub Actions**
Plataforma de CI/CD integrada en GitHub. Los workflows se definen en archivos YAML dentro de `.github/workflows/`. Se ejecutan automáticamente en eventos como `push` o `pull_request`.

**GitHub Secrets**
Variables sensibles (tokens, contraseñas) almacenadas de forma encriptada en GitHub. Se acceden en los workflows como `${{ secrets.NOMBRE }}` y nunca se exponen en los logs.

---

## H

**Healthcheck**
Comando que Docker ejecuta periódicamente dentro de un contenedor para verificar si el servicio está listo. Si falla repetidamente, Docker marca el contenedor como `unhealthy`.

---

## M

**Multi-stage build**
Técnica de Dockerfile que usa múltiples instrucciones `FROM`. La primera etapa compila la aplicación; la segunda copia solo los artefactos necesarios (el JAR) en una imagen mínima. Reduce el tamaño final hasta un 60%.

---

## P

**Perfil Spring (`spring.profiles.active`)**
Mecanismo para tener configuraciones diferentes por entorno. El perfil se activa con la variable `SPRING_PROFILES_ACTIVE=prod`. Permite usar H2 en tests y PostgreSQL en producción con el mismo código.

---

## V

**Volume (Volumen Docker)**
Mecanismo para persistir datos fuera del ciclo de vida del contenedor. Los datos en `/var/lib/postgresql/data` se almacenan en un volumen nombrado y sobreviven a `docker compose down`.
