# Rúbrica de Evaluación — Semana 14
## Cache, Async y WebSocket

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
| **Spring Cache** | Explica `@Cacheable` (lee caché), `@CacheEvict` (invalida), `@CachePut` (escribe siempre); Caffeine TTL | Usa `@Cacheable` y `@CacheEvict` correctamente | Solo usa `@Cacheable` sin invalidación |
| **`@Async`** | Explica que el método retorna inmediatamente; diferencia `void` vs `CompletableFuture<T>` | `@Async` funcionando en segundo plano | Cree que `@Async` es más lento |
| **`@Scheduled`** | Usa `cron`, `fixedRate`, `fixedDelay`; explica diferencia entre los dos últimos | `@Scheduled(fixedRate=...)` funcionando | Confunde `fixedRate` con `fixedDelay` |
| **WebSocket + STOMP** | Explica handshake, diferencia HTTP vs WS; `@MessageMapping` recibe, `@SendTo` broadcast | Endpoint STOMP funcionando básicamente | Confunde WebSocket con SSE o polling |
| **Cuándo usar caché** | Sabe qué NO cachear (datos muy volátiles, datos de usuario, datos por pagar) | Cachea datos que cambian poco | Cachea todo indiscriminadamente |

---

## 💪 Desempeño (40%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Cache hit verificable** | Logs muestran 1 query BD en primera llamada, 0 queries en siguientes (con `CACHE_MISS`/`CACHE_HIT` log) | Tiempo de respuesta notablemente menor en segunda llamada | Cache configurado pero no demostrable |
| **`@CacheEvict` funcionando** | Después de `PUT /products/{id}`, el caché se invalida y siguiente `GET` va a la BD | `@CacheEvict` en método de actualización | Sin invalidación de caché |
| **`@Async` no bloquea** | Logs muestran hilo HTTP termina antes que la tarea async; ID de thread diferente | Tarea corre en segundo plano | Tarea bloquea el hilo HTTP |
| **WebSocket con múltiples clientes** | 2 clientes conectados reciben el mismo broadcast cuando uno envía | Un cliente recibe su propio mensaje | Sin WebSocket funcional |

---

## 📦 Producto (30%)

| Criterio | Excelente (100%) | Satisfactorio (70%) | Insuficiente (<70%) |
|----------|-----------------|--------------------|--------------------|
| **Caché con lógica** | Cache con TTL configurado; invalidación correcta en CRUD | `@Cacheable` en al menos un endpoint | `@Cacheable` sin invalidación |
| **Tarea async** | `@Async` en caso de uso real (email, reporte, notificación); logs demuestran background | `@Async` funcionando | Sin `@Async` |
| **Tarea scheduled** | `@Scheduled` con propósito real (limpieza, reporte, ping); configurable via properties | `@Scheduled` corriendo con log visible | Sin `@Scheduled` |
| **WebSocket útil** | Notificaciones push en tiempo real relacionadas con el dominio del proyecto | WebSocket funcionando con un canal | Solo WebSocket de "hola mundo" |

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
- Cache funciona (primera llamada → BD; segunda llamada → cache)
- `@Async` no bloquea el hilo HTTP (demostrable con logs)
- `mvn spring-boot:run` sin errores
- Entrega puntual (penalización del 10% por día de retraso)
