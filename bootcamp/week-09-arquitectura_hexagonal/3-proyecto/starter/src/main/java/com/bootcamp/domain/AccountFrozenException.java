package com.bootcamp.domain;

public class AccountFrozenException extends RuntimeException {
  public AccountFrozenException(AccountId id) {
    super("Account " + id + " is not active");
  }
}
