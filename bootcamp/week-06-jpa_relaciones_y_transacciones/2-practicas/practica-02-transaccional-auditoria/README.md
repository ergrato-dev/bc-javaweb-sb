# Práctica 02 — @Transactional y Auditoría

## 🎯 Objetivo
Entender el comportamiento transaccional y agregar timestamps de auditoría.

## ⏱️ Duración estimada: 40 minutos

---

## Paso 1: Explorar el comportamiento base

**Abre `starter/TransactionApp.java`** — tiene un service sin `@Transactional`.

Llama al endpoint `POST /api/posts` y luego `POST /api/posts/fail-test` — observa si los datos se guardan incluso cuando hay error.

---

## Paso 2: Agregar @Transactional

**Descomenta la sección `// STEP 2`** — agrega `@Transactional` al service.

Llama de nuevo al endpoint de fallo — los datos incompletos NO deberían persistir.

---

## Paso 3: @Transactional(readOnly = true) en lecturas

**Descomenta la sección `// STEP 3`** — cambia la clase al nivel read-only y los métodos de escritura con `@Transactional`.

---

## Paso 4: Habilitar Spring Data Auditing

**Descomenta la sección `// STEP 4`** — agrega `@EnableJpaAuditing` y los campos `@CreatedDate` / `@LastModifiedDate`.

Verifica en GET /api/posts que cada post tiene `createdAt` y `updatedAt`.

---

## ✅ Verificación Final
- [ ] Sin `@Transactional`: error a mitad guarda datos parciales
- [ ] Con `@Transactional`: rollback completo ante error
- [ ] `createdAt` se auto-asigna al crear
- [ ] `updatedAt` cambia al actualizar
