# 🤝 Guía de Contribución

¡Gracias por tu interés en contribuir al **Bootcamp Java Web con Spring Boot — Zero to Hero**!
Este documento explica cómo puedes participar y ayudar a mejorar este recurso educativo.

---

## 📋 Tabla de Contenidos

- [Código de Conducta](#código-de-conducta)
- [¿Cómo puedo contribuir?](#cómo-puedo-contribuir)
- [Proceso de Contribución](#proceso-de-contribución)
- [Estándares de Código](#estándares-de-código)
- [Estándares de Documentación](#estándares-de-documentación)
- [Reportar Bugs](#reportar-bugs)
- [Sugerir Mejoras](#sugerir-mejoras)
- [Preguntas](#preguntas)

---

## Código de Conducta

Este proyecto se rige por el [Código de Conducta](CODE_OF_CONDUCT.md).
Al participar, se espera que lo respetes en todo momento.

---

## ¿Cómo puedo contribuir?

### ✨ Ejercicios adicionales
- Nuevos ejercicios guiados para semanas existentes
- Variantes de dificultad (básico / intermedio / avanzado)

### 📚 Mejoras en documentación
- Correcciones de errores tipográficos o conceptuales
- Mejoras en la claridad de explicaciones
- Traducciones (español ↔ inglés)

### 🐛 Corrección de errores
- Errores en el código de los ejercicios
- Configuraciones incorrectas de Maven/Docker
- Links rotos

### 🎨 Recursos visuales
- Diagramas SVG para conceptos de teoría
- Mejoras al banner o assets existentes

### 📹 Recursos adicionales
- Videografía, webgrafía y ebooks-free relevantes al tema

---

## Proceso de Contribución

### 1. Fork y Clone

```bash
# Fork desde GitHub, luego:
git clone https://github.com/TU_USUARIO/bc-javaweb-sb.git
cd bc-javaweb-sb
```

### 2. Crear una rama

```bash
# Usa nombres descriptivos con prefijo de tipo:
git checkout -b feat/week-05-ejercicio-jpql
git checkout -b fix/week-03-typo-controller
git checkout -b docs/week-08-mejora-readme
git checkout -b assets/week-02-diagrama-ioc
```

### 3. Realizar los cambios

- Sigue los [Estándares de Código](#estándares-de-código)
- Verifica que el código Java compile correctamente (via Docker)
- Respeta la estructura de carpetas del bootcamp

### 4. Commit

Usamos [Conventional Commits](https://www.conventionalcommits.org/es/):

```bash
# Ejemplos:
git commit -m "feat(week-05): add JPQL exercise for complex queries"
git commit -m "fix(week-03): correct @PathVariable annotation example"
git commit -m "docs(week-08): clarify hexagonal architecture diagram"
git commit -m "assets(week-02): add spring-ioc-lifecycle.svg diagram"
git commit -m "chore: update stack versions in README"
```

**Tipos permitidos:** `feat`, `fix`, `docs`, `assets`, `chore`, `refactor`, `test`

### 5. Push y Pull Request

```bash
git push origin feat/week-05-ejercicio-jpql
```

Luego abre un Pull Request en GitHub con:
- **Título claro** que describa el cambio
- **Descripción** explicando qué cambia y por qué
- **Referencia** al Issue si aplica (`Closes #123`)

---

## Estándares de Código

### Java 21+

- Usar Java moderno: Records, `var`, Switch expressions, Pattern matching
- Nomenclatura en inglés (clases, métodos, variables, paquetes)
- Comentarios educativos en español cuando expliquen conceptos
- Sin XML de configuración — solo anotaciones y Java config
- Sin referencias a `javax.*` — usar `jakarta.*` (Spring Boot 3.x)

```java
// ✅ CORRECTO
public record UserResponse(Long id, String email) {}

// ✅ CORRECTO — comentario educativo en español
// @Valid activa la validación de Jakarta Bean Validation en el request
@PostMapping
public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.create(request));
}
```

### Ejercicios (2-practicas/)

- Usar formato de **código comentado** (NO TODOs)
- El estudiante aprende descomentando, no implementando desde cero
- Ver ejemplos en [copilot-instructions.md](.github/copilot-instructions.md)

### Proyectos (3-proyecto/starter/)

- Usar formato de **TODOs** con instrucciones claras
- NUNCA incluir ni hacer commit de la carpeta `solution/`

---

## Estándares de Documentación

- Documentación en **español**
- Código y nomenclatura técnica en **inglés**
- Archivos de teoría: máximo **~200 líneas** por archivo
- Si el tema es extenso, dividir en múltiples archivos numerados
- Diagramas en **SVG** (tema dark, sin gradientes, verde Spring `#6DB33F`)
- Sin ASCII art

---

## Reportar Bugs

Usa [GitHub Issues](https://github.com/ergrato-dev/bc-javaweb-sb/issues) con la etiqueta `bug`.

Incluye:
1. **Semana y archivo** donde ocurre el error
2. **Descripción clara** del problema
3. **Pasos para reproducir**
4. **Comportamiento esperado vs actual**
5. **Versión de Java/Spring Boot** (si aplica)

---

## Sugerir Mejoras

Usa [GitHub Issues](https://github.com/ergrato-dev/bc-javaweb-sb/issues) con la etiqueta `enhancement`.

Incluye:
1. **Contexto** — ¿qué semana o sección afecta?
2. **Propuesta** — ¿qué cambio sugieres y por qué?
3. **Alternativas** consideradas

---

## Preguntas

- 💬 **Dudas generales:** [GitHub Discussions](https://github.com/ergrato-dev/bc-javaweb-sb/discussions)
- 🐛 **Bugs:** [GitHub Issues](https://github.com/ergrato-dev/bc-javaweb-sb/issues)

---

_Gracias por ayudar a construir un mejor recurso educativo para la comunidad Java. 🌱_
