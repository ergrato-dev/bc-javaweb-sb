# Semana 02 — Spring Core: IoC y Dependency Injection

> Comprende el corazón de Spring: el contenedor IoC, la inyección de dependencias
> y el ciclo de vida de los beans. La base de todo lo que viene.

---

## 🎯 Objetivos

- Entender qué es el contenedor IoC y por qué existe
- Registrar beans con `@Component`, `@Service`, `@Repository`
- Inyectar dependencias por constructor (recomendado) y por campo
- Configurar beans con `@Configuration` y `@Bean`
- Controlar el ciclo de vida con `@PostConstruct` y `@PreDestroy`
- Usar `@Scope` para beans singleton y prototype

---

## 📚 Requisitos Previos

- Semana 01: Java moderno (Lambdas, Records, Optional) ✅
- Interfaces en Java ✅
- Generics básicos ✅

---

## 🗂️ Estructura

```
week-02-spring_core_ioc_di/
├── 1-teoria/
│   ├── 01-ioc-container.md
│   ├── 02-beans-y-anotaciones.md
│   └── 03-ciclo-de-vida-y-scopes.md
├── 2-practicas/
│   ├── practica-01-primer-bean/
│   ├── practica-02-di-constructor/
│   └── practica-03-configuracion-java/
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
| [01-ioc-container.md](1-teoria/01-ioc-container.md) | ¿Qué es IoC? `ApplicationContext`, Spring vs new |
| [02-beans-y-anotaciones.md](1-teoria/02-beans-y-anotaciones.md) | `@Component`, `@Service`, `@Repository`, `@Controller`, `@Bean` |
| [03-ciclo-de-vida-y-scopes.md](1-teoria/03-ciclo-de-vida-y-scopes.md) | `@PostConstruct`, `@PreDestroy`, `@Scope`, singleton vs prototype |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-primer-bean](2-practicas/practica-01-primer-bean/) | Registrar y obtener el primer bean del contenedor |
| [practica-02-di-constructor](2-practicas/practica-02-di-constructor/) | Inyección por constructor vs `@Autowired` |
| [practica-03-configuracion-java](2-practicas/practica-03-configuracion-java/) | Configuración programática con `@Configuration` y `@Bean` |

### Proyecto (2.5h)

[📦 Calculadora de Precios con Spring IoC](3-proyecto/README.md) — Sistema de cálculo de descuentos usando múltiples strategies inyectadas por Spring.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: IoC Container | 45min |
| Teoría: Beans y anotaciones | 45min |
| Teoría: Ciclo de vida y scopes | 30min |
| Práctica 01: Primer bean | 45min |
| Práctica 02: DI por constructor | 1h |
| Práctica 03: Java Config | 45min |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Prácticas completadas (código descomentado y funcionando)
- [ ] Proyecto usando inyección por constructor (no `@Autowired` en campo)
- [ ] Al menos una clase con `@Configuration` + `@Bean`
- [ ] Demostrar comprensión del ciclo de vida con `@PostConstruct`

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 01 — Java Moderno](../week-01-java_moderno_streams_y_records/README.md) |
| ➡️ Siguiente | [Semana 03 — Spring Boot: Configuración y MVC](../week-03-spring_boot_configuracion_y_mvc/README.md) |
