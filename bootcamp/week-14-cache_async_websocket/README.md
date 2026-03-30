# Semana 14 — Cache, Async y WebSocket

> Más allá del CRUD: respuestas instantáneas con caché,
> tareas en background con @Async y tiempo real con WebSocket.

---

## 🎯 Objetivos

- Cachear respuestas costosas con `@Cacheable` (Caffeine y Redis)
- Invalidar caché con `@CacheEvict` y `@CachePut`
- Ejecutar tareas en segundo plano con `@Async` y `CompletableFuture`
- Programar tareas recurrentes con `@Scheduled`
- Implementar comunicación bidireccional en tiempo real con WebSocket + STOMP
- Saber cuándo usar (y cuándo no usar) cada característica

---

## 📚 Requisitos Previos

- Semanas 01–13: Stack completo incluyendo tests ✅
- Conceptos básicos de concurrencia (threads, blocking vs non-blocking) ✅

---

## 🗂️ Estructura

```
week-14-cache_async_websocket/
├── 1-teoria/
│   ├── 01-spring-cache-cacheable.md
│   ├── 02-async-y-scheduled.md
│   └── 03-websocket-stomp.md
├── 2-practicas/
│   ├── practica-01-cache-caffeine/
│   ├── practica-02-async-email/
│   └── practica-03-websocket-chat/
├── 3-proyecto/
│   ├── README.md
│   └── starter/
└── 5-glosario/
    └── README.md
```

---

## 📝 Contenidos

### Teoría (2h)

| Archivo | Tema |
|---------|------|
| [01-spring-cache-cacheable.md](1-teoria/01-spring-cache-cacheable.md) | `@EnableCaching`, `@Cacheable`, `@CacheEvict`, `@CachePut`, TTL, Caffeine vs Redis |
| [02-async-y-scheduled.md](1-teoria/02-async-y-scheduled.md) | `@EnableAsync`, `@Async`, `CompletableFuture<T>`, `@Scheduled`, `ThreadPoolTaskExecutor` |
| [03-websocket-stomp.md](1-teoria/03-websocket-stomp.md) | WebSocket handshake, STOMP frames, `@MessageMapping`, `@SendTo`, SockJS fallback |

### Prácticas (3.5h)

| Práctica | Descripción |
|----------|-------------|
| [practica-01-cache-caffeine](2-practicas/practica-01-cache-caffeine/) | Cachear `findProduct(id)` con Caffeine; medir tiempo de respuesta antes/después |
| [practica-02-async-email](2-practicas/practica-02-async-email/) | Enviar email de bienvenida en background sin bloquear el login del usuario |
| [practica-03-websocket-chat](2-practicas/practica-03-websocket-chat/) | Mini chat: enviar mensaje → broadcast a todos los suscritos via STOMP |

### Proyecto (2.5h)

[📦 Dashboard con Datos en Tiempo Real](3-proyecto/README.md) — API de métricas: estadísticas cacheadas (Caffeine), reporte diario generado con `@Scheduled` en background, WebSocket para notificaciones push de alertas.

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Spring Cache | 45min |
| Teoría: @Async + @Scheduled | 45min |
| Teoría: WebSocket + STOMP | 30min |
| Práctica 01: Cache Caffeine | 1.25h |
| Práctica 02: @Async email | 1.25h |
| Práctica 03: WebSocket chat | 1h |
| Proyecto integrador | 2.5h |
| **Total** | **8h** |

---

## 📌 Entregables

- [ ] Endpoint con `@Cacheable` y hit/miss verificable en logs
- [ ] `@CacheEvict` funciona al actualizar/eliminar el recurso
- [ ] Tarea `@Async` no bloquea el hilo HTTP principal (verificar con logs de thread)
- [ ] `@Scheduled` ejecuta tarea recurrente (cada minuto en dev)
- [ ] Endpoint WebSocket funcional: múltiples clientes reciben el mismo mensaje

---

## 🔗 Navegación

| | |
|---|---|
| ⬅️ Anterior | [Semana 13 — Integration Tests y Testcontainers](../week-13-integration_tests_testcontainers/README.md) |
| ➡️ Siguiente | [Semana 15 — Docker, CI/CD y Deployment](../week-15-docker_cicd_deployment/README.md) |
