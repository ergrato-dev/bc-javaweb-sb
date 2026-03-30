# Semana 13 — Integration Tests y Testcontainers

> Tests que usan la base de datos real. Con Testcontainers,
> tu CI/CD prueba contra PostgreSQL idéntico a producción.

---

## 🎯 Objetivos

- Escribir tests de integración con `@SpringBootTest` (contexto completo)
- Levantar PostgreSQL real en tests con Testcontainers
- Testear el flujo completo: HTTP request → controller → service → DB → response
- Usar `TestRestTemplate` para tests de integración de alto nivel
- Generar reportes de cobertura con JaCoCo
- Aplicar principios básicos de TDD (Red → Green → Refactor)

---

## 📚 Requisitos Previos

- Semana 12: JUnit 5, Mockito, `@WebMvcTest`, `@DataJpaTest` ✅
- Docker instalado y corriendo ✅

---

## 🗂️ Estructura

```
week-13-integration_tests_testcontainers/
├── 1-teoria/
│   ├── 01-integration-tests-springboottest.md
│   ├── 02-testcontainers-postgresql.md
│   └── 03-jacoco-y-tdd.md
├── 2-practicas/
│   ├── practica-01-springboottest-full/
│   ├── practica-02-testcontainers-postgres/
│   └── practica-03-jacoco-coverage/
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
| [01-integration-tests-springboottest.md](1-teoria/01-integration-tests-springboottest.md) | `@SpringBootTest(webEnvironment=RANDOM_PORT)`, `@AutoConfigureMockMvc`, `TestRestTemplate` |
| [02-testcontainers-postgresql.md](1-teoria/02-testcontainers-postgresql.md) | `@Testcontainers`, `@Container`, `PostgreSQLContainer`, `@DynamicPropertySource` |
| [03-jacoco-y-tdd.md](1-teoria/03-jacoco-y-tdd.md) | JaCoCo plugin Maven, reportes HTML, umbrales, ciclo Red→Green→Refactor |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-springboottest-full](2-practicas/practica-01-springboottest-full/) | Test end-to-end: crear usuario via HTTP y verificar en BD |
| [practica-02-testcontainers-postgres](2-practicas/practica-02-testcontainers-postgres/) | Reemplazar H2 en tests por PostgreSQL real con Testcontainers |
| [practica-03-jacoco-coverage](2-practicas/practica-03-jacoco-coverage/) | Configurar JaCoCo, generar reporte, imponer umbral del 70% |

### Proyecto (2.5h)

[📦 Tests de Integración para API de E-commerce](3-proyecto/README.md) — Suite completa: unitarios (Mockito) + slice tests (`@WebMvcTest`) + integración real (`@SpringBootTest` + Testcontainers). CI en GitHub Actions ejecuta todos.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: `@SpringBootTest` | 45min |
| Teoría: Testcontainers | 45min |
| Teoría: JaCoCo + TDD | 30min |
| Práctica 01: SpringBootTest full | 1.25h |
| Práctica 02: Testcontainers | 1.25h |
| Práctica 03: JaCoCo | 1h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Al menos un test `@SpringBootTest` con flujo completo verificado
- [ ] `PostgreSQLContainer` corriendo en tests de integración (no H2)
- [ ] `mvn verify` genera reporte JaCoCo en `target/site/jacoco/`
- [ ] Cobertura de líneas ≥ 70% configurada como requisito de build
- [ ] Tests pasando en CI (GitHub Actions, sin Docker-in-Docker manual)

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 12 — Testing con JUnit 5 y Mockito](../week-12-testing_junit5_mockito/README.md) |
| ➡️ Siguiente | [Semana 14 — Cache, Async y WebSocket](../week-14-cache_async_websocket/README.md) |
