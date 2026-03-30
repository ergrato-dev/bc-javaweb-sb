# Semana 01 — Java Moderno: Streams, Records y Optional

> Domina las características de Java 17–21 que usarás en todo el bootcamp:
> Lambdas, Streams API, Optional, Records, `var` y Generics modernos.

---

## 🎯 Objetivos

- Escribir código Java fluido con Lambdas y Streams API
- Eliminar null checks con `Optional`
- Crear DTOs inmutables con `Record`
- Usar `var` para inferencia de tipos local
- Aplicar switch expressions y pattern matching
- Dominar Generics y Collections Framework

---

## 📚 Requisitos Previos

- Java básico: tipos primitivos, operadores, control de flujo ✅
- POO en Java: clases, herencia, interfaces, polimorfismo ✅
- JDK 21 + Maven instalados via Docker ✅

---

## 🗂️ Estructura

```
week-01-java_moderno_streams_y_records/
├── 1-teoria/
│   ├── 01-lambdas-y-functional-interfaces.md
│   ├── 02-streams-api.md
│   ├── 03-optional.md
│   └── 04-records-var-switch.md
├── 2-practicas/
│   ├── practica-01-lambdas/
│   ├── practica-02-streams/
│   └── practica-03-records-optional/
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
| [01-lambdas-y-functional-interfaces.md](1-teoria/01-lambdas-y-functional-interfaces.md) | Lambdas, `Function`, `Predicate`, `Consumer`, `Supplier` |
| [02-streams-api.md](1-teoria/02-streams-api.md) | `stream()`, `filter()`, `map()`, `collect()`, `reduce()` |
| [03-optional.md](1-teoria/03-optional.md) | `Optional.of()`, `orElse()`, `orElseThrow()`, `map()`, `filter()` |
| [04-records-var-switch.md](1-teoria/04-records-var-switch.md) | `record`, `var`, switch expressions, pattern matching |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-lambdas](2-practicas/practica-01-lambdas/) | Refactorizar código imperativo a lambdas |
| [practica-02-streams](2-practicas/practica-02-streams/) | Pipelines de datos con Streams API |
| [practica-03-records-optional](2-practicas/practica-03-records-optional/) | Modelar datos con Records y manejar nulls con Optional |

### Proyecto (2.5h)

[📦 Sistema de Procesamiento de Productos](3-proyecto/README.md) — Aplicación de consola que procesa un catálogo de productos usando Streams, Records y Optional.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Lambdas + Streams | 1h |
| Teoría: Optional + Records | 1h |
| Práctica 01: Lambdas | 1h |
| Práctica 02: Streams | 1.5h |
| Práctica 03: Records + Optional | 1h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Prácticas completadas (código descomentado y funcionando)
- [ ] Proyecto `ProductCatalogApp` funcionando con Docker
- [ ] Sin uso de ciclos `for` donde un Stream sea más apropiado
- [ ] Sin uso de `null` donde un `Optional` sea más apropiado

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Prerrequisito | Java básico + POO en Java |
| ➡️ Siguiente | [Semana 02 — Spring Core: IoC y DI](../week-02-spring_core_ioc_di/README.md) |
