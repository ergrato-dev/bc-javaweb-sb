package com.bootcamp.taskapi.service;

import com.bootcamp.taskapi.domain.AppUser;
import com.bootcamp.taskapi.domain.Role;
import com.bootcamp.taskapi.dto.AuthDtos.AuthResponse;
import com.bootcamp.taskapi.dto.AuthDtos.LoginRequest;
import com.bootcamp.taskapi.dto.AuthDtos.RegisterRequest;
import com.bootcamp.taskapi.repository.UserRepository;
import com.bootcamp.taskapi.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio de autenticación.
 * Maneja registro y login — genera JWTs al autenticar exitosamente.
 */
@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;

  public AuthService(UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      JwtService jwtService,
      AuthenticationManager authenticationManager,
      UserDetailsService userDetailsService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
  }

  // ============================================
  // TODO: Implementar register
  // ============================================
  // 1. Verificar que username no exista:
  // userRepository.existsByUsername(req.username())
  // Si existe, lanzar new IllegalArgumentException("Username already taken")
  //
  // 2. Verificar que email no exista: userRepository.existsByEmail(req.email())
  // Si existe, lanzar new IllegalArgumentException("Email already registered")
  //
  // 3. Crear y guardar el usuario:
  // var user = new AppUser(req.username(), req.email(),
  // passwordEncoder.encode(req.password()), Role.USER);
  // userRepository.save(user);
  //
  // 4. Cargar UserDetails y generar JWT:
  // var userDetails = userDetailsService.loadUserByUsername(req.username());
  // String token = jwtService.generateToken(userDetails);
  // return AuthResponse.of(token, user.getUsername(), user.getRole().name());
  //
  @Transactional
  public AuthResponse register(RegisterRequest req) {
    // TODO: implementar
    return null;
  }

  // ============================================
  // TODO: Implementar login
  // ============================================
  // 1. Autenticar con AuthenticationManager (lanza excepción si falla):
  // authenticationManager.authenticate(
  // new UsernamePasswordAuthenticationToken(req.username(), req.password())
  // );
  // Si el password es incorrecto, AuthenticationManager lanza
  // BadCredentialsException
  // que Spring Security convierte automáticamente en 401.
  //
  // 2. Cargar UserDetails y generar JWT:
  // var userDetails = userDetailsService.loadUserByUsername(req.username());
  // String token = jwtService.generateToken(userDetails);
  //
  // 3. Retornar AuthResponse.of(token, username, role)
  // Para el role, puedes usar:
  // userDetails.getAuthorities().iterator().next().getAuthority()
  // (retorna "ROLE_USER" o "ROLE_ADMIN" — puedes hacer substring(5) para quitar
  // "ROLE_")
  //
  public AuthResponse login(LoginRequest req) {
    // TODO: implementar
    return null;
  }
}
