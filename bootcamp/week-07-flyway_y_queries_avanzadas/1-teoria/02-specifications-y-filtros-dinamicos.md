# JPA Specifications y Filtros Dinámicos

## 🎯 Objetivos
- Construir queries dinámicas con JPA Specifications
- Evitar condicionales `if-else` anidados para filtros

---

## 1. El Problema de los Filtros Dinámicos

Sin Specifications:

```java
// ❌ Verboso y difícil de mantener
public List<Patient> search(String name, String specialty, LocalDate fromDate) {
    if (name != null && specialty != null && fromDate != null) {
        return repo.findByNameAndSpecialtyAndDateAfter(name, specialty, fromDate);
    } else if (name != null && specialty != null) {
        return repo.findByNameAndSpecialty(name, specialty);
    } else if (name != null) {
        return repo.findByName(name);
    }
    // ... combinaciones exponenciales
}
```

---

## 2. JPA Specifications

```java
// Repository debe extender JpaSpecificationExecutor
public interface AppointmentRepository
        extends JpaRepository<Appointment, Long>,
                JpaSpecificationExecutor<Appointment> {}
```

```java
// Clase con Specifications estáticas
public class AppointmentSpec {

    public static Specification<Appointment> hasDoctor(Long doctorId) {
        return (root, query, cb) ->
            doctorId == null ? null : cb.equal(root.get("doctor").get("id"), doctorId);
    }

    public static Specification<Appointment> hasStatus(AppointmentStatus status) {
        return (root, query, cb) ->
            status == null ? null : cb.equal(root.get("status"), status);
    }

    public static Specification<Appointment> scheduledAfter(LocalDateTime from) {
        return (root, query, cb) ->
            from == null ? null : cb.greaterThanOrEqualTo(root.get("scheduledAt"), from);
    }

    public static Specification<Appointment> scheduledBefore(LocalDateTime to) {
        return (root, query, cb) ->
            to == null ? null : cb.lessThanOrEqualTo(root.get("scheduledAt"), to);
    }
}
```

---

## 3. Combinar Specifications

```java
// Service — combina specs con and()
public Page<Appointment> search(AppointmentFilter filter, Pageable pageable) {
    var spec = Specification
            .where(AppointmentSpec.hasDoctor(filter.doctorId()))
            .and(AppointmentSpec.hasStatus(filter.status()))
            .and(AppointmentSpec.scheduledAfter(filter.fromDate()))
            .and(AppointmentSpec.scheduledBefore(filter.toDate()));

    return appointmentRepository.findAll(spec, pageable);
}

// DTO para el filtro
public record AppointmentFilter(
    Long doctorId,
    AppointmentStatus status,
    LocalDateTime fromDate,
    LocalDateTime toDate
) {}
```

---

## 4. Uso en Controller

```java
@GetMapping
public Page<AppointmentResponse> search(
        @RequestParam(required = false) Long doctorId,
        @RequestParam(required = false) AppointmentStatus status,
        @RequestParam(required = false) @DateTimeFormat(iso = DATE_TIME) LocalDateTime from,
        @RequestParam(required = false) @DateTimeFormat(iso = DATE_TIME) LocalDateTime to,
        Pageable pageable) {

    var filter = new AppointmentFilter(doctorId, status, from, to);
    return service.search(filter, pageable);
}
```

---

## ✅ Checklist de Verificación
- [ ] Repository extiende `JpaSpecificationExecutor<T>`
- [ ] Specifications retornan `null` cuando el parámetro es `null` (Specification ignora nulls)
- [ ] `Specification.where().and().and()...` para combinar
- [ ] `@DateTimeFormat(iso = DATE_TIME)` en `@RequestParam LocalDateTime`
