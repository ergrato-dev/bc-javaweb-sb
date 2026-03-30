# Proyecto Semana 14 — Notifications API (Cache + Async + Events)

## 🎯 Objetivo

Implementar las funcionalidades de caché, notificaciones asíncronas y eventos de dominio en la **Notifications API** (gestión de artículos de blog), y verificarlas con tests unitarios.

---

## 📦 Dominio — Articles API

| Estado | Descripción |
|--------|-------------|
| DRAFT | Recién creado, puede editarse |
| PUBLISHED | Publicado, visible al público |
| ARCHIVED | Archivado, no modificable |

**Flujo de estados:** `DRAFT → PUBLISHED → ARCHIVED`

---

## 🗂️ Tu Tarea

El código de producción del `ArticleService` y `ArticleController` está **completo**. Tu tarea:

### 1. `NotificationListener.java` — Implementar los 3 métodos

```
src/main/java/com/bootcamp/notifications/service/NotificationListener.java
```

- `onArticleCreated(ArticleCreatedEvent)` — `@Async @EventListener`, simular envío de email
- `onArticlePublished(ArticlePublishedEvent)` — `@Async @EventListener`, simular notificación
- `scheduledCleanup()` — `@Scheduled(fixedRate = 60_000)`, log de tarea periódica

### 2. `ArticleServiceTest.java` — Implementar los 7 tests

```
src/test/java/com/bootcamp/notifications/ArticleServiceTest.java
```

Tests detallados con TODOs en el archivo.

### 3. `NotificationListenerTest.java` — Implementar los 2 tests (smoke tests)

```
src/test/java/com/bootcamp/notifications/NotificationListenerTest.java
```

---

## 🚀 Cómo Ejecutar

```bash
# Levantar la API (usa H2 en memoria, no necesita Docker)
./mvnw spring-boot:run

# Explorar la API en Swagger
open http://localhost:8080/swagger-ui.html

# Consola H2
open http://localhost:8080/h2-console
# URL: jdbc:h2:mem:notificationsdb  /  User: sa  /  Password: (vacío)

# Ejecutar tests
./mvnw test

# Reporte de cobertura
./mvnw test jacoco:report && open target/site/jacoco/index.html
```

---

## 📁 Estructura

```
starter/
├── src/main/java/com/bootcamp/notifications/
│   ├── NotificationsApiApplication.java  ← @EnableCaching @EnableAsync @EnableScheduling
│   ├── config/AppConfig.java             ← ThreadPool + Caffeine CacheManager
│   ├── controller/ArticleController.java ← endpoints REST (ya implementado)
│   ├── service/
│   │   ├── ArticleService.java           ← @Cacheable @CacheEvict @CachePut (ya implementado)
│   │   └── NotificationListener.java     ← 🎯 TU TAREA (listeners + @Scheduled)
│   ├── event/
│   │   ├── ArticleCreatedEvent.java      ← record
│   │   └── ArticlePublishedEvent.java    ← record
│   ├── domain/Article.java              ← entidad JPA
│   ├── repository/ArticleRepository.java
│   ├── dto/Dtos.java                    ← records DTO
│   └── exception/                       ← ya implementado
└── src/test/java/com/bootcamp/notifications/
    ├── ArticleServiceTest.java           ← 🎯 TU TAREA (7 tests)
    └── NotificationListenerTest.java     ← 🎯 TU TAREA (2 smoke tests)
```

---

## 🔑 Puntos Clave

```java
// @CachePut — SIEMPRE ejecuta, actualiza el caché
// @Cacheable — ejecuta solo en cache miss, retorna caché en hit
// @CacheEvict — elimina la entrada del caché

// Para verificar el caché: llama findById(1L) dos veces y verifica
// que el repositorio fue llamado UNA SOLA VEZ (la segunda fue hit)
verify(articleRepository, times(1)).findById(1L);
```

---

## 📊 Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| `NotificationListener` — 3 métodos implementados correctamente | 30 pts |
| `ArticleServiceTest` — 7 tests pasando | 50 pts |
| `NotificationListenerTest` — 2 smoke tests pasando | 10 pts |
| Logs muestran threads async-N para eventos y scheduling-N para tarea | 10 pts |
| **Total** | **100 pts** |
