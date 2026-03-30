package com.bootcamp.asyncevents;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AsyncEventsApp — Demostración de @Async, @Scheduled y Application Events
 *
 * Descomenta cada sección en el orden indicado.
 */

// ============================================
// PASO 1: Habilitar @EnableAsync y @EnableScheduling
// ============================================
// Descomenta las siguientes anotaciones:

// @SpringBootApplication
// @EnableAsync // ← activa @Async en toda la aplicación
// @EnableScheduling // ← activa @Scheduled
@Slf4j
public class AsyncEventsApp {

  public static void main(String[] args) {
    SpringApplication.run(AsyncEventsApp.class, args);
  }
}

// ============================================
// PASO 1: Método asíncrono (fire and forget)
// ============================================
// Descomenta la clase NotificationService:

// @Service
// @Slf4j
// class NotificationService {
//
// @Async // ← se ejecuta en un thread del pool dedicado
// public void sendEmail(String to, String subject) {
// log.info("[Email] Enviando '{}' a {} en thread: {}",
// subject, to, Thread.currentThread().getName());
// try { Thread.sleep(2000); } catch (InterruptedException e) {}
// log.info("[Email] Enviado a {}", to);
// }
// }

// ============================================
// PASO 2: Tarea programada con @Scheduled
// ============================================
// Descomenta la clase CleanupTask:

// @Component
// @Slf4j
// class CleanupTask {
//
// // fixedRate: se ejecuta cada N milisegundos sin importar tiempo de ejecución
// @Scheduled(fixedRate = 10_000) // cada 10 segundos
// public void cleanExpiredSessions() {
// log.info("[Scheduled] Limpiando sesiones expiradas en thread: {}",
// Thread.currentThread().getName());
// }
//
// // cron: expresión estilo Unix para scheduling avanzado
// // @Scheduled(cron = "0 0 8 * * MON-FRI") // lunes a viernes a las 8 AM
// // public void dailyReport() { ... }
// }

// ============================================
// PASO 3: Evento de aplicación (record)
// ============================================
// Descomenta el record y el UserService:

// record UserRegisteredEvent(Long userId, String email, String name) {}

// @Service
// @RequiredArgsConstructor
// @Slf4j
// class UserService {
//
// private final ApplicationEventPublisher eventPublisher;
//
// public String createUser(String email, String name) {
// Long fakeId = System.currentTimeMillis(); // simular ID de BD
// log.info("Usuario creado para: {}", email);
// // Publicar evento — UserService no sabe quién escucha ni cuántos listeners
// hay
// eventPublisher.publishEvent(new UserRegisteredEvent(fakeId, email, name));
// return "User-" + fakeId;
// }
// }

// ============================================
// PASO 4: Listeners del evento
// ============================================
// Descomenta los dos listeners:

// @Component
// @Slf4j
// class EmailListener {
//
// @Async
// @EventListener // ← Spring llama a este método cuando se publica
// UserRegisteredEvent
// public void onUserRegistered(UserRegisteredEvent event) {
// log.info("[Email] Enviando bienvenida a {} en thread: {}",
// event.email(), Thread.currentThread().getName());
// }
// }

// @Component
// @Slf4j
// class AuditListener {
//
// @Async
// @EventListener
// public void onUserRegistered(UserRegisteredEvent event) {
// log.info("[Audit] Registrando creación de usuario {} en thread: {}",
// event.userId(), Thread.currentThread().getName());
// }
// }
