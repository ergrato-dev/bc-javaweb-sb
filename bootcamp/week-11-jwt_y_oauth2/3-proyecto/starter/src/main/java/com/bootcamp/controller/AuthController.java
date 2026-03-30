package com.bootcamp.controller;

import com.bootcamp.dto.AuthResponse;
import com.bootcamp.dto.LoginRequest;
import com.bootcamp.dto.RegisterRequest;
import com.bootcamp.dto.UserResponse;
import com.bootcamp.security.JwtService;
import com.bootcamp.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthService authService,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * Registra un nuevo usuario.
     *
     * TODO: Implementar
     * - Llamar authService.register(request)
     * - Retornar 201 CREATED con UserResponse
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request) {
        // TODO: Implementar
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Autenticación con username + password → retorna JWT.
     *
     * TODO: Implementar
     * 1. Llamar authenticationManager.authenticate() con UsernamePasswordAuthenticationToken
     * 2. Si BadCredentialsException → ResponseStatusException(UNAUTHORIZED)
     * 3. Obtener userDetails del principal de la autenticación
     * 4. Generar el access token con jwtService.generateToken(userDetails)
     * 5. Retornar 200 OK con AuthResponse(accessToken, jwtService.getExpirationMs())
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest request) {
        // TODO: Implementar
        return ResponseEntity.ok().build();
    }
}
