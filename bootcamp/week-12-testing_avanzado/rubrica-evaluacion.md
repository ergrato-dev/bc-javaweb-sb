# Rúbrica de Evaluación — Semana 12

## Semana 12: Testing Avanzado con JUnit 5 y Mockito

---

## Conocimiento 🧠 (30%)

| Criterio | Logrado (100%) | En progreso (70%) | Inicial (40%) |
|---|---|---|---|
| @ParameterizedTest | Explica fuentes de datos y cuándo usarlo | Usa @CsvSource pero no @MethodSource | Confunde con @Test regular |
| ArgumentCaptor | Explica por qué capturar vs. `any()` | Usa captor pero no analiza el valor | Conoce el concepto pero no implementa |
| Slices de test | Distingue @WebMvcTest/@DataJpaTest/@SpringBootTest | Usa @SpringBootTest para todo | No conoce diferencias |
| Pirámide de tests | Justifica la distribución: muchos unit, pocos E2E | La conoce pero no la aplica | No conoce el concepto |

---

## Desempeño 💪 (40%)

### Práctica 1 — JUnit 5 Avanzado

| Criterio | Pts |
|---|---|
| PASO 1: @ParameterizedTest con @CsvSource funcionando | 25 |
| PASO 2: @Nested con clases internas nombradas | 25 |
| PASO 3: @TestMethodOrder con @Order correcto | 25 |
| PASO 4: assertThatThrownBy y SoftAssertions | 25 |

### Práctica 2 — Mockito Avanzado

| Criterio | Pts |
|---|---|
| PASO 1: ArgumentCaptor capturando y verificando | 25 |
| PASO 2: spy() con doReturn() | 25 |
| PASO 3: verify() con times/never/inOrder | 25 |
| PASO 4: doAnswer simulating side effects | 25 |

---

## Producto 📦 (30%)

### E-Library API — Suite de Tests

| Criterio | Descripción | Pts |
|---|---|---|
| `mvn test` pasa | `BUILD SUCCESS` con 0 errores | 30 |
| BookServiceTest | Todos los TODOs implementados (10 tests) | 25 |
| LoanServiceTest | Las 4 reglas de negocio cubiertas | 25 |
| BookControllerTest | Todos los códigos HTTP verificados | 15 |
| Cobertura ≥80% | JaCoCo reporta ≥80% en servicios | 5 |

---

## Mínimo aprobatorio: 70% en cada sección
