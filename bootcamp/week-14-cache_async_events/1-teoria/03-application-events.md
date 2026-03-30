# Application Events — Desacoplamiento con ApplicationEventPublisher

## ¿Qué son los Application Events?

Los eventos de aplicación permiten que un componente notifique que algo ocurrió **sin necesitar una referencia directa** al componente que debe reaccionar. Implementan el patrón Observer desacoplado.

```
Sin eventos (acoplado):
  UserService → EmailService.send()
  UserService → AuditService.log()
  UserService → SlackService.notify()
  ← UserService conoce todos los servicios

Con eventos (desacoplado):
  UserService → publica UserCreatedEvent
                    ← EmailService escucha y envía email
                    ← AuditService escucha y registra
                    ← SlackService escucha opcionalmente
  ← UserService NO conoce ningún listener
```

## Implementación

### 1. Definir el Evento

```java
// Un record es ideal: inmutable y conciso
public record UserCreatedEvent(Long userId, String email, String name) {}

// Alternative: heredar de ApplicationEvent (para info de timestamp, source)
public class OrderShippedEvent extends ApplicationEvent {
    private final Long orderId;

    public OrderShippedEvent(Object source, Long orderId) {
        super(source);
        this.orderId = orderId;
    }

    public Long orderId() { return orderId; }
}
```

### 2. Publicar el Evento

```java
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher; // ← inyectar

    public UserResponse createUser(UserCreateRequest request) {
        var user = userRepository.save(toEntity(request));

        // Publicar evento — UserService no sabe quién escucha
        eventPublisher.publishEvent(new UserCreatedEvent(user.getId(),
                                                          user.getEmail(),
                                                          user.getName()));
        return toResponse(user);
    }
}
```

### 3. Escuchar el Evento

```java
@Component
@Slf4j
public class UserEventListener {

    // Síncrono por defecto (mismo thread, misma transacción)
    @EventListener
    public void onUserCreated(UserCreatedEvent event) {
        log.info("Usuario creado: {} <{}>", event.name(), event.email());
        // lógica de bienvenida, auditoría, etc.
    }
}

@Component
@Slf4j
public class EmailNotificationListener {

    private final EmailService emailService;

    // Asíncrono — no bloquea la transacción principal
    @Async
    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        emailService.sendWelcomeEmail(event.email(), event.name());
    }
}
```

## `@TransactionalEventListener` — Eventos Transaccionales

Problema: si se publica un evento dentro de una transacción y el listener actúa inmediatamente, la transacción aún no se ha confirmado (commit). El listener podría ver datos inconsistentes.

```java
@Component
public class AuditListener {

    // Escucha DESPUÉS del commit de la transacción (más seguro)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onUserCreated(UserCreatedEvent event) {
        auditRepository.save(new AuditLog("USER_CREATED", event.userId()));
    }

    // Fases disponibles:
    // BEFORE_COMMIT      — antes del commit (puede afectar la transacción)
    // AFTER_COMMIT       — después del commit exitoso (default recomendado)
    // AFTER_ROLLBACK     — si la transacción fue revertida
    // AFTER_COMPLETION   — siempre, sin importar resultado
}
```

## Múltiples Listeners y Orden

```java
@Component
public class MultipleListenersExample {

    @EventListener
    @Order(1) // ← ejecutar primero
    public void auditFirst(UserCreatedEvent event) { ... }

    @EventListener
    @Order(2)
    public void sendEmailSecond(UserCreatedEvent event) { ... }

    @EventListener
    @Order(3)
    public void notifySlackThird(UserCreatedEvent event) { ... }
}
```

## Comparativa: Eventos vs Llamada Directa

| Criterio | Llamada directa | Eventos |
|----------|----------------|---------|
| Acoplamiento | Alto — conoce todos los receptores | Bajo — solo publica |
| Testabilidad | Testear todos los colaboradores | Testear publisher y listener por separado |
| Agregar funcionalidad | Modificar el service | Agregar nuevo listener |
| Orden de ejecución | Explícito | Requiere `@Order` |
| Transacciones | Comparte transacción | Configurable con `@TransactionalEventListener` |

## ✅ Checklist de Verificación

- [ ] El evento es un record inmutable o hereda de `ApplicationEvent`
- [ ] El publisher inyecta `ApplicationEventPublisher` (no implementación concreta)
- [ ] Para emails y side effects lentos: `@Async @EventListener`
- [ ] Para garantías transaccionales: `@TransactionalEventListener(AFTER_COMMIT)`
- [ ] Los listeners se testean de forma INDEPENDIENTE al publisher
