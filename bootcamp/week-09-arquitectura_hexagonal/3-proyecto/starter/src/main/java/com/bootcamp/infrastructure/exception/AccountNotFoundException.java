package com.bootcamp.infrastructure.exception;

import com.bootcamp.domain.AccountId;

public class AccountNotFoundException extends RuntimeException {
  public AccountNotFoundException(String id) {
    super("Account not found: " + id);
  }
}
