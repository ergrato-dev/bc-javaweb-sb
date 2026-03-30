package com.bootcamp.security;

import com.bootcamp.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Implementación de UserDetailsService que carga usuarios desde la base de
 * datos.
 *
 * Spring Security llama a loadUserByUsername() automáticamente en cada request
 * autenticada para verificar las credenciales.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .map(user -> User.builder()
            .username(user.getUsername())
            // El password ya está hasheado con BCrypt en la DB
            // Spring Security usa passwordEncoder.matches() para verificar
            .password(user.getPassword())
            // El rol incluye el prefijo ROLE_ (ej: ROLE_USER, ROLE_ADMIN)
            .authorities(user.getRole().name())
            .accountExpired(false)
            .accountLocked(!user.isActive())
            .credentialsExpired(false)
            .disabled(!user.isActive())
            .build())
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }
}
