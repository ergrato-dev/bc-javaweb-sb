# Rúbrica de Evaluación — Semana 13
## Integration Tests y Testcontainers

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
| **`@SpringBootTest`** | Diferencia `MOCK` vs `RANDOM_PORT` vs `DEFINED_PORT`; costo de levantar contexto completo | Usa `RANDOM_PORT` con `TestRestTemplate` | Prefiere `@SpringBootTest` para tests unitarios |
| **Testcontainers** | Explica qué es un container efímero; `@DynamicPropertySource` para sobreescribir datasource | `PostgreSQLContainer` corriendo en tests | Sin Testcontainers (usa H2 siempre) |
| **`@DynamicPropertySource`** | Usa `@DynamicPropertySource` para inyectar URL+credenciales del container en propiedades | Configuración dinámica funcionando | Hardcodea URL de PostgreSQL en tests |
| **JaCoCo** | Configura plugin, genera reporte HTML, impone umbral de cobertura en `verify` | JaCoCo genera reporte | JaCoCo instalado pero sin umbral |
| **TDD básico** | Ciclo Red-Green-Refactor: primero el test falla, luego implementa, luego refactoriza | Escribe tests antes de implementar | Tests escritos después de la implementación |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Test end-to-end con `@SpringBootTest`** | Crea usuario vía HTTP → verifica en BD que existe | Request HTTP → response verificada | Solo verifica que no hay excepción |
| **PostgreSQL real en tests** | `PostgreSQLContainer` levanta al inicio; `@DynamicPropertySource` funciona | Container levanta correctamente | H2 en todos los tests |
| **JaCoCo en verde** | `mvn verify` pasa con umbral ≥ 70% configurado | Reporte generado con algún % | Sin JaCoCo configurado |
| **Tests independientes** | Cada test limpia o recrea datos; no hay dependencia de orden de ejecución | Tests mayormente independientes | Tests fallan si se corren en diferente orden |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Suite completa** | Unitarios + slice tests + integración con Testcontainers | 2 de los 3 tipos | Solo unitarios o solo integración |
| **`mvn verify` en verde** | Todos los tests y cobertura pasan | `mvn test` pasa pero cobertura no configurada | `mvn test` con fallos |
| **CI ejecuta tests** | GitHub Actions workflow corre `mvn verify` con Testcontainers en CI | GitHub Actions corre `mvn test` | Sin CI configurado |
| **Reporte JaCoCo** | Reporte HTML muestra cobertura por clase; clases clave con ≥70% | Reporte visible | Sin reporte |

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
- `mvn verify` pasa (tests + cobertura)
- Al menos un test de integración con PostgreSQL real (Testcontainers)
- Tests no dependen del orden de ejecución
- Entrega puntual (penalización del 10% por día de retraso)
