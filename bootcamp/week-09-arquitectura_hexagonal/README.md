# Semana 09 — Arquitectura Hexagonal

> Escribe código de negocio que no depende de Spring.
> Ports & Adapters: dominio puro, infraestructura intercambiable.

---

## 🎯 Objetivos

- Entender los límites del dominio vs la infraestructura
- Definir puertos de entrada (`InputPort`) y de salida (`OutputPort`)
- Implementar adaptadores primarios (REST) y secundarios (JPA)
- Escribir casos de uso como servicios de aplicación puros
- Comparar arquitectura en capas vs hexagonal con criterios concretos
- Reestructurar el proyecto de la semana anterior a hexagonal

---

## 📚 Requisitos Previos

- Semana 08: Arquitectura en capas completa ✅
- Interfaces Java: implementación y polimorfismo ✅

---

## 🗂️ Estructura

```
week-09-arquitectura_hexagonal/
├── 1-teoria/
│   ├── 01-ports-and-adapters.md
│   ├── 02-domain-model-y-value-objects.md
│   └── 03-hexagonal-en-spring-boot.md
├── 2-practicas/
│   ├── practica-01-dominio-puro/
│   └── practica-02-adaptadores/
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
| [01-ports-and-adapters.md](1-teoria/01-ports-and-adapters.md) | Origen (Alistair Cockburn), hexágono, driving vs driven, ventajas |
| [02-domain-model-y-value-objects.md](1-teoria/02-domain-model-y-value-objects.md) | Modelo de dominio puro (sin Spring/JPA), Value Objects, use cases |
| [03-hexagonal-en-spring-boot.md](1-teoria/03-hexagonal-en-spring-boot.md) | Adaptadores REST + JPA, estructura de paquetes, wiring con Spring |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-dominio-puro](2-practicas/practica-01-dominio-puro/) | Dominio puro sin Spring: entidades, value objects y puertos |
| [practica-02-adaptadores](2-practicas/practica-02-adaptadores/) | Implementar adaptadores REST + JPA conectados al dominio |

### Proyecto (2.5h)

[📦 Sistema de Reservas con Arquitectura Hexagonal](3-proyecto/README.md) — `Room` + `Booking`: dominio puro, puertos de entrada/salida, adaptadores REST + JPA, sin dependencias de Spring en la capa de dominio.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Ports & Adapters | 45min |
| Teoría: Domain model + use cases | 45min |
| Teoría: Adaptadores en Spring Boot | 30min |
| Práctica 01: Puertos e interfaces | 1h |
| Práctica 02: Caso de uso puro | 1.25h |
| Práctica 03: Refactor a hexagonal | 1.25h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Paquetes separados: `domain/`, `application/`, `infrastructure/`
- [ ] Modelo de dominio **sin** anotaciones `@Entity` ni `@Component`
- [ ] Al menos un use case probado en unitario sin Spring Context
- [ ] Adaptador JPA que implementa el puerto de persistencia
- [ ] Diagrama del hexágono incluido en el README del proyecto

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 08 — Arquitectura en Capas](../week-08-arquitectura_en_capas/README.md) |
| ➡️ Siguiente | [Semana 10 — Spring Security](../week-10-spring_security/README.md) |
