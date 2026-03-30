# Práctica 01 — Jakarta Bean Validation

## 🎯 Objetivo
Agregar validaciones a un endpoint POST y capturar errores con mensajes descriptivos.

## ⏱️ Duración estimada: 50 minutos

---

## Paso 1: Starter sin validación

**Abre `starter/ValidationApp.java`** — tiene un endpoint POST que acepta cualquier dato. El objetivo es agregar validaciones.

Primero, arranca la app y envía una petición sin datos:
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{}'
```
Actualmente acepta el objeto vacío → **incorrecto**.

---

## Paso 2: Agregar constraints al Request DTO

**Descomenta la sección `// STEP 2`** — agrega `@NotBlank`, `@Email`, `@Positive` al record.

---

## Paso 3: @Valid en el Controller

**Descomenta la sección `// STEP 3`** — agrega `@Valid` antes de `@RequestBody`.

Envía de nuevo:
```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{}'
```
Ahora debería retornar `400 Bad Request` pero con el body de error genérico de Spring.

---

## Paso 4: Handler para MethodArgumentNotValidException

**Descomenta la sección `// STEP 4`** — agrega el handler en `GlobalExceptionHandler`.

Envía de nuevo — respuesta esperada:
```json
{
  "status": 400,
  "error": "Validation Failed",
  "errors": {
    "name": "Name is required",
    "email": "Must be a valid email",
    "salary": "Salary must be positive"
  }
}
```

---

## Paso 5: Validar que datos correctos sí funcionan

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@company.com","salary":65000,"department":"Engineering"}'
```
Respuesta esperada: `201 Created`.

---

## ✅ Verificación Final
- [ ] `{}` → 400 con errores por campo
- [ ] Email inválido → 400 con mensaje de email
- [ ] Salary negativo → 400 con mensaje de salary
- [ ] Datos válidos → 201 Created
