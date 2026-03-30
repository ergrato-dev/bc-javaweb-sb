package com.bootcamp.controller;

import com.bootcamp.dto.RegisterRequest;
import com.bootcamp.dto.UserResponse;
import com.bootcamp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registrar un nuevo usuario — endpoint público.
     *
     * TODO: Implementar
     * - Llamar userService.register(request)
     * - Retornar 201 CREATED con el UserResponse
     */
    @PostMapping("/auth/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {
        // TODO: Implementar
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Obtener todos los usuarios — solo ADMIN.
     *
     * TODO: Implementar
     * - Anotar con @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     * - Llamar userService.findAll(pageable)
     * - Retornar 200 OK con la Page<UserResponse>
     */
    @GetMapping("/admin/users")
    public ResponseEntity<Page<UserResponse>> findAll(Pageable pageable) {
        // TODO: Implementar
        return ResponseEntity.ok(Page.empty());
    }

    /**
     * Obtener un usuario por username — solo ADMIN.
     *
     * TODO: Implementar
     * - Anotar con @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     * - Llamar userService.findByUsername(username)
     * - Retornar 200 OK con UserResponse
     */
    @GetMapping("/admin/users/{username}")
    public ResponseEntity<UserResponse> findByUsername(@PathVariable String username) {
        // TODO: Implementar
        return ResponseEntity.ok().build();
    }

    /**
     * Desactivar un usuario — solo ADMIN.
     *
     * TODO: Implementar
     * - Anotar con @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     * - Llamar userService.deactivate(id)
     * - Retornar 204 NO CONTENT
     */
    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        // TODO: Implementar
        return ResponseEntity.noContent().build();
    }
}
