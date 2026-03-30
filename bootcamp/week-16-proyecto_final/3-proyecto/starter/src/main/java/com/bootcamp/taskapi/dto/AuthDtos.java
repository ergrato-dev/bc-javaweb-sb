package com.bootcamp.taskapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTOs para autenticación: registro, login y respuesta con JWT.
 * Todos son records inmutables — no tienen setters ni estado mutable.
 */
public class AuthDtos {

    /**
     * Datos requeridos para registrar un nuevo usuario.
     */
    public record RegisterRequest(
        @NotBlank @Size(min = 3, max = 50) String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, max = 100) String password
    ) {}

    /**
     * Datos requeridos para iniciar sesión.
     */
    public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password
    ) {}

    /**
     * Respuesta exitosa de autenticación.
     * Contiene el JWT y datos básicos del usuario para el cliente.
     */
    public record AuthResponse(
        String accessToken,
        String tokenType,
        String username,
        String role
    ) {
        // Constructor de conveniencia — tokenType siempre es "Bearer"
        public static AuthResponse of(String token, String username, String role) {
            return new AuthResponse(token, "Bearer", username, role);
        }
    }
}
