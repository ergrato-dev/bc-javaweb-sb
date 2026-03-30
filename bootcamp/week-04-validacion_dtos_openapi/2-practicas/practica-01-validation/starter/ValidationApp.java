package com.bootcamp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Práctica 01 — Jakarta Bean Validation
 *
 * Instrucciones: descomenta cada sección en orden.
 */
@SpringBootApplication
public class ValidationApp {
  public static void main(String[] args) {
    SpringApplication.run(ValidationApp.class, args);
  }

  // ============================================
  // STEP 2: Agregar constraints al Request DTO
  // Reemplaza el record EmployeeRequest con este (con constraints):
  // ============================================

  // Versión SIN validación (activa al inicio):
  record EmployeeRequest(String name, String email, double salary, String department) {
  }

  // Versión CON validación (descomenta para STEP 2):
  // record EmployeeRequest(
  // @NotBlank(message = "Name is required")
  // String name,
  //
  // @Email(message = "Must be a valid email")
  // @NotBlank(message = "Email is required")
  // String email,
  //
  // @Positive(message = "Salary must be positive")
  // double salary,
  //
  // @NotBlank(message = "Department is required")
  // String department
  // ) {}

  record EmployeeResponse(Long id, String name, String email, String department) {
  }

  // ============================================
  // STEP 4: Handler de validación en GlobalExceptionHandler
  // Descomenta las siguientes líneas:
  // ============================================

  // @RestControllerAdvice
  // static class GlobalExceptionHandler {
  //
  // @ExceptionHandler(MethodArgumentNotValidException.class)
  // @ResponseStatus(HttpStatus.BAD_REQUEST)
  // Map<String, Object> handleValidation(MethodArgumentNotValidException ex,
  // HttpServletRequest request) {
  // var errors = ex.getBindingResult().getFieldErrors()
  // .stream()
  // .collect(Collectors.toMap(
  // FieldError::getField,
  // fe -> fe.getDefaultMessage() != null ? fe.getDefaultMessage() : "invalid",
  // (a, b) -> a + ", " + b
  // ));
  // return Map.of(
  // "status", 400,
  // "error", "Validation Failed",
  // "errors", errors
  // );
  // }
  // }

  @Service
  static class EmployeeService {
    private final AtomicLong counter = new AtomicLong(1);
    private final List<EmployeeResponse> store = new ArrayList<>();

    public EmployeeResponse create(EmployeeRequest request) {
      var employee = new EmployeeResponse(counter.getAndIncrement(),
          request.name(), request.email(), request.department());
      store.add(employee);
      return employee;
    }

    public List<EmployeeResponse> findAll() {
      return List.copyOf(store);
    }
  }

  @RestController
  @RequestMapping("/api/employees")
  static class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
      this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getAll() {
      return employeeService.findAll();
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(
        // STEP 3: agrega @Valid antes de @RequestBody
        @RequestBody EmployeeRequest request) {
      var created = employeeService.create(request);
      return ResponseEntity
          .created(URI.create("/api/employees/" + created.id()))
          .body(created);
    }
  }
}
