# Rúbrica de Evaluación — Semana 15: Docker y CI/CD

## Proyecto: Blog API — Dockerización y CI/CD

### 🧠 Conocimiento (30%)

| Criterio | Excelente | Satisfactorio | Insuficiente |
|----------|-----------|---------------|--------------|
| Multi-stage build | Explica por qué multi-stage reduce el tamaño y qué va en cada etapa | Sabe que hay dos etapas pero no puede explicar por qué | Copia el Dockerfile sin entender las instrucciones |
| Docker Compose | Explica `depends_on`, `healthcheck`, `volumes` y para qué sirve cada servicio | Levanta los servicios pero no explica las configuraciones | No puede levantar la aplicación sin asistencia |
| GitHub Actions | Puede describir el flujo CI (push → build → test → docker) y qué son los secrets | Crea el workflow pero no sabe para qué sirve cada job | No puede crear ni modificar el workflow |

### 💪 Desempeño (40%)

| Criterio | Puntos | Descripción |
|----------|--------|-------------|
| `Dockerfile` multi-stage correcto | 15 pts | Dos etapas (builder/runtime), usuario no-root, opciones JVM |
| `docker compose up --build` funciona | 15 pts | API y PostgreSQL levantan sin errores |
| `GET /actuator/health` responde 200 | 10 pts | La app está sana dentro del contenedor |

### 📦 Producto (30%)

| Criterio | Puntos | Descripción |
|----------|--------|-------------|
| `.github/workflows/ci.yml` pasa en GitHub | 15 pts | Workflow visible en GitHub Actions con status verde |
| `.dockerignore` correcto | 5 pts | Excluye `target/`, `.git/`, `.env` |
| `.env.example` completo | 5 pts | Todas las variables tienen clave y descripción |
| Imagen ≤ 300MB | 5 pts | `docker images blog-api` muestra tamaño razonable (multi-stage efectivo) |

---

## Escala de Calificación

| Puntos | Calificación |
|--------|-------------|
| 90–100 | Excelente — el estudiante puede dockerizar cualquier app Spring Boot |
| 80–89 | Muy bien — comprende el proceso con mínima asistencia |
| 70–79 | Aprobado — puede seguir instrucciones pero requiere apoyo en troubleshooting |
| < 70 | Insuficiente — revisar teoría de Docker y practicar con ejemplos simples primero |

---

## Checklist del Estudiante

- [ ] `Dockerfile` existe con dos etapas (builder y runtime)
- [ ] Usuario no-root (`spring`) en la imagen runtime
- [ ] `.dockerignore` excluye `target/`, `.git/`, `.env`
- [ ] `docker compose up --build` termina exitosamente
- [ ] `curl http://localhost:8080/actuator/health` retorna 200
- [ ] `.env.example` existe con variables documentadas
- [ ] `.env` está en `.gitignore` (NO subir al repositorio)
- [ ] `.github/workflows/ci.yml` existe y corre en GitHub Actions
- [ ] El workflow pasa (status verde en Actions tab)
