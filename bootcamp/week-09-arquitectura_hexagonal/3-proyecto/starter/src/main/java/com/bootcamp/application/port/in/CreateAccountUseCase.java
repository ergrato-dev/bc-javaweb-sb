package com.bootcamp.application.port.in;

import com.bootcamp.domain.Money;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/** Input port — use case interface consumed by the REST adapter */
public interface CreateAccountUseCase {

    record CreateAccountCommand(
        @NotBlank String ownerName,
        @NotBlank String currency,
        @Positive double initialBalance
    ) {}

    record AccountCreatedResult(String accountId, double balance, String currency) {}

    AccountCreatedResult createAccount(CreateAccountCommand command);
}
