# Rúbrica de Evaluación — Semana 08
## Arquitectura en Capas Completa

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
| **Reglas de dependencia** | Explica que Controller → Service → Repository (no al revés); motivos | Conoce la dirección correcta | No sabe por qué separar capas |
| **Service Layer** | Ubica correctamente lógica de negocio: validación de reglas, orquestación de repos, transacciones | Lógica de negocio en service (no en controller) | Lógica en controller o en repository |
| **Interfaces de servicio** | Explica beneficios de `UserService` + `UserServiceImpl` (testing, swap) | Crea interfaz + implementación | No usa interfaz, solo la implementación |
| **Jerarquía de excepciones** | Diseña `BusinessException` → `ResourceNotFoundException`, `ConflictException` | Al menos `ResourceNotFoundException` personalizada | Solo usa `Exception` genérica |
| **MapStruct avanzado** | Mapea listas `List<Entity>` → `List<Response>`, campos anidados, `@AfterMapping` | Mapeo de lista funcionando | Solo mapeo 1:1 de campos simples |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Controller sin lógica** | Controller solo llama service + retorna ResponseEntity; sin `if` de negocio | 1-2 `if` simples en controller | Lógica de negocio en controller |
| **Service con interfaz** | `@Service` implementa interfaz; controller inyecta la interfaz | Service implementa interfaz | Service sin interfaz |
| **DTOs diferenciados** | `CreateRequest`, `UpdateRequest`, `Response` distintos con campos apropiados | Al menos `Request` y `Response` separados | Un solo DTO para todo |
| **Exception handling** | `@ControllerAdvice` maneja la jerarquía completa con códigos HTTP correctos | 404 y 400 manejados | Sin manejo global |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Arquitectura respetada** | Cero dependencias inversas entre capas; ningún import de controller en service | Sin ciclos de dependencia | Controller importa Repository directamente |
| **Feature end-to-end** | Una feature completa: HTTP request → controller → service → repository → DB → response | Feature principal funcionando | Solo partes aisladas funcionando |
| **Errores semánticos** | `404` cuando no existe, `409` en conflicto, `422` en regla de negocio violada | `404` y `400` correctos | Siempre `500` en errores |
| **MapStruct en toda la cadena** | Sin `userEntity.getName()` manual en controller o service para construir DTOs | MapStruct en el 70% de los mapeos | Mapeo manual con setters |

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
- Controller no tiene lógica de negocio (solo llamadas a service)
- Repository no tiene lógica de negocio
- Entidades JPA nunca salen del paquete `service` hacia el `controller`
- Entrega puntual (penalización del 10% por día de retraso)
