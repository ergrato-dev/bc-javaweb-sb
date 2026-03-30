package com.bootcamp.exception;

public class PatientNotFoundException extends RuntimeException {
  public PatientNotFoundException(Long id) {
    super("Patient not found with id: " + id);
  }
}
