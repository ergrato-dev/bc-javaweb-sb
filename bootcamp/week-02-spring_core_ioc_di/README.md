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
│   ├── 01-ioc-y-dependency-injection.md
│   ├── 02-beans-ciclo-de-vida-y-escopos.md
│   └── 03-configuracion-properties-y-profiles.md
├── 2-practicas/
│   ├── practica-01-ioc-beans/
│   └── practica-02-scopes-lifecycle/
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
| [01-ioc-y-dependency-injection.md](1-teoria/01-ioc-y-dependency-injection.md) | ¿Qué es IoC? `ApplicationContext`, DI por constructor |
| [02-beans-ciclo-de-vida-y-escopos.md](1-teoria/02-beans-ciclo-de-vida-y-escopos.md) | `@Component`, `@Service`, `@Repository`, ciclo de vida, `@Scope` |
| [03-configuracion-properties-y-profiles.md](1-teoria/03-configuracion-properties-y-profiles.md) | `@Configuration`, `@Bean`, `@Value`, `@ConfigurationProperties`, perfiles |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-ioc-beans](2-practicas/practica-01-ioc-beans/) | Registrar beans y explorar el contenedor IoC |
| [practica-02-scopes-lifecycle](2-practicas/practica-02-scopes-lifecycle/) | Ciclo de vida, `@PostConstruct`, `@PreDestroy`, `@Scope` |

### Proyecto (2.5h)

[📦 Calculadora de Precios con Spring IoC](3-proyecto/README.md) — Sistema de cálculo de descuentos usando múltiples strategies inyectadas por Spring.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: IoC y DI | 45min |
| Teoría: Beans, ciclo de vida y scopes | 45min |
| Teoría: Configuración y perfiles | 30min |
| Práctica 01: IoC beans | 1h |
| Práctica 02: Scopes y lifecycle | 1.5h |
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
