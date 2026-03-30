# Semana 14 — Cache, Async y Application Events

## 🎯 Objetivos de Aprendizaje

- ✅ Implementar caché con `@Cacheable`, `@CacheEvict` y `@CachePut` usando Caffeine
- ✅ Configurar un `ThreadPoolTaskExecutor` para tareas asíncronas con `@Async`
- ✅ Programar tareas periódicas con `@Scheduled` y expresiones cron
- ✅ Publicar eventos de dominio con `ApplicationEventPublisher`
- ✅ Escuchar eventos con `@EventListener` y `@TransactionalEventListener`
- ✅ Distinguir entre fire-and-forget (`@Async`) y resultados diferidos (`CompletableFuture`)

---

## 📚 Requisitos Previos

- Semana 8–9: Arquitectura en capas, Services, DTOs
- Semana 12: JUnit 5, Mockito, `ArgumentCaptor`

---

## 🗂️ Estructura

```
week-14-cache_async_events/
├── README.md
├── rubrica-evaluacion.md
├── 1-teoria/
│   ├── 01-spring-cache.md          ← @Cacheable, @CacheEvict, Caffeine, Redis
│   ├── 02-async-y-scheduled.md     ← @Async, CompletableFuture, @Scheduled, cron
│   └── 03-application-events.md    ← ApplicationEventPublisher, @EventListener, @TransactionalEventListener
├── 2-practicas/
│   ├── practica-01-cache/          ← Configurar y aplicar Spring Cache
│   └── practica-02-async-events/   ← @Async, @Scheduled, publicar y escuchar eventos
├── 3-proyecto/                     ← Notifications API
│   ├── README.md
│   └── starter/                    ← Maven project completo
└── 5-glosario/
    └── README.md
```

---

## 📝 Contenidos

| Archivo | Tema |
|---------|------|
| [01-spring-cache.md](1-teoria/01-spring-cache.md) | Spring Cache, Caffeine, anotaciones |
| [02-async-y-scheduled.md](1-teoria/02-async-y-scheduled.md) | @Async, CompletableFuture, @Scheduled |
| [03-application-events.md](1-teoria/03-application-events.md) | Eventos de dominio, desacoplamiento |

---

## ⏱️ Distribución del Tiempo

| Actividad | Tiempo |
|-----------|--------|
| Teoría: Spring Cache | 40 min |
| Teoría: @Async y @Scheduled | 40 min |
| Teoría: Application Events | 40 min |
| Práctica 1: Cache | 1h 45min |
| Práctica 2: Async + Events | 1h 45min |
| Proyecto: Notifications API | 2h 30min |

---

## 📌 Entregables

- [ ] `NotificationListener.java` con los 3 métodos implementados
- [ ] `ArticleServiceTest.java` con 7 tests pasando
- [ ] `NotificationListenerTest.java` con 2 smoke tests
- [ ] `mvn test` termina en `BUILD SUCCESS`
- [ ] Logs mostrando `thread: async-N` para operaciones asíncronas

---

## 🔗 Navegación

← [Semana 13 — Testcontainers](../week-13-testcontainers_e_integracion/README.md)
→ [Semana 15 — Docker y CI/CD](../week-15-docker_cicd/README.md)
