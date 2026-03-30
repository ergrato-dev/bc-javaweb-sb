# JPA — Queries JPQL Avanzadas y Optimización

## 🎯 Objetivos
- Escribir queries JPQL con agregaciones y subconsultas
- Entender @EntityGraph como alternativa a JOIN FETCH
- Identificar y resolver el problema N+1

---

## 1. Queries de Agregación

```java
// DTO para estadísticas
public record DoctorStats(String doctorName, long totalAppointments, long cancelledCount) {}

// Repository
@Query("""
    SELECT new com.bootcamp.dto.DoctorStats(
        d.name,
        COUNT(a),
        SUM(CASE WHEN a.status = 'CANCELLED' THEN 1 ELSE 0 END)
    )
    FROM Appointment a
    JOIN a.doctor d
    GROUP BY d.id, d.name
    ORDER BY COUNT(a) DESC
    """)
List<DoctorStats> findDoctorStats();
```

---

## 2. Subconsultas JPQL

```java
// Pacientes con más de N citas
@Query("SELECT DISTINCT p FROM Patient p WHERE " +
       "(SELECT COUNT(a) FROM Appointment a WHERE a.patient = p AND a.status = 'COMPLETED') >= :minCount")
List<Patient> findFrequentPatients(@Param("minCount") long minCount);
```

---

## 3. @EntityGraph — Alternativa a JOIN FETCH

```java
// En la entidad — define los graph
@Entity
@NamedEntityGraph(
    name = "Appointment.withDoctorAndPatient",
    attributeNodes = {
        @NamedAttributeNode("doctor"),
        @NamedAttributeNode("patient")
    }
)
public class Appointment { ... }

// En el repository — usa el graph
@EntityGraph("Appointment.withDoctorAndPatient")
List<Appointment> findByStatus(AppointmentStatus status);

// Alternativa inline sin @NamedEntityGraph
@EntityGraph(attributePaths = {"doctor", "patient"})
Optional<Appointment> findById(Long id);
```

> `@EntityGraph` es más limpio que `@Query` con `JOIN FETCH` para casos simples.

---

## 4. Text Blocks en @Query (Java 15+)

```java
// ✅ JPQL legible con Text Blocks
@Query("""
    SELECT a FROM Appointment a
    JOIN FETCH a.doctor d
    JOIN FETCH a.patient p
    WHERE a.scheduledAt BETWEEN :start AND :end
    ORDER BY a.scheduledAt ASC
    """)
List<Appointment> findByDateRange(
    @Param("start") LocalDateTime start,
    @Param("end") LocalDateTime end
);
```

---

## 5. Identificar N+1 con show-sql

```yaml
spring:
  jpa:
    show-sql: true
    properties:
      hibernate.format_sql: false   # false para contar queries de una pasada
```

Señales de N+1:
- `select ... from appointments where id=1`
- `select ... from appointments where id=2`
- `select ... from appointments where id=3`
- ... (misma query N veces con diferente ID)

---

## ✅ Checklist de Verificación
- [ ] Aggregation queries usan `GROUP BY` con `COUNT`, `SUM`, `AVG`
- [ ] `@EntityGraph` con `attributePaths` para carga eager opcional
- [ ] Text Blocks para JPQL multilinea
- [ ] `show-sql: true` en desarrollo para detectar N+1
- [ ] Solo una query SQL para listas con relaciones frecuentemente accedidas
