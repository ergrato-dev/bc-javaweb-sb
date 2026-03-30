package com.bootcamp.review.controller;

import com.bootcamp.review.domain.User;
import com.bootcamp.review.dto.CreateUserRequest;
import com.bootcamp.review.dto.UserResponse;
import com.bootcamp.review.repository.UserRepository;
import com.bootcamp.review.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserRepository userRepository;
  private final UserService userService;

  public UserController(UserRepository userRepository, UserService userService) {
    this.userRepository = userRepository;
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest req) {
    return ResponseEntity.status(201).body(userService.create(req));
  }

  // ============================================
  // PASO 4: No exponer la entidad JPA
  // ============================================
  // ❌ MAL — retorna la entidad con el campo password visible:
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserBad(@PathVariable Long id) {
    return ResponseEntity.ok(userRepository.findById(id).orElseThrow());
  }

  // ✅ BIEN — descomenta y comenta el metodo anterior:
  // @GetMapping("/{id}")
  // public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
  // return ResponseEntity.ok(userService.findById(id));
  // }
}
