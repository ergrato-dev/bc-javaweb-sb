![Bootcamp Java Web con Spring Boot](_assets/bootcamp-header.svg)

![License MIT](https://img.shields.io/badge/License-MIT-green.svg)
![16 Semanas](https://img.shields.io/badge/Duración-16%20Semanas-blue.svg)
![128 Horas](https://img.shields.io/badge/Horas-128h-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4%2B-brightgreen.svg)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

[English Version](README_EN.md)

---

## 📋 Descripción

Bootcamp intensivo de 16 semanas (~4 meses) enfocado en el dominio de Spring Boot y desarrollo **backend API RESTful** moderno con Java.
Diseñado para llevar a estudiantes con conocimientos previos de Java básico y OOP a **Desarrollador Backend Junior**,
con énfasis en código limpio, buenas prácticas, arquitectura y proyectos del mundo real.

> ⚠️ **Alcance:** Este bootcamp cubre **exclusivamente el backend** (API RESTful). El frontend (React) se aborda en un bootcamp separado.

> **Prerrequisito:** Java básico y POO en Java ✅

### 🗺️ Ecosistema de Bootcamps

Este bootcamp forma parte de una ruta de aprendizaje completa:

| Bootcamp | Tecnología | Descripción |
|----------|-----------|-------------|
| **Este bootcamp** | Spring Boot + Java | Backend API RESTful |
| [bc-react](https://github.com/ergrato-dev/bc-react) | React | Frontend SPA |
| **Proyecto Integrador** | Spring Boot + React + PostgreSQL | Full-stack con y sin Docker |

### 🎯 Objetivos

Al finalizar el bootcamp, los estudiantes serán capaces de:

- ✅ Dominar Java moderno (Streams, Lambdas, Records, Generics, Optional)
- ✅ Construir APIs RESTful completas con Spring Boot
- ✅ Implementar validación de datos con Jakarta Bean Validation
- ✅ Trabajar con bases de datos usando Spring Data JPA + Hibernate
- ✅ Implementar autenticación y autorización (JWT, OAuth2) con Spring Security
- ✅ Escribir tests automatizados con JUnit 5, Mockito y Testcontainers
- ✅ Documentar APIs automáticamente (OpenAPI/Swagger con SpringDoc)
- ✅ Desplegar aplicaciones con Docker y CI/CD con GitHub Actions
- ✅ Aplicar arquitectura en capas y arquitectura hexagonal
- ✅ Construir proyectos completos listos para producción

### 🚀 ¿Por qué Spring Boot?

> Spring Boot moderno desde el día 1 — Sin código legacy, solo las mejores prácticas actuales.

Spring Boot es el framework Java más adoptado en la industria empresarial. Este bootcamp se enfoca
en Spring Boot 3.x con Java 21+ y las características modernas del ecosistema Spring, construyendo
**APIs RESTful robustas y production-ready** — sin frontend, sin HTML, sin CSS.
Los estudiantes aprenden directamente las herramientas y técnicas que usarán en el mundo profesional.

---

## 🗓️ Estructura del Bootcamp

| Fase | Semanas | Horas | Contenido |
|------|---------|-------|-----------|
| Spring Core & Boot | 1–3 | 24h | Java moderno, IoC/DI, auto-config, REST MVC |
| Validación y Docs | 4 | 8h | Bean Validation, DTOs, MapStruct, OpenAPI |
| Persistencia JPA | 5–7 | 24h | JPA, Hibernate, Flyway, PostgreSQL |
| Arquitectura | 8–9 | 16h | Capas, Hexagonal, Patterns |
| Seguridad | 10–11 | 16h | Spring Security, JWT, OAuth2 |
| Testing | 12–13 | 16h | JUnit 5, Mockito, Testcontainers |
| Avanzado | 14 | 8h | Cache, Async, WebSocket |
| Producción | 15 | 8h | Docker, CI/CD, Deployment |
| Proyecto Final | 16 | 8h | API RESTful backend completa (producción) |
| **Total** | **16** | **128h** | |

---

## 📚 Contenido por Semana

Cada semana incluye:

```
bootcamp/week-XX-tema_principal/
├── README.md                 # Descripción y objetivos de la semana
├── rubrica-evaluacion.md     # Criterios de evaluación detallados
├── 0-assets/                 # Imágenes, diagramas y recursos visuales
├── 1-teoria/                 # Material teórico (archivos .md)
├── 2-practicas/              # Ejercicios guiados paso a paso
├── 3-proyecto/               # Proyecto semanal integrador
├── 4-recursos/               # Recursos adicionales
│   ├── ebooks-free/
│   ├── videografia/
│   └── webgrafia/
└── 5-glosario/               # Términos clave de la semana
```

### 🔑 Componentes Clave

- 📖 **Teoría:** Conceptos fundamentales con ejemplos del mundo real
- 💻 **Práctica:** Ejercicios progresivos y proyectos hands-on
- 📝 **Evaluación:** Evidencias de conocimiento, desempeño y producto
- 🎓 **Recursos:** Glosarios, referencias y material complementario

---

## 🛠️ Stack Tecnológico

| Tecnología | Versión | Rol |
|------------|---------|-----|
| Java | 21 LTS | Lenguaje principal |
| Spring Boot | 3.4+ | Framework web |
| Spring Data JPA | 3.4+ | ORM (repositorios) |
| Spring Security | 6.4+ | Autenticación y autorización |
| Hibernate | 6.6+ | Implementación JPA |
| PostgreSQL | 17+ | Base de datos producción |
| H2 | 2.x | Base de datos desarrollo/testing |
| Flyway | 10+ | Migraciones de BD |
| MapStruct | 1.6+ | Mapeo DTO |
| SpringDoc OpenAPI | 2.x | Documentación Swagger |
| JUnit 5 | 5.11+ | Testing unitario |
| Mockito | 5+ | Mocking |
| Testcontainers | 1.20+ | Tests de integración |
| Docker | 27+ | Containerización |
| Docker Compose | 2.32+ | Orquestación |
| Maven | 3.9+ | Build tool |

> **Entorno de desarrollo:** Docker + Docker Compose (❌ NO instalar Java/Maven localmente)

Documentación de API: OpenAPI/Swagger vía SpringDoc (acceso en `/swagger-ui.html`)

---

## 🚀 Inicio Rápido

### Prerrequisitos

- Docker y Docker Compose instalados
- Git para control de versiones
- VS Code (recomendado) con extensiones incluidas
- Navegador moderno (Chrome, Firefox, Edge)
- **Conocimientos previos:** Java básico y POO en Java ✅

### 1. Clonar el Repositorio

```bash
git clone https://github.com/ergrato-dev/bc-javaweb-sb.git
cd bc-javaweb-sb
```

### 2. Instalar Extensiones de VS Code

```bash
# Abrir en VS Code
code .

# Las extensiones recomendadas aparecerán automáticamente
# O ejecutar: Ctrl+Shift+P → "Extensions: Show Recommended Extensions"
```

### 3. Navegar a la Semana Actual

```bash
cd bootcamp/week-01-java_moderno_streams_y_records
```

### 4. Seguir las Instrucciones

Cada semana contiene un `README.md` con instrucciones detalladas.

---

## 📊 Metodología de Aprendizaje

### Estrategias Didácticas

- 🎯 **Aprendizaje Basado en Proyectos (ABP)**
- 🧩 **Práctica Deliberada**
- 🔄 **API Challenges**
- 👥 **Code Review entre pares**
- 🎮 **Live Coding**

### Distribución del Tiempo (8h/semana)

- Teoría: 2 horas
- Prácticas: 3.5 horas
- Proyecto: 2.5 horas

### Evaluación

Cada semana incluye tres tipos de evidencias:

1. **Conocimiento 🧠 (30%):** Cuestionarios y evaluaciones teóricas
2. **Desempeño 💪 (40%):** Ejercicios prácticos en clase
3. **Producto 📦 (30%):** Entregables evaluables (proyectos funcionales)

**Criterio de aprobación:** Mínimo 70% en cada tipo de evidencia

---

## 🤝 Contribuir

¡Las contribuciones son bienvenidas! Este es un proyecto educativo de código abierto.

### Cómo Contribuir

1. Lee la [Guía de Contribución](CONTRIBUTING.md)
2. Revisa el [Código de Conducta](CODE_OF_CONDUCT.md)
3. Fork del repositorio
4. Crea tu rama (`git checkout -b feature/nueva-funcionalidad`)
5. Commit con [Conventional Commits](https://www.conventionalcommits.org/) (`git commit -m 'feat: add new exercise'`)
6. Push a la rama (`git push origin feature/nueva-funcionalidad`)
7. Abre un Pull Request

### 📋 Áreas de Contribución

- ✨ Ejercicios adicionales
- 📚 Mejoras en documentación
- 🐛 Corrección de errores
- 🎨 Recursos visuales (diagramas SVG)
- 🌐 Traducciones
- 📹 Videos tutoriales

---

## 📞 Soporte

- 💬 **Discussions:** [GitHub Discussions](https://github.com/ergrato-dev/bc-javaweb-sb/discussions)
- 🐛 **Issues:** [GitHub Issues](https://github.com/ergrato-dev/bc-javaweb-sb/issues)

---

## ⚠️ Exención de Responsabilidad

Este repositorio es un recurso educativo creado con fines de aprendizaje. Al utilizarlo, aceptas los siguientes términos:

- **Solo fines educativos:** El contenido, los ejemplos de código y los proyectos están diseñados exclusivamente para la enseñanza y el aprendizaje. No constituyen asesoramiento profesional, legal ni de seguridad.
- **Sin garantías:** El material se proporciona "tal cual", sin garantías de ningún tipo, expresas o implícitas, incluyendo idoneidad para un propósito particular o ausencia de errores.
- **Código en producción:** Los ejemplos de código son ilustrativos. Antes de usarlos en entornos productivos, debes realizar revisiones de seguridad, rendimiento y adaptación a tu contexto específico.
- **Versiones de software:** Las versiones de librerías y herramientas mencionadas pueden quedar desactualizadas. Siempre consulta la documentación oficial más reciente.
- **Limitación de responsabilidad:** Los autores y contribuidores no se responsabilizan por pérdidas de datos, daños directos o indirectos, interrupciones de servicio ni cualquier otro perjuicio derivado del uso de este material.
- **Responsabilidad del estudiante:** Cada estudiante es responsable de sus propias implementaciones, entornos de desarrollo y decisiones técnicas.

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT — ver el archivo [LICENSE](LICENSE) para más detalles.

---

## 🏆 Agradecimientos

- [Spring](https://spring.io/) — Por crear el ecosistema más robusto de Java
- [Hibernate](https://hibernate.org/) — Por el ORM más potente de Java
- [Testcontainers](https://testcontainers.com/) — Por revolucionar los tests de integración
- [SpringDoc](https://springdoc.org/) — Por la integración OpenAPI con Spring Boot
- Comunidad Java y Spring — Por los recursos y ejemplos
- Todos los contribuidores

---

## 📚 Documentación Adicional

- [🤖 Instrucciones de Copilot](.github/copilot-instructions.md)
- [🤝 Guía de Contribución](CONTRIBUTING.md)
- [📜 Código de Conducta](CODE_OF_CONDUCT.md)
- [🔒 Política de Seguridad](SECURITY.md)

---

🎓 **Bootcamp Java Web con Spring Boot — Zero to Hero**
_De prerrequisitos a desarrollador backend en 4 meses_

[Comenzar Semana 1](bootcamp/week-01-java_moderno_streams_y_records) • [Ver Documentación](_docs) • [Reportar Issue](https://github.com/ergrato-dev/bc-javaweb-sb/issues) • [Contribuir](CONTRIBUTING.md)

_Hecho con ❤️ para la comunidad de desarrolladores_
