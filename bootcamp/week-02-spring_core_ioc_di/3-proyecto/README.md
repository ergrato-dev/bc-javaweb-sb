# Proyecto Semana 02 — Sistema de Notificaciones con IoC/DI

## 🎯 Descripción

Construye un sistema de notificaciones multi-canal usando el container IoC de Spring. La lógica de negocio debe estar completamente desacoplada del mecanismo de entrega.

## 📋 Escenario

Una plataforma necesita enviar notificaciones a usuarios por distintos canales (email, SMS, push). La implementación concreta del canal puede cambiar sin afectar el código de negocio.

## 🏗️ Interfaces del Dominio

```java
// Puerto de salida — definido en negocio, implementado en infraestructura
public interface NotificationSender {
    void send(String recipient, String subject, String body);
    String getChannel(); // "EMAIL", "SMS", "PUSH"
}

// Servicio de negocio
public interface NotificationService {
    void notifyUser(Long userId, String event, String message);
    void notifyAll(List<Long> userIds, String event, String message);
}
```

## 📌 Requerimientos

### Funcionales
- [ ] **R1:** Al menos 2 implementaciones de `NotificationSender`: `EmailSender` y `SmsSender`
- [ ] **R2:** `NotificationServiceImpl` inyecta lista de `NotificationSender` con `List<NotificationSender>`
- [ ] **R3:** Seleccionar canal por defecto basado en preferencia del usuario
- [ ] **R4:** `NotificationLog` — bean singleton que registra todas las notificaciones enviadas
- [ ] **R5:** `@PostConstruct` en `NotificationServiceImpl` para log de canales disponibles
- [ ] **R6:** `@Configuration` con `@Bean` para un `NotificationFormatter` (clase de utilidad externa)

### Técnicos
- [ ] Inyección exclusivamente por constructor (sin `@Autowired` en fields)
- [ ] Dependencias con `final`
- [ ] `NotificationService` inyectada como interfaz (no como `NotificationServiceImpl`)
- [ ] `NotificationLog` con scope singleton y estado compartido
- [ ] Demo en `CommandLineRunner` que envía 3 notificaciones al arrancar

## 📂 Estructura Sugerida

```
src/main/java/com/bootcamp/
├── config/
│   └── NotificationConfig.java      (@Configuration)
├── service/
│   ├── NotificationService.java     (interfaz)
│   └── NotificationServiceImpl.java (@Service)
├── sender/
│   ├── NotificationSender.java      (interfaz)
│   ├── EmailSender.java             (@Component)
│   └── SmsSender.java               (@Component)
└── model/
    └── NotificationLog.java         (@Component singleton)
```

## ✅ Criterios de Evaluación

| Criterio | Puntos |
|----------|--------|
| R1-R6 implementados y funcionando | 50 |
| Inyección por constructor en todos los beans | 20 |
| Sin `new ServiceImpl()` manual | 10 |
| Aplicación arranca sin errores de DI | 10 |
| `@PostConstruct` y `@Bean` usados | 10 |
| **Total** | **100** |
