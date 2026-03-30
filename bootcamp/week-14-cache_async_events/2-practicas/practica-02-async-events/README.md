# Práctica 02 — @Async, @Scheduled y Application Events

## Objetivos

- Ejecutar tareas en threads separados con `@Async`
- Programar tareas periódicas con `@Scheduled`
- Publicar y escuchar eventos de aplicación con `ApplicationEventPublisher` y `@EventListener`

## Instrucciones

### Paso 1: Tarea asíncrona con @Async

Abre `starter/AsyncEventsApp.java` y descomenta la sección **PASO 1**.

Al llamar `notificationService.sendEmail(...)`, el método retorna inmediatamente. El email se "envía" en background. Observa los nombres de threads en los logs: el main usa `http-nio-*`, el async usa `async-*`.

### Paso 2: Tarea programada con @Scheduled

Descomenta la sección **PASO 2**. La tarea se ejecuta cada 10 segundos.

Formato cron explicado:
```
0 * * * * *   ← cada minuto (segundo 0)
*/10 * * * * * ← cada 10 segundos
0 0 8 * * *   ← cada día a las 8 AM
```

### Paso 3: Publicar un evento con ApplicationEventPublisher

Descomenta la sección **PASO 3**. El `UserService` publica un `UserRegisteredEvent` al crear un usuario. NO conoce quién escucha.

### Paso 4: Escuchar eventos con @EventListener

Descomenta la sección **PASO 4**. El `AuditListener` y el `EmailListener` reaccionan al mismo evento de forma independiente.

Observa que agregar un nuevo listener NO requiere modificar `UserService`.

## Verificación

```
[http-nio-8080  ] INFO  - Usuario creado para: user@example.com
[async-1        ] INFO  - [Email] Enviando bienvenida a user@example.com
[async-2        ] INFO  - [Audit] Registrando creación de usuario 1
[scheduling-1   ] INFO  - [Scheduled] Tarea de limpieza ejecutada
```
