# Rúbrica de Evaluación — Semana 13: Testcontainers e Integración

## Proyecto: Inventory API Tests

### 🧠 Conocimiento (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|---------------------|---------------------|
| Comprensión de Testcontainers | Explica correctamente por qué H2 no replica PostgreSQL y cómo `@ServiceConnection` elimina la configuración manual | Describe el propósito de Testcontainers pero confunde algunos detalles | No puede explicar la diferencia entre tests con H2 y Testcontainers |
| Tipos de test por capa | Distingue claramente `@DataJpaTest`, unit tests con Mockito y `@SpringBootTest` y cuando usar cada uno | Conoce los tres tipos pero los confunde en algunos casos | Solo conoce un tipo de test |
| TDD | Puede describir el ciclo Red-Green-Refactor con un ejemplo concreto | Conoce el concepto pero no lo aplica consistentemente | No sabe qué significa TDD |

### 💪 Desempeño (40%)

| Criterio | Puntos | Descripción |
|----------|--------|-------------|
| `ProductRepositoryTest` implementado | 15 pts | Los 4 métodos del repositorio tienen tests funcionales que pasan |
| `ProductServiceTest` implementado | 20 pts | Todas las clases `@Nested` tienen al menos el happy path implementado |
| `ProductApiIntegrationTest` implementado | 15 pts | Al menos 6 de los 10 tests están implementados y pasan |

**Requisito mínimo:** Al menos 2 de los 3 archivos de test deben tener todos sus métodos implementados.

### 📦 Producto (30%)

| Criterio | Puntos | Descripción |
|----------|--------|-------------|
| Todos los tests pasan (`mvn test`) | 10 pts | `BUILD SUCCESS` sin errores |
| Cobertura JaCoCo ≥ 70% en `service/` | 10 pts | Reportado en `target/site/jacoco/` |
| Código limpio y convenciones | 5 pts | Patrón `given/when/then`, nombres descriptivos en `@DisplayName` |
| Captura del reporte JaCoCo | 5 pts | Screenshot del reporte HTML entregado |

---

## Escala de Calificación

| Puntos | Calificación |
|--------|-------------|
| 90–100 | Excelente — listo para escribir tests en un equipo profesional |
| 80–89 | Muy bien — comprende los conceptos y los aplica correctamente |
| 70–79 | Aprobado — requiere refuerzo en integración con `@SpringBootTest` |
| < 70 | Insuficiente — necesita revisar teoría y repetir las prácticas |

---

## Lista de Verificación del Estudiante

Antes de entregar:

- [ ] `ProductRepositoryTest` — 4 métodos implementados y pasando
- [ ] `ProductServiceTest` — todas las clases `@Nested` con al menos happy path
- [ ] `ProductApiIntegrationTest` — flujo lifecycle completo implementado
- [ ] `mvn test` termina con `BUILD SUCCESS`
- [ ] Reporte JaCoCo generado con `mvn jacoco:report`
- [ ] Cobertura de `ProductService` ≥ 70%
- [ ] Captura del reporte JaCoCo adjunta
