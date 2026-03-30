package com.bootcamp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for all REST controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  record ErrorResponse(int status, String error, String message, String path) {
  }

  // TODO:
  // 1. Handle EmployeeNotFoundException → 404 Not Found
  // return ErrorResponse with status=404, error="Not Found"

  // TODO:
  // 2. Handle DuplicateEmailException → 409 Conflict
  // return ErrorResponse with status=409, error="Conflict"

  // TODO:
  // 3. Handle MethodArgumentNotValidException → 400 Bad Request
  // Build a map of field → error message from getBindingResult().getFieldErrors()
  // Return: {"status":400, "error":"Validation Failed", "errors":{...},
  // "path":"..."}

  // TODO:
  // 4. Catch-all Exception → 500 Internal Server Error
  // DO NOT expose internal details
}
