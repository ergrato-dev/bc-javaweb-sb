package com.bootcamp.application.port.in;

/** Input port — transfer money between accounts */
public interface TransferMoneyUseCase {

  record TransferCommand(String sourceAccountId, String targetAccountId,
      double amount, String currency) {
  }

  record TransferResult(String sourceAccountId, double newSourceBalance,
      String targetAccountId, double newTargetBalance) {
  }

  TransferResult transfer(TransferCommand command);
}
