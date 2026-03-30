package com.bootcamp.infrastructure.exception;

import com.bootcamp.domain.AccountFrozenException;
import com.bootcamp.domain.InsufficientFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AccountNotFoundException.class)
  ProblemDetail handleNotFound(AccountNotFoundException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  @ExceptionHandler(InsufficientFundsException.class)
  ProblemDetail handleInsufficientFunds(InsufficientFundsException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
  }

  @ExceptionHandler(AccountFrozenException.class)
  ProblemDetail handleFrozen(AccountFrozenException ex) {
    return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  ProblemDetail handleValidation(MethodArgumentNotValidException ex) {
    var detail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
    detail.setTitle("Validation failed");
    detail.setProperty("errors", ex.getBindingResult().getFieldErrors()
        .stream().map(e -> e.getField() + ": " + e.getDefaultMessage()).toList());
    return detail;
  }
}
