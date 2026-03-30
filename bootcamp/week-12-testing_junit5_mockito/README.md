# Semana 12 — Testing con JUnit 5 y Mockito

> El código sin tests es código con fecha de caducidad.
> Aprende a probar cada capa con la herramienta correcta.

---

## 🎯 Objetivos

- Escribir tests unitarios con JUnit 5 y anotaciones `@Test`, `@BeforeEach`
- Aislar dependencias con `Mockito.mock()`, `@Mock`, `@InjectMocks`
- Testear controllers con `@WebMvcTest` y `MockMvc`
- Testear repositorios JPA con `@DataJpaTest` (H2 en memoria)
- Usar AssertJ para aserciones legibles: `assertThat(...).isEqualTo(...)`
- Distinguir qué testear en cada capa y por qué

---

## 📚 Requisitos Previos

- Semanas 05–11: Stack completo (JPA + Security) ✅
- Conceptos básicos de testing (qué es un test, por qué importa) ✅

---

## 🗂️ Estructura

```
week-12-testing_junit5_mockito/
├── 1-teoria/
│   ├── 01-junit5-y-estructura-de-tests.md
│   ├── 02-mockito-mocking-y-stubbing.md
│   └── 03-webmvctest-y-datajpatest.md
├── 2-practicas/
│   ├── practica-01-unit-tests-service/
│   ├── practica-02-webmvctest-controller/
│   └── practica-03-datajpatest-repository/
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
| [01-junit5-y-estructura-de-tests.md](1-teoria/01-junit5-y-estructura-de-tests.md) | `@Test`, `@BeforeEach`, `@AfterAll`, `@Nested`, `@ParameterizedTest`, pirámide de tests |
| [02-mockito-mocking-y-stubbing.md](1-teoria/02-mockito-mocking-y-stubbing.md) | `when().thenReturn()`, `verify()`, `@Mock`, `@InjectMocks`, `ArgumentCaptor` |
| [03-webmvctest-y-datajpatest.md](1-teoria/03-webmvctest-y-datajpatest.md) | `@WebMvcTest`, `MockMvc`, `@MockBean`, `@DataJpaTest`, H2 para JPA tests |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-unit-tests-service](2-practicas/practica-01-unit-tests-service/) | Tests unitarios de `UserService`: crear, buscar, validar email duplicado |
| [practica-02-webmvctest-controller](2-practicas/practica-02-webmvctest-controller/) | `@WebMvcTest`: testear `GET /users/{id}` con 200 y 404 |
| [practica-03-datajpatest-repository](2-practicas/practica-03-datajpatest-repository/) | `@DataJpaTest`: custom queries, relaciones, `findByEmail` |

### Proyecto (2.5h)

[📦 Suite de Tests para API de Tareas](3-proyecto/README.md) — Tests de las 3 capas: unitarios en service, `@WebMvcTest` en controllers, `@DataJpaTest` en repositorios. Cobertura mínima del 70% con JaCoCo.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: JUnit 5 | 45min |
| Teoría: Mockito | 45min |
| Teoría: WebMvcTest + DataJpaTest | 30min |
| Práctica 01: Unit tests service | 1.25h |
| Práctica 02: WebMvcTest | 1.25h |
| Práctica 03: DataJpaTest | 1h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Al menos 5 tests unitarios de la service layer con Mockito
- [ ] Al menos 3 tests de controller con `@WebMvcTest` (2xx y 4xx)
- [ ] Al menos 2 tests de repositorio con `@DataJpaTest`
- [ ] Todos los tests en verde (`mvn test`)
- [ ] Convención de nombres: `should[Resultado]When[Condicion]`

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 11 — JWT y OAuth2](../week-11-jwt_y_oauth2/README.md) |
| ➡️ Siguiente | [Semana 13 — Integration Tests y Testcontainers](../week-13-integration_tests_testcontainers/README.md) |
