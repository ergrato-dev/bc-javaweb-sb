package com.bootcamp.service;

import com.bootcamp.domain.AppUser;
import com.bootcamp.domain.Role;
import com.bootcamp.dto.RegisterRequest;
import com.bootcamp.dto.UserResponse;
import com.bootcamp.exception.UserNotFoundException;
import com.bootcamp.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * TODO: Implementar lógica de registro
     * 1. Verificar que el username no esté tomado (existsByUsername)
     * 2. Si ya existe → lanzar IllegalArgumentException("Username already taken")
     * 3. Crear AppUser con username, password hasheado con BCrypt y role
     * 4. Si role es null → asignar Role.ROLE_USER por defecto
     * 5. Guardar en userRepository.save() y retornar toResponse()
     */
    public UserResponse register(RegisterRequest request) {
        // TODO: Implementar
        return null;
    }

    /**
     * Obtiene todos los usuarios paginados (solo ADMIN).
     *
     * TODO: Implementar
     * 1. Llamar userRepository.findAll(pageable)
     * 2. Mapear cada AppUser a UserResponse usando toResponse()
     */
    @Transactional(readOnly = true)
    public Page<UserResponse> findAll(Pageable pageable) {
        // TODO: Implementar
        return Page.empty();
    }

    /**
     * Obtiene el perfil de un usuario por username.
     *
     * TODO: Implementar
     * 1. Llamar userRepository.findByUsername(username)
     * 2. Si no existe → lanzar UserNotFoundException(username)
     * 3. Retornar toResponse()
     */
    @Transactional(readOnly = true)
    public UserResponse findByUsername(String username) {
        // TODO: Implementar
        return null;
    }

    /**
     * Desactiva (soft delete) un usuario por ID (solo ADMIN).
     *
     * TODO: Implementar
     * 1. Buscar por userRepository.findById(id)
     * 2. Si no existe → lanzar UserNotFoundException
     * 3. Llamar user.setActive(false)
     * 4. Guardar con userRepository.save()
     */
    public void deactivate(Long id) {
        // TODO: Implementar
    }

    private UserResponse toResponse(AppUser user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.isActive(),
                user.getCreatedAt()
        );
    }
}
