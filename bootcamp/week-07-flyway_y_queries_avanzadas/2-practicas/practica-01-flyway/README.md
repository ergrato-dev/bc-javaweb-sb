# Práctica 01 — Mi Primera Migración Flyway

## 🎯 Objetivo
Crear migraciones SQL para un schema de hospital y verificar con H2.

## ⏱️ Duración estimada: 40 minutos

---

## Paso 1: Ver la app sin Flyway

**Abre `starter/FlywayStarter.java`** — tiene `ddl-auto: create-drop`. Arranca y verifica la tabla en H2 Console.

---

## Paso 2: Agregar Flyway y cambiar a validate

**Descomenta la sección `// STEP 2`** — agrega la dependencia Flyway y cambia `ddl-auto: validate`.

Sin scripts de migración → Flyway falla. La entidad no tiene tabla.

---

## Paso 3: Crear V1__create_doctors_table.sql

**Descomenta la sección `// STEP 3`** — crea el archivo SQL en `resources/db/migration/`.

Reinicia — Flyway ejecuta el script y JPA valida el schema exitosamente.

---

## Paso 4: Agregar una columna con V2

**Descomenta la sección `// STEP 4`** — crea `V2__add_phone_column.sql`.

Verifica en H2: `SHOW COLUMNS FROM doctors;` — aparece la columna `phone`.

---

## Paso 5: Intentar modificar V1 (observar el error)

Intenta cambiar algo en V1 — Flyway rechaza con error de checksum.

Esto demuestra por qué las migraciones son inmutables.

---

## ✅ Verificación Final
- [ ] Tabla `flyway_schema_history` visible en H2 Console
- [ ] V1 y V2 con `success = true`
- [ ] Intentar modificar V1 genera error de Flyway
