# Práctica 01 — Relaciones OneToMany y ManyToOne

## 🎯 Objetivo
Mapear la relación Author → Post (1:N) y Post → Comment (1:N).

## ⏱️ Duración estimada: 50 minutos

---

## Paso 1: Explorar el código base

**Abre `starter/RelationsApp.java`** — tiene las entidades `Author`, `Post` y `Comment` sin anotaciones de relación.

Arranca la app — actualmente Author, Post y Comment son tablas independientes.

---

## Paso 2: Mapear Author → Post (@OneToMany)

**Descomenta la sección `// STEP 2`** — agrega la relación `@OneToMany` en `Author` y `@ManyToOne` en `Post`.

Verifica en H2 Console que `posts` tiene la columna `author_id`.

---

## Paso 3: Mapear Post → Comment (cascada)

**Descomenta la sección `// STEP 3`** — agrega `@OneToMany(cascade = ALL, orphanRemoval = true)` en `Post`.

---

## Paso 4: Método helper addComment()

**Descomenta la sección `// STEP 4`** — usa el método `post.addComment()` para mantener la bidireccionalidad.

---

## Paso 5: JOIN FETCH para evitar N+1

**Descomenta la sección `// STEP 5`** — agrega la query `findAllWithAuthors()` en el repository.

Observa la diferencia de queries en los logs: antes N+1, ahora 1 sola query.

---

## ✅ Verificación Final
- [ ] `posts` tiene columna `author_id` (FK)
- [ ] `comments` tiene columna `post_id` (FK)
- [ ] Cascade: al eliminar un post, sus comments se eliminan
- [ ] JOIN FETCH: un solo SELECT para posts + authors
