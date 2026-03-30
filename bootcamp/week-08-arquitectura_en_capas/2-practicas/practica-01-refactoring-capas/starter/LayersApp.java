package com.bootcamp;

// ============================================================
// LayersApp.java — Práctica 01: Refactoring a arquitectura en capas
// Descomenta cada PASO en orden para ver la transformación
// ============================================================

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class LayersApp {
    public static void main(String[] args) {
        SpringApplication.run(LayersApp.class, args);
    }
}

// ============================================================
// PASO 1: Dominio — Entidad JPA limpia
// ============================================================
// Solo persistencia — sin lógica HTTP ni de negocio.
// Descomenta las siguientes líneas:
//
// @Entity
// @Table(name = "tasks")
// class Task {
//     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
//
//     @Column(nullable = false, unique = true)
//     private String title;
//
//     @Column
//     private String description;
//
//     @Column(nullable = false)
//     private boolean completed = false;
//
//     @Column(name = "created_at")
//     private LocalDateTime createdAt;
//
//     protected Task() {}
//
//     Task(String title, String description) {
//         this.title = title;
//         this.description = description;
//         this.createdAt = LocalDateTime.now();
//     }
//
//     public Long getId() { return id; }
//     public String getTitle() { return title; }
//     public String getDescription() { return description; }
//     public boolean isCompleted() { return completed; }
//     public LocalDateTime getCreatedAt() { return createdAt; }
//     public void complete() { this.completed = true; }
// }

// ============================================================
// PASO 2: Repository — Solo acceso a datos
// ============================================================
// Derived queries — Spring Data genera el SQL automáticamente.
// Descomenta las siguientes líneas:
//
// interface TaskRepository extends JpaRepository<Task, Long> {
//     boolean existsByTitle(String title);
//     List<Task> findByCompleted(boolean completed);
// }

// ============================================================
// PASO 3: Service — Toda la lógica de negocio
// ============================================================
// El Service coordina: valida, crea, persiste, mapea.
// Descomenta las siguientes líneas:
//
// @Service
// @Transactional(readOnly = true)
// class TaskService {
//
//     private final TaskRepository taskRepository;
//
//     TaskService(TaskRepository taskRepository) {
//         this.taskRepository = taskRepository;
//     }
//
//     public List<TaskResponse> findAll() {
//         return taskRepository.findAll().stream().map(this::toResponse).toList();
//     }
//
//     @Transactional
//     public TaskResponse create(TaskCreateRequest request) {
//         if (taskRepository.existsByTitle(request.title())) {
//             throw new DuplicateTitleException(request.title());
//         }
//         var saved = taskRepository.save(new Task(request.title(), request.description()));
//         return toResponse(saved);
//     }
//
//     @Transactional
//     public TaskResponse complete(Long id) {
//         var task = taskRepository.findById(id)
//             .orElseThrow(() -> new TaskNotFoundException(id));
//         task.complete();
//         return toResponse(task);
//     }
//
//     private TaskResponse toResponse(Task t) {
//         return new TaskResponse(t.getId(), t.getTitle(), t.getDescription(), t.isCompleted(), t.getCreatedAt());
//     }
// }
//
// class TaskNotFoundException extends RuntimeException {
//     TaskNotFoundException(Long id) { super("Task not found: " + id); }
// }
// class DuplicateTitleException extends RuntimeException {
//     DuplicateTitleException(String title) { super("Task already exists: " + title); }
// }

// ============================================================
// PASO 4: Controller limpio — solo HTTP + delegación
// ============================================================
// Sin lógica de negocio, sin acceso a repositories.
// Descomenta las siguientes líneas:
//
// @RestController
// @RequestMapping("/api/tasks")
// class TaskController {
//
//     private final TaskService taskService;
//
//     TaskController(TaskService taskService) {
//         this.taskService = taskService;
//     }
//
//     @GetMapping
//     public ResponseEntity<List<TaskResponse>> findAll() {
//         return ResponseEntity.ok(taskService.findAll());
//     }
//
//     @PostMapping
//     public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskCreateRequest request) {
//         return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(request));
//     }
//
//     @PatchMapping("/{id}/complete")
//     public ResponseEntity<TaskResponse> complete(@PathVariable Long id) {
//         return ResponseEntity.ok(taskService.complete(id));
//     }
//
//     @ExceptionHandler(TaskNotFoundException.class)
//     ResponseEntity<String> handleNotFound(TaskNotFoundException ex) {
//         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
//     }
//
//     @ExceptionHandler(DuplicateTitleException.class)
//     ResponseEntity<String> handleDuplicate(DuplicateTitleException ex) {
//         return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
//     }
// }

// ============================================================
// PASO 5: DTOs — Records inmutables para la API
// ============================================================
// Descomenta las siguientes líneas:
//
// record TaskCreateRequest(
//     @NotBlank(message = "Title is required") String title,
//     String description
// ) {}
//
// record TaskResponse(Long id, String title, String description,
//                     boolean completed, LocalDateTime createdAt) {}
