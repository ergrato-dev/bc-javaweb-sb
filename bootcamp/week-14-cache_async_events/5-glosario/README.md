# Glosario — Semana 14: Cache, Async y Events

## A

**`ApplicationEventPublisher`**
Interface de Spring que permite publicar eventos de aplicación. Se inyecta como dependencia y desacopla al publicador de los listeners.

```java
eventPublisher.publishEvent(new UserCreatedEvent(user.getId(), user.getEmail()));
```

**`@Async`**
Anotación de Spring que ejecuta el método en un thread del pool asíncrono (no en el thread del llamante). Requiere `@EnableAsync`. Solo funciona en métodos `public` de beans Spring inyectados desde fuera.

---

## C

**Caffeine**
Librería de cache en memoria de alto rendimiento para Java. Es el cache backend recomendado para desarrollo en Spring Boot (alternativa más rápida a Guava). Soporta TTL, tamaño máximo y estadísticas.

**`@Cacheable`**
En un cache hit (clave ya existe), devuelve el valor cacheado sin ejecutar el método. En un cache miss, ejecuta el método y cachea el resultado.

**`@CacheEvict`**
Elimina una entrada (o todas) del cache al ejecutar el método. Usar en operaciones de escritura (`update`, `delete`) para mantener consistencia.

**`@CachePut`**
Siempre ejecuta el método y actualiza el cache con el resultado. A diferencia de `@Cacheable`, no omite la ejecución.

**`CompletableFuture<T>`**
Clase de Java que representa un cómputo asíncrono que eventualmente producirá un resultado. Usada con `@Async` para operaciones que retornan valores en el futuro.

---

## E

**`@EnableAsync`**
Anotación de clase que activa el soporte de `@Async` en Spring Boot. Se coloca en la clase principal o en una clase `@Configuration`.

**`@EnableCaching`**
Activa el soporte de caché en Spring Boot. Sin esta anotación, las anotaciones `@Cacheable`, `@CacheEvict` y `@CachePut` son ignoradas.

**`@EnableScheduling`**
Activa el soporte de `@Scheduled`. Sin esta anotación, las tareas programadas no se ejecutan.

**`@EventListener`**
Anotación de método que indica a Spring que el método debe ser invocado cuando se publique un evento del tipo indicado como parámetro.

---

## S

**`@Scheduled`**
Anotación para ejecutar un método periódicamente. Soporta `fixedRate`, `fixedDelay` (en ms) y expresiones cron.

```java
@Scheduled(cron = "0 0 8 * * MON-FRI") // cada día laborable a las 8 AM
public void dailyTask() { ... }
```

**Self-invocation problem**
Problema donde un bean llama a sus propios métodos anotados con `@Cacheable`, `@Async`, etc. El proxy de Spring no intercepta la llamada y las anotaciones son ignoradas. Solución: inyectar el bean como dependencia de sí mismo o trasladar el método a otro bean.

---

## T

**`ThreadPoolTaskExecutor`**
Implementación de `Executor` de Spring para configurar un pool de threads con `corePoolSize`, `maxPoolSize`, `queueCapacity` y nombre de threads.

**`@TransactionalEventListener`**
Variante de `@EventListener` que escucha eventos en una fase específica de la transacción (típicamente `AFTER_COMMIT`) para garantizar que los datos estén confirmados antes de reaccionar.
