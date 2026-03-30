# Rúbrica de Evaluación — Semana 02
## Spring Core: IoC y Dependency Injection

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
| **IoC y container** | Explica inversión de control, diferencia IoC container de `new`, relaciona con testabilidad | Concepto básico claro | Confunde IoC con herencia |
| **Tipos de inyección** | Diferencia constructor vs field vs setter; explica por qué constructor es preferido | Sabe que existe inyección por constructor y field | Solo conoce `@Autowired` en field |
| **Stereotype annotations** | Distingue semánticamente `@Component`, `@Service`, `@Repository`, `@Controller` | Sabe que existen los 4, diferencia básica | Usa solo `@Component` para todo |
| **Bean scopes** | Singleton vs Prototype: casos de uso, cuándo usar cada uno | Sabe que Singleton es el default | No entiende el concepto de scope |
| **`@Configuration` y `@Bean`** | Crea beans de terceros (no propios) con `@Bean` correctamente | Copia el patrón sin entender del todo | No sabe cuándo usar `@Bean` vs `@Component` |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Inyección por constructor** | Todos los beans inyectados por constructor, sin `@Autowired` en campo | Mayoría por constructor, alguno por campo | Solo usa `@Autowired` en campos |
| **Definir beans de configuración** | `@Configuration` con `@Bean` para cliente HTTP o utilidad externa | Crea `@Bean` simple correctamente | No puede crear `@Bean` propio |
| **Ciclo de vida** | Usa `@PostConstruct` y `@PreDestroy` con propósito claro | Ejecuta `@PostConstruct` | No conoce hooks de ciclo de vida |
| **Inicio sin errores** | `mvn spring-boot:run` arranca sin `NoSuchBeanDefinitionException` | Arranca con warnings no críticos | No arranca por problemas de DI |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Arquitectura de beans** | Capas bien definidas: `@Controller` → `@Service` → `@Repository` | 2 capas claramente separadas | Todo en una sola clase |
| **Inyección por constructor** | Sin `@Autowired` en fields; con `final` en dependencias | Mayoritariamente por constructor | `@Autowired` en fields |
| **Functionalidad completa** | Todos los requerimientos del proyecto implementados | ≥80% de requerimientos | <70% de requerimientos |
| **Sin instanciación manual** | Cero `new ServiceImpl()` o `new RepositoryImpl()` | 1-2 instanciaciones manuales como máximo | Mezcla `new` con inyección |

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

- Mínimo **70%** en cada tipo de evidencia
- Aplicación inicia sin errores de DI
- Sin `new ServiceImpl()` ni instanciación manual de beans Spring
- Entrega puntual (penalización del 10% por día de retraso)
