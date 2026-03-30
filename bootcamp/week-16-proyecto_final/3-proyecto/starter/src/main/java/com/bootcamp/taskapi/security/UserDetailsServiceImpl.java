package com.bootcamp.taskapi.security;

import com.bootcamp.taskapi.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación de UserDetailsService que carga usuarios desde PostgreSQL.
 *
 * Spring Security invoca este servicio durante la autenticación para cargar
 * el usuario y comparar la contraseña proporcionada con el hash almacenado.
 *
 * El rol se mapea a una GrantedAuthority con el prefijo "ROLE_" que Spring
 * Security requiere para @PreAuthorize("hasRole('ADMIN')").
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ============================================
    // TODO: Implementar loadUserByUsername
    // ============================================
    // 1. Buscar el usuario por username con userRepository.findByUsername(username)
    // 2. Si no existe, lanzar new UsernameNotFoundException("User not found: " + username)
    // 3. Retornar User.builder() con:
    //    .username(appUser.getUsername())
    //    .password(appUser.getPassword())   // ya está hasheado
    //    .authorities(List.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name())))
    //    .build()
    //
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: implementar
        throw new UsernameNotFoundException("Not implemented yet");
    }
}
