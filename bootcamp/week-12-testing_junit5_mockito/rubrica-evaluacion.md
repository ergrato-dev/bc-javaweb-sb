# Rúbrica de Evaluación — Semana 12
## Testing con JUnit 5 y Mockito

---

## 📊 Distribución de Evidencias

| Tipo | Porcentaje | Descripción |
|------|-----------|-------------|
| 🧠 Conocimiento | 30% | Evaluación teórica de conceptos |
| 💪 Desempeño | 40% | Ejercicios prácticos en clase |
| 📦 Producto | 30% | Proyecto entregable funcional |

---

## 🧠 Conocimiento (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Pirámide de tests** | Diferencia unitarios/slice tests/integración; explica cuándo usar cada uno; costo relativo | Distingue unitarios de integración | Cree que solo importan los tests end-to-end |
| **Mockito** | Explica `when().thenReturn()`, `verify()`, `ArgumentCaptor`, cuándo usar `@Mock` vs `@Spy` | Usa `when().thenReturn()` y `verify()` correctamente | Mocks creados con `Mockito.mock()` manualmente sin anotaciones |
| **`@WebMvcTest`** | Carga solo el slice de web (controller); no levanta JPA; `@MockBean` para dependencias | `@WebMvcTest` funcionando con `MockMvc` | Usa `@SpringBootTest` para todo |
| **`@DataJpaTest`** | H2 automático; no carga security ni web; prueba queries y repositorios | `@DataJpaTest` con H2 funcionando | Tests de repositorio con `@SpringBootTest` |
| **AssertJ** | Usa `assertThat(result).isEqualTo(expected)`, `.isNotNull()`, `.hasSize(3)`, `.contains()` | AssertJ en lugar de JUnit assertions básicas | Solo `assertEquals()` y `assertNotNull()` de JUnit |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Tests unitarios de service** | Al menos 3 tests: caso feliz, email duplicado, entidad no encontrada | Al menos 2 tests con mocks | 1 test sin mocks |
| **`@WebMvcTest` funcionando** | `GET /{id}` → 200 con body JSON + `GET /99` → 404; `@MockBean` del service | Un test 200 y uno 404 con MockMvc | Test de controller con `@SpringBootTest` completo |
| **`@DataJpaTest` en uso** | Prueba al menos un método de `findBy*` con datos de prueba reales en H2 | `findByEmail` probado con H2 | Sin tests de repositorio |
| **Convención de nombres** | `shouldReturnUserWhenExists()`, `shouldThrow404WhenUserNotFound()` | Nombres descriptivos aunque no perfectos | Nombres como `test1()` o `testGetUser()` |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Cobertura de las 3 capas** | Tests unitarios + `@WebMvcTest` + `@DataJpaTest` todos presentes | 2 de las 3 capas cubiertas | Solo una capa testeada |
| **`mvn test` en verde** | Todos los tests pasan sin flakiness | ≥90% de tests pasan | Más del 20% de tests fallan |
| **Aislamiento correcto** | Unitarios sin Spring context; web tests sin JPA; JPA tests sin web | Mayoría correctamente aislados | `@SpringBootTest` para todo |
| **Aserciones significativas** | Tests fallarían si cambia la lógica; no solo verifican que no hay excepción | Aserciones sobre el resultado | `Assertions.assertDoesNotThrow()` sin verificar resultado |

---

## 📏 Escala de Calificación

| Nota | Rango |
|------|-------|
| A | 90–100% |
| B | 80–89% |
| C | 70–79% |
| D | <70% |

---

## ✅ Criterios de Aprobación

- Mínimo **70%** en cada tipo de evidencia
- `mvn test` sin fallos
- Al menos 5 tests unitarios en service
- Al menos 2 tests con `@WebMvcTest` (caso éxito + caso error)
- Entrega puntual (penalización del 10% por día de retraso)
