# Semana 13 — Testcontainers e Integración

## 🎯 Objetivos de Aprendizaje

Al finalizar esta semana, el estudiante podrá:

- ✅ Explicar por qué H2 no es suficiente para tests de integración reales
- ✅ Configurar Testcontainers con `@ServiceConnection` en Spring Boot 3.x
- ✅ Escribir tests de repositorio con `@DataJpaTest` + PostgreSQL real
- ✅ Diseñar tests de integración full-stack con `TestRestTemplate` y `@SpringBootTest`
- ✅ Aplicar el patrón `AbstractIntegrationTest` para reusar la configuración del contenedor
- ✅ Usar `@Sql` para fixtures de datos en tests específicos
- ✅ Reportar cobertura con JaCoCo y entender el ciclo Red-Green-Refactor del TDD

---

## 📚 Requisitos Previos

- Semana 12: JUnit 5, Mockito, `@WebMvcTest`, `@DataJpaTest`
- Docker Desktop o Docker Engine corriendo
- Conexión a internet (primera ejecución descarga `postgres:17-alpine`)

---

## 🗂️ Estructura de la Semana

```
week-13-testcontainers_e_integracion/
├── README.md                        ← este archivo
├── rubrica-evaluacion.md
├── 0-assets/
├── 1-teoria/
│   ├── 01-testcontainers-setup.md   ← Por qué H2 falla, setup, @ServiceConnection
│   ├── 02-tests-de-integracion.md   ← @Sql, @Transactional, AbstractIntegrationTest
│   └── 03-tdd-y-calidad.md          ← TDD, test naming, JaCoCo, fragile vs robust
├── 2-practicas/
│   ├── practica-01-testcontainers/  ← Primer contenedor, @DataJpaTest + TC
│   └── practica-02-integration-tests/ ← TestRestTemplate, flujo CRUD completo
├── 3-proyecto/                      ← Inventory API (tienes que escribir los tests)
│   ├── README.md
│   └── starter/                     ← Maven project completo
└── 5-glosario/
    └── README.md
```

---

## 📝 Contenidos

### Teoría (2h)

| Archivo | Tema |
|---------|------|
| [01-testcontainers-setup.md](1-teoria/01-testcontainers-setup.md) | H2 limitaciones, dependencias, `@ServiceConnection`, `@DataJpaTest + TC` |
| [02-tests-de-integracion.md](1-teoria/02-tests-de-integracion.md) | `@Sql`, `@Transactional` en tests, `TestRestTemplate`, `AbstractIntegrationTest` |
| [03-tdd-y-calidad.md](1-teoria/03-tdd-y-calidad.md) | TDD Red-Green-Refactor, naming conventions, JaCoCo, anti-patrones |

### Prácticas (3.5h)

| Carpeta | Ejercicio |
|---------|-----------|
| [practica-01-testcontainers](2-practicas/practica-01-testcontainers/) | Configurar Testcontainers, primer test con PostgreSQL real |
| [practica-02-integration-tests](2-practicas/practica-02-integration-tests/) | Tests de integración full-stack con `TestRestTemplate` y `@Sql` |

### Proyecto (2.5h)

La **Inventory API** ya está implementada. Tu tarea: escribir los tres tipos de test.

[Ver instrucciones del proyecto →](3-proyecto/README.md)

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Testcontainers setup | 40 min |
| Teoría: Integration tests | 40 min |
| Teoría: TDD y calidad | 40 min |
| Práctica 1: Primer contenedor | 1h 45min |
| Práctica 2: Tests de integración | 1h 45min |
| Proyecto: Inventory API tests | 2h 30min |

---

## 📌 Entregables

- [ ] `ProductRepositoryTest.java` completo (4 tests pasando)
- [ ] `ProductServiceTest.java` completo (todos los `@Nested` completos)
- [ ] `ProductApiIntegrationTest.java` completo (flujo CRUD + casos de error)
- [ ] Reporte JaCoCo con cobertura ≥ 70%
- [ ] Captura de pantalla del reporte (`target/site/jacoco/index.html`)

---

## 🔗 Navegación

← [Semana 12 — Testing Avanzado](../week-12-testing_avanzado/README.md)
→ [Semana 14 — Cache, Async y Events](../week-14-cache_async_events/README.md)
