# Semana 08 — Arquitectura en Capas

> Conecta todas las piezas: Controller → Service → Repository.
> Separa responsabilidades, aplica DTOs con MapStruct y construye
> una API production-ready con arquitectura en capas completa.

---

## 🎯 Objetivos

- Comprender la arquitectura en capas y las responsabilidades de cada capa
- Separar correctamente Controller, Service y Repository
- Aplicar el patrón Service Layer para encapsular lógica de negocio
- Implementar el Repository Pattern con Spring Data JPA
- Conectar las tres capas con un flujo completo de request → response
- Manejar transacciones en la capa de servicio

---

## 📚 Requisitos Previos

- Semanas 05–07: Spring Data JPA, relaciones, Flyway ✅
- Semana 04: DTOs, MapStruct, validación ✅
- Semana 03: `@RestController`, `ResponseEntity` ✅

---

## 🗂️ Estructura

```
week-08-arquitectura_en_capas/
├── 1-teoria/
│   ├── 01-arquitectura-en-capas.md
│   ├── 02-service-layer-y-repository-pattern.md
│   └── 03-integracion-capas-completa.md
├── 2-practicas/
│   ├── practica-01-refactoring-capas/
│   └── practica-02-service-layer/
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
| [01-arquitectura-en-capas.md](1-teoria/01-arquitectura-en-capas.md) | Capas, responsabilidades, separación de concerns, ventajas |
| [02-service-layer-y-repository-pattern.md](1-teoria/02-service-layer-y-repository-pattern.md) | Service Layer, Repository Pattern, lógica de negocio, transacciones |
| [03-integracion-capas-completa.md](1-teoria/03-integracion-capas-completa.md) | Flujo completo request → response, DTOs por capa, manejo de errores |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-refactoring-capas](2-practicas/practica-01-refactoring-capas/) | Refactorizar código monolítico a arquitectura en 3 capas |
| [practica-02-service-layer](2-practicas/practica-02-service-layer/) | Implementar Service Layer con lógica de negocio y transacciones |

### Proyecto (2.5h)

[📦 Orders API](3-proyecto/README.md) — API de gestión de órdenes e-commerce con arquitectura en capas completa: Controller → Service → Repository, DTOs con MapStruct, Flyway y Swagger.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Arquitectura en capas | 40min |
| Teoría: Service Layer + Repository Pattern | 40min |
| Teoría: Integración completa | 40min |
| Práctica 01: Refactoring a capas | 1.5h |
| Práctica 02: Service Layer | 2h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Proyecto con estructura de paquetes `controller/`, `service/`, `repository/`, `domain/`, `dto/`
- [ ] Ninguna lógica de negocio en el `@RestController`
- [ ] `@Transactional` en la capa de servicio (no en controller ni repository)
- [ ] DTOs separados por dirección: `*Request` (entrada) y `*Response` (salida)
- [ ] MapStruct mapeando automáticamente entre capas

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 07 — Flyway y Queries Avanzadas](../week-07-flyway_y_queries_avanzadas/README.md) |
| ➡️ Siguiente | [Semana 09 — Arquitectura Hexagonal](../week-09-arquitectura_hexagonal/README.md) |
