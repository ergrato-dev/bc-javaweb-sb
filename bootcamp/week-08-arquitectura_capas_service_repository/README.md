# Semana 08 — Arquitectura en Capas Completa

> Organiza tu código como un profesional: Controller → Service → Repository,
> con DTOs en cada frontera y responsabilidades bien definidas.

---

## 🎯 Objetivos

- Implementar arquitectura de 3 capas de forma rigurosa
- Ubicar correctamente la lógica de negocio en la Service Layer
- Separar DTOs de entrada y salida en cada capa
- Mapear automáticamente con MapStruct avanzado (listas, nested objects)
- Construir una jerarquía de excepciones de negocio
- Aplicar el patrón llevando una feature completa de extremo a extremo

---

## 📚 Requisitos Previos

- Semanas 01–07: Todo el stack hasta JPA + Flyway ✅
- DTOs básicos y MapStruct (Semana 04) ✅

---

## 🗂️ Estructura

```
week-08-arquitectura_capas_service_repository/
├── 1-teoria/
│   ├── 01-layered-architecture.md
│   ├── 02-service-layer-y-business-logic.md
│   └── 03-mapstruct-avanzado-excepciones.md
├── 2-practicas/
│   ├── practica-01-refactor-controller-a-service/
│   ├── practica-02-dtos-completos-por-capa/
│   └── practica-03-exception-hierarchy/
├── 3-proyecto/
│   ├── README.md
│   └── starter/
└── 5-glosario/
    └── README.md
```

---

## 📝 Contenidos

### Teoría (2h)

| Archivo | Tema |
|---------|------|
| [01-layered-architecture.md](1-teoria/01-layered-architecture.md) | Diagrama de capas, reglas de dependencia, por qué no lógica en controllers |
| [02-service-layer-y-business-logic.md](1-teoria/02-service-layer-y-business-logic.md) | `@Service`, interfaces de servicio, reglas de negocio, validación semántica |
| [03-mapstruct-avanzado-excepciones.md](1-teoria/03-mapstruct-avanzado-excepciones.md) | MapStruct: listas, objetos anidados, `@AfterMapping`; jerarquía de excepciones |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-refactor-controller-a-service](2-practicas/practica-01-refactor-controller-a-service/) | Mover lógica del controller a un service con interfaz |
| [practica-02-dtos-completos-por-capa](2-practicas/practica-02-dtos-completos-por-capa/) | `CreateRequest` → `Entity` → `Response` con MapStruct |
| [practica-03-exception-hierarchy](2-practicas/practica-03-exception-hierarchy/) | `BusinessException` → `ResourceNotFoundException` → `@ControllerAdvice` |

### Proyecto (2.5h)

[📦 API de Blog Completa en Capas](3-proyecto/README.md) — `Post` + `Comment` + `Author`: arquitectura de 3 capas estricta, DTOs completos, exceptions de negocio, Flyway, paginación.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Arquitectura en capas | 45min |
| Teoría: Service Layer | 45min |
| Teoría: MapStruct avanzado + excepciones | 30min |
| Práctica 01: Refactor a service | 1.25h |
| Práctica 02: DTOs por capa | 1.25h |
| Práctica 03: Jerarquía de excepciones | 1h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Controllers sin lógica de negocio (solo orquestan llamadas a services)
- [ ] Services con interfaces (`UserService` + `UserServiceImpl`)
- [ ] DTOs diferenciados: `CreateRequest`, `UpdateRequest`, `Response`
- [ ] `@ControllerAdvice` manejando la jerarquía completa de excepciones
- [ ] MapStruct mapeando listas y objetos anidados automáticamente

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 07 — Migraciones y Queries Avanzadas](../week-07-migraciones_y_queries_avanzadas/README.md) |
| ➡️ Siguiente | [Semana 09 — Arquitectura Hexagonal](../week-09-arquitectura_hexagonal/README.md) |
