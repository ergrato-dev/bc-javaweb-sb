# Semana 12 — Testing Avanzado: JUnit 5 + Mockito

## 🎯 Objetivos de Aprendizaje

Al finalizar esta semana, serás capaz de:

- Eliminar tests duplicados con `@ParameterizedTest` (`@CsvSource`, `@MethodSource`)
- Organizar suites de tests con `@Nested` y `@DisplayName`
- Capturar argumentos exactos con `ArgumentCaptor`
- Verificar interacciones con `verify()`, `never()`, `inOrder()`
- Elegir el slice de test correcto: `@WebMvcTest`, `@DataJpaTest`, `@SpringBootTest`
- Escribir tests completos para una aplicación Spring Boot existente

---

## 📚 Requisitos Previos

- Semana 11 completada (Spring Security + JWT)
- Conocimiento básico de JUnit 5 y Mockito (weekly 06-08)
- Familiaridad con `@Mock`, `@InjectMocks`, `when...thenReturn`

---

## 🗂️ Estructura de la Semana

```
week-12-testing_avanzado/
├── 1-teoria/
│   ├── 01-junit5-avanzado.md         (@ParameterizedTest, @Nested, AssertJ avanzado)
│   ├── 02-mockito-avanzado.md        (ArgumentCaptor, spy, verify, doAnswer)
│   └── 03-testing-por-capas.md      (@WebMvcTest, @DataJpaTest, test pyramid)
├── 2-practicas/
│   ├── practica-01-junit5-avanzado/  (descomenta @ParameterizedTest, @Nested, etc.)
│   └── practica-02-mockito-avanzado/ (descomenta ArgumentCaptor, spy, verify)
├── 3-proyecto/
│   ├── README.md                     (instrucciones del proyecto)
│   └── starter/                      (E-Library API — implementa los tests)
└── 5-glosario/README.md
```

---

## 📝 Contenidos

| # | Tema | Archivo |
|---|------|---------|
| 1 | JUnit 5 Avanzado | [01-junit5-avanzado.md](1-teoria/01-junit5-avanzado.md) |
| 2 | Mockito Avanzado | [02-mockito-avanzado.md](1-teoria/02-mockito-avanzado.md) |
| 3 | Testing por Capas | [03-testing-por-capas.md](1-teoria/03-testing-por-capas.md) |
| 4 | Práctica 1: JUnit 5 | [practica-01](2-practicas/practica-01-junit5-avanzado/README.md) |
| 5 | Práctica 2: Mockito | [practica-02](2-practicas/practica-02-mockito-avanzado/README.md) |
| 6 | Proyecto E-Library | [3-proyecto/README.md](3-proyecto/README.md) |

---

## ⏱️ Distribución del Tiempo (8 horas)

| Actividad | Tiempo |
|---|---|
| Teoría 1: JUnit 5 avanzado | 45 min |
| Teoría 2: Mockito avanzado | 45 min |
| Teoría 3: Testing por capas | 30 min |
| Práctica 1: JUnit 5 | 1.5 h |
| Práctica 2: Mockito | 1.5 h |
| Proyecto: suite de tests | 3 h |

---

## 📌 Entregables

1. **Proyecto E-Library** con los 3 archivos de test implementados:
   - `BookServiceTest.java` — todos los TODOs resueltos
   - `LoanServiceTest.java` — todos los TODOs resueltos
   - `BookControllerTest.java` — todos los TODOs resueltos
2. Todos los tests pasando: `mvn test` → `BUILD SUCCESS`
3. Cobertura ≥ 80% en servicios (JaCoCo)

---

## 🔗 Navegación

- ← [Semana 11 — JWT y OAuth2](../week-11-jwt_y_oauth2/README.md)
- → [Semana 13 — Testcontainers e Integración](../week-13-testcontainers_e_integracion/README.md)
