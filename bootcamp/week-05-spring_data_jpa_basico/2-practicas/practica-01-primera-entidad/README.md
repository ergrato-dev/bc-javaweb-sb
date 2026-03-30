# Práctica 01 — Mi Primera Entidad JPA

## 🎯 Objetivo
Crear una entidad JPA, un repositorio y probar el CRUD con H2.

## ⏱️ Duración estimada: 45 minutos

---

## Paso 1: Revisar el proyecto

**Abre `starter/JpaStarter.java`** — tiene una aplicación Spring Boot con H2.

Arranca la app y accede a la consola H2:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:devdb`
- No hay tablas todavía.

---

## Paso 2: Crear la entidad @Entity

**Descomenta la sección `// STEP 2`** — agrega `@Entity`, `@Id`, `@GeneratedValue` a la clase `Product`.

Reinicia la app y verifica en H2 Console — debería aparecer la tabla `products`.

---

## Paso 3: Agregar Derived Query Methods

**Descomenta la sección `// STEP 3`** — agrega métodos `findByCategory` y `existsByName` al repository.

---

## Paso 4: Guardar datos con CommandLineRunner

**Descomenta la sección `// STEP 4`** — usa `productRepository.save()` para insertar 3 productos al arrancar.

Verifica en H2 Console: `SELECT * FROM products;`

---

## Paso 5: Consultar datos

**Descomenta la sección `// STEP 5`** — llama a `findByCategory("Electronics")` y muestra en consola.

---

## ✅ Verificación Final
- [ ] Tabla `products` visible en H2 Console
- [ ] 3 productos al arrancar la app
- [ ] `findByCategory` retorna solo los de esa categoría
- [ ] `existsByName("Laptop")` retorna `true`
