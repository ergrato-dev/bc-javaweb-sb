# Flyway — Migraciones de Base de Datos

## 🎯 Objetivos
- Gestionar el schema de BD con migraciones versionadas
- Entender el ciclo de vida de Flyway
- Crear seeds de datos con Flyway

---

## 1. ¿Por qué Flyway?

`ddl-auto: create-drop` destruye datos al reiniciar → **inaceptable en producción**.

Flyway soluciona esto con **migraciones SQL versionadas**:
- Un script SQL por cada cambio en el schema
- Las migraciones corren una sola vez
- Flyway registra el historial en la tabla `flyway_schema_history`

---

## 2. Configuración

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
<!-- Para PostgreSQL 15+ / Flyway 10+ -->
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-database-postgresql</artifactId>
</dependency>
```

```yaml
# application.yml
spring:
  jpa:
    hibernate:
      ddl-auto: validate     # Flyway gestiona el schema; JPA solo valida
  flyway:
    enabled: true
    locations: classpath:db/migration   # directorio de scripts
```

---

## 3. Nomenclatura de Scripts

```
src/main/resources/db/migration/
├── V1__create_tables.sql          # V{version}__{descripcion}.sql
├── V2__add_appointment_table.sql  # Doble guion bajo obligatorio
├── V3__add_indexes.sql
└── R__seed_data.sql               # R = Repeatable (corre cuando cambia)
```

> Formato obligatorio: `V` + número + `__` + descripción + `.sql`

---

## 4. Scripts de Migración

```sql
-- V1__create_tables.sql
CREATE TABLE doctors (
    id          BIGSERIAL       PRIMARY KEY,
    name        VARCHAR(100)    NOT NULL,
    specialty   VARCHAR(50)     NOT NULL,
    email       VARCHAR(100)    NOT NULL UNIQUE,
    created_at  TIMESTAMP       NOT NULL DEFAULT NOW()
);

CREATE TABLE patients (
    id          BIGSERIAL       PRIMARY KEY,
    first_name  VARCHAR(50)     NOT NULL,
    last_name   VARCHAR(50)     NOT NULL,
    email       VARCHAR(100)    UNIQUE,
    birth_date  DATE            NOT NULL,
    created_at  TIMESTAMP       NOT NULL DEFAULT NOW()
);
```

```sql
-- V2__create_appointments.sql
CREATE TABLE appointments (
    id           BIGSERIAL   PRIMARY KEY,
    doctor_id    BIGINT      NOT NULL REFERENCES doctors(id),
    patient_id   BIGINT      NOT NULL REFERENCES patients(id),
    scheduled_at TIMESTAMP   NOT NULL,
    status       VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED',
    notes        TEXT,
    created_at   TIMESTAMP   NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_appointments_doctor  ON appointments(doctor_id);
CREATE INDEX idx_appointments_patient ON appointments(patient_id);
CREATE INDEX idx_appointments_date    ON appointments(scheduled_at);
```

---

## 5. Migraciones en H2 (Desarrollo)

H2 soporta SQL estándar pero no toda la sintaxis de PostgreSQL. Usar H2 Compatibility Mode:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:devdb;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE
```

O mejor: crear scripts específicos por entorno:
```yaml
spring:
  flyway:
    locations:
      - classpath:db/migration          # scripts compartidos
      - classpath:db/migration-h2       # scripts específicos para H2 dev
```

---

## ✅ Checklist de Verificación
- [ ] Dependencia `flyway-core` en pom.xml
- [ ] `ddl-auto: validate` en todos los entornos con Flyway
- [ ] Scripts nombrados `V{n}__{descripcion}.sql`
- [ ] Scripts versionados incremental — nunca modificar uno existente
- [ ] Tabla `flyway_schema_history` creada automáticamente por Flyway
