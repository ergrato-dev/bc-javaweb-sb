# @Async y CompletableFuture en Spring

## ¿Por Qué Operaciones Asíncronas?

Algunas operaciones son lentas pero no necesitan bloquear la respuesta al cliente: envío de emails, procesamiento de imágenes, notificaciones push, generación de reportes, llamadas a APIs externas.

```
Sin @Async:
  Request → Service → Email (2s) → DB (50ms) → Response   (2050ms total)

Con @Async:
  Request → DB (50ms) → Response   (50ms)
                └─────→ Email en background (2s, el cliente ya recibió respuesta)
```

## Configuración

```xml
<!-- pom.xml — no necesita dependencia extra, incluido en spring-context -->
```

```java
// Habilitar soporte asíncrono
@SpringBootApplication
@EnableAsync  // ← activa el procesamiento asíncrono
public class Application { ... }
```

```java
// Configurar el ThreadPool (recomendado en producción)
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        var executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("async-");
        executor.initialize();
        return executor;
    }
}
```

## `@Async` Básico — Fire and Forget

```java
@Service
public class NotificationService {

    // Se ejecuta en un thread separado — el llamante no espera
    @Async
    public void sendWelcomeEmail(String email, String name) {
        log.info("Enviando email a {} en thread: {}", email,
                 Thread.currentThread().getName()); // async-1, async-2, etc.
        // simulación de email lento
        Thread.sleep(2000);
        log.info("Email enviado a {}", email);
    }
}

// En el controller → respuesta inmediata
@PostMapping("/users")
public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserCreateRequest req) {
    var user = userService.create(req);
    notificationService.sendWelcomeEmail(user.email(), user.name()); // no bloquea
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
}
```

## `@Async` con `CompletableFuture` — Para Resultados Futuros

```java
@Service
public class ReportService {

    @Async
    public CompletableFuture<ReportResponse> generateSalesReport(int year) {
        log.info("Generando reporte del año {} en thread: {}", year,
                 Thread.currentThread().getName());
        var data = reportRepository.findSalesByYear(year); // costoso
        var report = buildReport(data);
        return CompletableFuture.completedFuture(report);
    }
}

// En el controller — esperar el resultado
@GetMapping("/reports/sales/{year}")
public ResponseEntity<ReportResponse> getSalesReport(@PathVariable int year) {
    CompletableFuture<ReportResponse> future = reportService.generateSalesReport(year);
    // .get() bloquea hasta que el future complete (con timeout recomendado)
    var report = future.get(30, TimeUnit.SECONDS);
    return ResponseEntity.ok(report);
}
```

## `@Scheduled` — Tareas Programadas

```java
@SpringBootApplication
@EnableScheduling  // ← habilitar scheduling
public class Application { ... }

@Component
public class ScheduledTasks {

    // Cada 60 segundos desde el inicio de la aplicación
    @Scheduled(fixedDelay = 60_000)
    public void cleanExpiredSessions() {
        log.info("Limpiando sesiones expiradas...");
        sessionRepository.deleteByExpiresAtBefore(LocalDateTime.now());
    }

    // Cada 30 segundos independiente del tiempo de ejecución
    @Scheduled(fixedRate = 30_000)
    public void syncPrices() {
        log.info("Sincronizando precios...");
        priceService.syncWithExternalApi();
    }

    // Expresión cron: lunes a viernes a las 9:00 AM
    @Scheduled(cron = "0 0 9 * * MON-FRI", zone = "America/Mexico_City")
    public void dailyReport() {
        log.info("Generando reporte diario...");
        reportService.generateAndEmail();
    }
}
```

### Formato Cron

```
┌──────────── segundo (0-59)
│ ┌────────── minuto (0-59)
│ │ ┌──────── hora (0-23)
│ │ │ ┌────── día del mes (1-31)
│ │ │ │ ┌──── mes (1-12)
│ │ │ │ │ ┌── día de la semana (0-7, 0=Dom)
│ │ │ │ │ │
0 0 9 * * MON-FRI
```

## Restricciones de `@Async`

```java
// ❌ NO funciona con self-invocation (mismo problema que @Cacheable)
@Service
public class MyService {
    public void method1() {
        this.asyncMethod(); // ← el proxy no intercepta
    }

    @Async
    public void asyncMethod() { ... }
}

// ❌ NO funciona en métodos privados (Spring necesita crear el proxy)
@Async
private void privateMethod() { ... } // ← ignorado silenciosamente

// ✅ Solo en métodos public de beans Spring inyectados desde fuera
```

## ✅ Checklist de Verificación

- [ ] `@EnableAsync` en la clase principal o en `@Configuration`
- [ ] `@EnableScheduling` en la clase principal si se usan `@Scheduled`
- [ ] Manejar excepciones: configurar `AsyncUncaughtExceptionHandler`
- [ ] No usar `@Async` con `@Transactional` en el mismo método (transacciones no cruzan threads)
- [ ] En producción: configurar `ThreadPoolTaskExecutor` con límites razonables
