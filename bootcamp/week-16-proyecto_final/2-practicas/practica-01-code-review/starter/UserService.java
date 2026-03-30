package com.bootcamp.review.service;

import com.bootcamp.review.domain.User;
import com.bootcamp.review.dto.CreateUserRequest;
import com.bootcamp.review.dto.UserResponse;
import com.bootcamp.review.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  // ============================================
  // PASO 3: Hashear contraseñas con BCrypt
  // ============================================
  @Transactional
  public UserResponse create(CreateUserRequest request) {
    var user = new User();
    user.setEmail(request.email());
    user.setName(request.name());

    // ❌ CRÍTICO — contraseña en texto plano (línea que debes eliminar):
    user.setPassword(request.password());

    // ✅ Descomenta esta línea y comenta la anterior:
    // user.setPassword(passwordEncoder.encode(request.password()));

    var saved = userRepository.save(user);
    return new UserResponse(saved.getId(), saved.getEmail(), saved.getName());
  }

  // ============================================
  // PASO 4: Retornar DTO desde el service
  // ============================================
  // ✅ Descomenta este método cuando el controller esté arreglado:
  // @Transactional(readOnly = true)
  // public UserResponse findById(Long id) {
  // return userRepository.findById(id)
  // .map(u -> new UserResponse(u.getId(), u.getEmail(), u.getName()))
  // .orElseThrow(() -> new ResourceNotFoundException("User not found: " + id));
  // }
}
