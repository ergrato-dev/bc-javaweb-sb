package com.bootcamp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({OrderNotFoundException.class, CustomerNotFoundException.class, ProductNotFoundException.class})
    ProblemDetail handleNotFound(RuntimeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidOrderStatusTransitionException.class)
    ProblemDetail handleBadTransition(InvalidOrderStatusTransitionException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(IllegalStateException.class)
    ProblemDetail handleIllegalState(IllegalStateException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
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
