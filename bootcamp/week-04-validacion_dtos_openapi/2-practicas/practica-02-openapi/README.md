# Práctica 02 — SpringDoc OpenAPI y Swagger UI

## 🎯 Objetivo
Documentar un controller existente con anotaciones SpringDoc para generar Swagger UI automáticamente.

## ⏱️ Duración estimada: 40 minutos

---

## Paso 1: Revisar la API base

**Abre `starter/OpenApiApp.java`** — tiene un controller de `Book` completamente funcional pero sin documentación OpenAPI.

Arranca la aplicación e intenta abrir:
- `http://localhost:8080/swagger-ui.html`

SpringDoc ya genera algo básico con solo agregar la dependencia. El objetivo es mejorar esa documentación.

---

## Paso 2: Agregar @Tag al Controller

**Descomenta la sección `// STEP 2`** — agrega `@Tag` a nivel de clase.

Resultado: en Swagger UI aparecerá la sección "Books" con una descripción.

---

## Paso 3: Documentar el endpoint GET con @Operation

**Descomenta la sección `// STEP 3`** — agrega `@Operation` y `@ApiResponse` al método `getAll`.

---

## Paso 4: Documentar POST con validación y respuestas múltiples

**Descomenta la sección `// STEP 4`** — documenta el endpoint create con todos sus posibles códigos de respuesta.

---

## Paso 5: Personalizar el bean OpenAPI global

**Descomenta la sección `// STEP 5`** — agrega `@Bean OpenAPI` con título, versión y descripción.

Resultado: el header de Swagger UI mostrará "Bookstore API v1.0".

---

## ✅ Verificación Final
- [ ] Swagger UI accesible en `/swagger-ui.html`
- [ ] Sección "Books" visible con descripción
- [ ] GET `/api/books` documentado con respuesta 200
- [ ] POST `/api/books` documentado con respuestas 201, 400, 409
- [ ] Header de Swagger UI muestra "Bookstore API v1.0"
