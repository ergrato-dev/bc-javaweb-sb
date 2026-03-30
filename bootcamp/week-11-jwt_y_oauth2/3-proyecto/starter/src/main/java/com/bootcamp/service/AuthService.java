package com.bootcamp.service;

import com.bootcamp.domain.AppUser;
import com.bootcamp.domain.Role;
import com.bootcamp.dto.RegisterRequest;
import com.bootcamp.dto.UserResponse;
import com.bootcamp.exception.UserNotFoundException;
import com.bootcamp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra un nuevo usuario.
     *
     * TODO: Implementar
     * 1. Verificar que el username no esté tomado con existsByUsername()
     * 2. Si ya existe → lanzar IllegalArgumentException("Username already taken")
     * 3. Crear AppUser con password hasheado con BCrypt
     * 4. Role por defecto: Role.ROLE_USER si es null en el request
     * 5. Guardar y retornar toResponse()
     */
    public UserResponse register(RegisterRequest request) {
        // TODO: Implementar
        return null;
    }

    private UserResponse toResponse(AppUser user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
