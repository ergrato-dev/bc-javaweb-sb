package com.bootcamp.taskapi.controller;

import com.bootcamp.taskapi.dto.AuthDtos.AuthResponse;
import com.bootcamp.taskapi.dto.AuthDtos.LoginRequest;
import com.bootcamp.taskapi.dto.AuthDtos.RegisterRequest;
import com.bootcamp.taskapi.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints de autenticación — públicos (sin token requerido).
 * Configurados como permitAll() en SecurityConfig.
 */
@Tag(name = "Authentication", description = "Register and login endpoints")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // ============================================
    // TODO: Implementar endpoint de registro
    // ============================================
    // Delega al authService.register(req) y retorna 201 con el AuthResponse
    // que contiene el JWT.
    //
    @Operation(summary = "Register new user",
               description = "Creates a new user account and returns a JWT token")
    @ApiResponse(responseCode = "201", description = "User registered successfully")
    @ApiResponse(responseCode = "400", description = "Validation error or username/email already taken")
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid RegisterRequest req) {
        // TODO: llamar authService.register(req) y retornar ResponseEntity.status(201).body(...)
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // ============================================
    // TODO: Implementar endpoint de login
    // ============================================
    // Delega al authService.login(req) y retorna 200 con el AuthResponse.
    // Si las credenciales son incorrectas, Spring Security retorna 401 automáticamente.
    //
    @Operation(summary = "Login",
               description = "Authenticates user credentials and returns a JWT token")
    @ApiResponse(responseCode = "200", description = "Login successful")
    @ApiResponse(responseCode = "401", description = "Invalid credentials")
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest req) {
        // TODO: llamar authService.login(req) y retornar ResponseEntity.ok(...)
        return ResponseEntity.ok(null);
    }
}
