package com.bootcamp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Centralized exception handler for all REST controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  // TODO:
  // 1. Define a record ErrorResponse with fields:
  // int status, String error, String message, String path

  // TODO:
  // 2. Add @ExceptionHandler for ProductNotFoundException
  // - @ResponseStatus(HttpStatus.NOT_FOUND)
  // - Return ErrorResponse with status=404, error="Not Found"

  // TODO:
  // 3. Add @ExceptionHandler for IllegalArgumentException
  // - @ResponseStatus(HttpStatus.BAD_REQUEST)
  // - Return ErrorResponse with status=400, error="Bad Request"

  // TODO:
  // 4. Add catch-all @ExceptionHandler for Exception.class
  // - @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  // - Return ErrorResponse with status=500
  // - DO NOT expose internal error details (just "An unexpected error occurred")
}
