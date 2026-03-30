package com.bootcamp.domain;

import java.util.Objects;
import java.util.UUID;

/** Value Object — strongly-typed account identifier */
public record AccountId(String value) {
  public AccountId {
    if (value == null || value.isBlank())
      throw new IllegalArgumentException("AccountId cannot be blank");
  }

  public static AccountId generate() {
    return new AccountId(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return value;
  }
}
