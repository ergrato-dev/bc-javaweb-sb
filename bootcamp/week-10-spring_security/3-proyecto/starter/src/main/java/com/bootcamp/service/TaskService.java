package com.bootcamp.service;

import com.bootcamp.domain.Task;
import com.bootcamp.domain.TaskStatus;
import com.bootcamp.dto.TaskCreateRequest;
import com.bootcamp.dto.TaskResponse;
import com.bootcamp.dto.TaskUpdateRequest;
import com.bootcamp.exception.TaskNotFoundException;
import com.bootcamp.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Lista todas las tareas del usuario autenticado (paginadas).
     *
     * TODO: Implementar
     * 1. Llamar taskRepository.findByOwnerUsername(ownerUsername, pageable)
     * 2. Mapear cada Task a TaskResponse usando toResponse()
     */
    @Transactional(readOnly = true)
    public Page<TaskResponse> findMyTasks(String ownerUsername, Pageable pageable) {
        // TODO: Implementar
        return Page.empty();
    }

    /**
     * Lista todas las tareas del sistema (solo ADMIN).
     *
     * TODO: Implementar
     * 1. Llamar taskRepository.findAll(pageable)
     * 2. Mapear cada Task a TaskResponse
     */
    @Transactional(readOnly = true)
    public Page<TaskResponse> findAll(Pageable pageable) {
        // TODO: Implementar
        return Page.empty();
    }

    /**
     * Obtiene una tarea por ID. El usuario solo puede ver sus propias tareas;
     * el admin puede ver cualquier tarea.
     *
     * TODO: Implementar
     * 1. Si isAdmin → buscar con taskRepository.findById(id)
     * 2. Si no es admin → buscar con taskRepository.findByIdAndOwnerUsername(id, ownerUsername)
     * 3. Si no existe → lanzar TaskNotFoundException(id)
     * 4. Retornar toResponse()
     */
    @Transactional(readOnly = true)
    public TaskResponse findById(Long id, String ownerUsername, boolean isAdmin) {
        // TODO: Implementar
        return null;
    }

    /**
     * Crea una nueva tarea para el usuario autenticado.
     *
     * TODO: Implementar
     * 1. Crear Task con title, description y ownerUsername
     * 2. Guardar con taskRepository.save()
     * 3. Retornar toResponse()
     */
    public TaskResponse create(TaskCreateRequest request, String ownerUsername) {
        // TODO: Implementar
        return null;
    }

    /**
     * Actualiza una tarea. El usuario solo puede editar sus propias tareas.
     *
     * TODO: Implementar
     * 1. Buscar la tarea con findByIdAndOwnerUsername (o findById si admin)
     * 2. Si no existe → TaskNotFoundException
     * 3. Actualizar con los campos no nulos del request (title, description, status)
     * 4. Guardar y retornar toResponse()
     */
    public TaskResponse update(Long id, TaskUpdateRequest request,
                               String ownerUsername, boolean isAdmin) {
        // TODO: Implementar
        return null;
    }

    /**
     * Elimina una tarea. Solo el admin puede eliminar tareas.
     *
     * TODO: Implementar
     * 1. Verificar que la tarea existe con taskRepository.findById(id)
     * 2. Si no existe → TaskNotFoundException
     * 3. Eliminar con taskRepository.delete()
     */
    public void delete(Long id) {
        // TODO: Implementar
    }

    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getOwnerUsername(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
    }
}
