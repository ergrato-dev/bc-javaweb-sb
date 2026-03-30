package com.bootcamp.domain;

public class InsufficientFundsException extends RuntimeException {
  public InsufficientFundsException(AccountId id, Money requested) {
    super("Insufficient funds in account " + id + ": requested " + requested.amount() + " " + requested.currency());
  }
}
