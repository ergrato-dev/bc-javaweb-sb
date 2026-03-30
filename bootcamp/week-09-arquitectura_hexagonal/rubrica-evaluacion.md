# Rúbrica de Evaluación — Semana 09
## Arquitectura Hexagonal

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
| **Ports & Adapters** | Explica hexágono, dentro (dominio) vs fuera (infraestructura), driving vs driven | Distingue puertos de adaptadores | Confunde con arquitectura en capas |
| **Puertos de entrada/salida** | Define `InputPort` (use case interface) y `OutputPort` (repository interface); explica quién los implementa | Define puertos correctamente | No distingue input de output port |
| **Dominio puro** | Modelo de dominio sin anotaciones JPA (`@Entity`), Spring (`@Component`) ni web | Dominio sin Spring, pero con JPA | `@Entity` en el dominio |
| **Adaptadores** | Explica REST como adaptador primario y JPA como adaptador secundario | Implementa adaptador JPA correctamente | No sabe cómo conectar dominio con Spring |
| **vs. Capas** | Diferencia concreta: en hexagonal el dominio NO depende de ningún framework | Sabe que hexagonal es más desacoplado | No ve diferencia práctica |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Estructura de paquetes** | `domain/`, `application/`, `infrastructure/` claramente separados | `domain/` y `infrastructure/` separados | Estructura plana sin separación |
| **Dominio testeable sin Spring** | Test unitario del use case sin levantar contexto Spring | Use case instanciable con `new UseCase(mockPort)` | Use case tiene `@Autowired` |
| **Puerto de persistencia** | Interfaz `UserRepository` en `domain/`; `JpaUserRepository` en `infrastructure/` | Interfaz en domain correctamente | Interfaz en infrastructure |
| **Wiring con Spring** | `@Bean` o `@Component` solo en adaptadores; use case recibe puerto por constructor | Bean de use case configurado correctamente | Mezcla anotaciones Spring en dominio |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Dominio sin frameworks** | `domain/` sin imports de `org.springframework.*` ni `jakarta.persistence.*` | Solo 1-2 imports de framework en dominio | Dominio acoplado a Spring/JPA |
| **Use case testeado** | Test unitario del use case que pasa sin levantar Spring context | Instancia el use case en el test | No tiene tests del use case |
| **Feature end-to-end** | HTTP → REST adapter → use case → domain → JPA adapter → DB → response | Feature principal funcionando | Solo partes aisladas conectadas |
| **Diagrama hexagonal** | Diagrama SVG o imagen que muestra el hexágono con todas las piezas etiquetadas | Diagrama textual / ASCII claro (se acepta) | Sin documentación visual |

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
- Paquete `domain/` sin imports de Spring ni JPA
- Al menos un test unitario del use case sin Spring Boot context
- `mvn test` en verde
- Entrega puntual (penalización del 10% por día de retraso)
