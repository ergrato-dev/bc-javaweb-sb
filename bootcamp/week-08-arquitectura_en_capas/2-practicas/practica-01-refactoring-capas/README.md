# Práctica 01 — Refactorizar Código Espagueti

## 🎯 Objetivo

Tomar código "espagueti" (todo mezclado en el controller) y separarlo correctamente en capas: Controller → Service → Repository → Domain.

## 📋 Código inicial (espagueti)

```java
// PROBLEMA: toda la lógica está en el controller
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private EntityManager em;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, String> body) {
        // lógica de negocio en el controller ❌
        if (body.get("title") == null || body.get("title").isBlank()) {
            return ResponseEntity.badRequest().body("Title is required");
        }
        // acceso directo a DB desde el controller ❌
        var q = em.createQuery("SELECT COUNT(t) FROM Task t WHERE t.title = :t").setParameter("t", body.get("title")).getSingleResult();
        if ((Long)q > 0) return ResponseEntity.status(409).body("Duplicate title");
        // entidad expuesta directamente en la API ❌
        Task t = new Task(body.get("title"), body.get("description"));
        em.persist(t);
        return ResponseEntity.ok(t);
    }
}
```

## 🔧 Pasos de refactoring

Abre `starter/LayersApp.java` y descomenta por secciones.

### Paso 1: Separar el dominio

El dominio (`Task`) debe ser solo una entidad JPA — sin lógica HTTP.

**Abre `starter/LayersApp.java`** y descomenta el bloque `PASO 1`.

### Paso 2: Crear el Repository

La capa de acceso a datos solo maneja la persistencia.

**Descomenta el bloque `PASO 2`**.

### Paso 3: Crear el Service

Toda la lógica de negocio (validación de duplicados, creación) va al Service.

**Descomenta el bloque `PASO 3`**.

### Paso 4: Crear el Controller limpio

El Controller solo recibe HTTP, valida con `@Valid` y delega al Service.

**Descomenta el bloque `PASO 4`**.

### Paso 5: Crear DTOs

Separar la API del dominio con Records.

**Descomenta el bloque `PASO 5`**.

## ✅ Verificación

1. El `TaskController` NO importa ningún Repository directamente
2. El `TaskService` NO importa ninguna clase HTTP (`ResponseEntity`, etc.)
3. El endpoint `POST /tasks` retorna `TaskResponse` (no la entidad `Task`)
4. Al enviar un título duplicado, la API retorna 409 Conflict
