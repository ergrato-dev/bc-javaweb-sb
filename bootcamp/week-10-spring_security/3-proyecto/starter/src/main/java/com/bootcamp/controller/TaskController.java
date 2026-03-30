package com.bootcamp.controller;

import com.bootcamp.dto.TaskCreateRequest;
import com.bootcamp.dto.TaskResponse;
import com.bootcamp.dto.TaskUpdateRequest;
import com.bootcamp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Listar mis tareas — cualquier usuario autenticado.
     *
     * TODO: Implementar
     * - Obtener el username del userDetails.getUsername()
     * - Verificar si es admin con hasAuthority check
     * - Si admin → llamar taskService.findAll(pageable)
     * - Si user → llamar taskService.findMyTasks(username, pageable)
     * - Retornar 200 OK
     *
     * Pista: usar @AuthenticationPrincipal UserDetails userDetails para obtener el usuario actual
     */
    @GetMapping
    public ResponseEntity<Page<TaskResponse>> findTasks(
            @AuthenticationPrincipal UserDetails userDetails,
            Pageable pageable) {
        // TODO: Implementar
        return ResponseEntity.ok(Page.empty());
    }

    /**
     * Obtener una tarea por ID.
     *
     * TODO: Implementar
     * - Obtener username del userDetails
     * - Verificar si es admin
     * - Llamar taskService.findById(id, username, isAdmin)
     * - Retornar 200 OK con TaskResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> findById(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        // TODO: Implementar
        return ResponseEntity.ok().build();
    }

    /**
     * Crear una nueva tarea para el usuario autenticado.
     *
     * TODO: Implementar
     * - Obtener username del userDetails
     * - Llamar taskService.create(request, username)
     * - Retornar 201 CREATED con TaskResponse
     */
    @PostMapping
    public ResponseEntity<TaskResponse> create(
            @Valid @RequestBody TaskCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        // TODO: Implementar
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Actualizar una tarea (el dueño o admin).
     *
     * TODO: Implementar
     * - Obtener username y verificar si es admin
     * - Llamar taskService.update(id, request, username, isAdmin)
     * - Retornar 200 OK con TaskResponse actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody TaskUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {
        // TODO: Implementar
        return ResponseEntity.ok().build();
    }

    /**
     * Eliminar una tarea — solo ADMIN.
     *
     * TODO: Implementar
     * - Anotar con @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     * - Llamar taskService.delete(id)
     * - Retornar 204 NO CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // TODO: Implementar
        return ResponseEntity.noContent().build();
    }
}
