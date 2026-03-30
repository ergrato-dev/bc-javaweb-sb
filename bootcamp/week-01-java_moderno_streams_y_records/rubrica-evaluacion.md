# Rúbrica de Evaluación — Semana 01
## Java Moderno: Streams, Records y Optional

---

## 📊 Distribución de Evidencias

| Tipo | Porcentaje | Descripción |
|------|-----------|-------------|
| 🧠 Conocimiento | 30% | Evaluación teórica de conceptos |
| 💪 Desempeño | 40% | Ejercicios prácticos en clase |
| 📦 Producto | 30% | Proyecto entregable funcional |

---

## 🧠 Conocimiento (30%)

### Cuestionario / Evaluación Teórica

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Lambdas y referencias a métodos** | Explica la diferencia, identifica cuándo usar cada una, escribe lambdas sin error | Diferencia básica clara, escribe lambdas simples | No distingue lambdas de métodos anónimos |
| **Streams API** | Explica operaciones intermedias vs terminales, encadena operaciones sin errores | Usa `filter`, `map`, `collect` correctamente | Confunde Stream con Collection |
| **Optional** | Explica `orElseThrow`, `map`, `flatMap`; nunca usa `get()` sin verificar | Usa `isPresent()` + `get()` con verificación | No entiende la utilidad de Optional |
| **Records** | Define record, explica inmutabilidad, diferencia de POJO con getters | Crea records simples correctamente | Confunde record con clase normal |
| **`var` y switch expressions** | Usa `var` apropiadamente y switch expressions con `->` sin errores | Uso básico correcto | No usa Java moderno o usa sintaxis obsoleta |

---

## 💪 Desempeño (40%)

### Ejercicios Prácticos

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Transformación con Streams** | Pipeline complejo: `filter` + `map` + `sorted` + `collect` funcionando | Pipeline simple de 2-3 operaciones | No puede encadenar operaciones |
| **Manejo de Optional** | Cadena de `Optional.map().filter().orElseThrow()` sin NPE | Uso básico con `isPresent()` | NullPointerException en algún caso |
| **Records como DTOs** | Record con validación, `compact constructor`, métodos custom | Record básico con campos | Usa clases con getters manuales |
| **Código limpio y moderno** | Cero código verboso, usa `var`, no casteos innecesarios | Mayormente moderno, algunos resabios pre-Java 16 | Mezcla sintaxis antigua y moderna |

---

## 📦 Producto (30%)

### Proyecto Integrador

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Funcionalidad** | Todos los requerimientos implementados y funcionando | Requerimientos principales (≥80%) | Menos del 70% de requerimientos |
| **Uso de Java moderno** | Streams, Records, Optional, `var` usados en todo el código | Uso en la mayoría de casos | Código mayormente pre-Java 16 |
| **Calidad del código** | Métodos pequeños, nombres descriptivos, sin lógica repetida | Código legible con alguna repetición | Código difícil de leer o muy repetitivo |
| **Compilación y ejecución** | `mvn compile` sin warnings ni errores | Compila con warnings menores | No compila |

---

## 📏 Escala de Calificación

| Nota | Rango | Descripción |
|------|-------|-------------|
| A | 90–100% | Sobresaliente |
| B | 80–89% | Notable |
| C | 70–79% | Aprobado |
| D | <70% | Reprobado — requiere refuerzo |

---

## ✅ Criterios de Aprobación

- Mínimo **70%** en cada tipo de evidencia (Conocimiento, Desempeño, Producto)
- Proyecto compila y ejecuta sin errores
- Código no usa API obsoleta (`javax.*` en lugar de `jakarta.*`, no aplica esta semana)
- Entrega puntual (penalización del 10% por día de retraso)
