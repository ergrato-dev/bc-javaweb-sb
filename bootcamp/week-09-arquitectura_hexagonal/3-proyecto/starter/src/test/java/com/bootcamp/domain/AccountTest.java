package com.bootcamp.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/** Pure domain tests — no Spring context, runs in milliseconds */
class AccountTest {

    private Account activeAccount(double balance) {
        return new Account(AccountId.generate(), Money.of(balance, "USD"), AccountStatus.ACTIVE, "Alice");
    }

    @Test
    void deposit_shouldIncreaseBalance() {
        var account = activeAccount(100.0);
        account.deposit(Money.of(50, "USD"));
        assertThat(account.getBalance()).isEqualTo(Money.of(150, "USD"));
    }

    @Test
    void withdraw_shouldDecreaseBalance() {
        var account = activeAccount(200.0);
        account.withdraw(Money.of(75, "USD"));
        assertThat(account.getBalance()).isEqualTo(Money.of(125, "USD"));
    }

    @Test
    void withdraw_shouldThrowInsufficientFunds_whenBalanceTooLow() {
        var account = activeAccount(50.0);
        assertThatThrownBy(() -> account.withdraw(Money.of(100, "USD")))
            .isInstanceOf(InsufficientFundsException.class);
    }

    @Test
    void deposit_shouldThrowAccountFrozenException_whenAccountIsFrozen() {
        var account = new Account(AccountId.generate(), Money.of(100, "USD"), AccountStatus.FROZEN, "Bob");
        assertThatThrownBy(() -> account.deposit(Money.of(50, "USD")))
            .isInstanceOf(AccountFrozenException.class);
    }

    @Test
    void money_shouldNotAllowNegativeAmount() {
        assertThatThrownBy(() -> Money.of(-10, "USD"))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void money_shouldThrowOnCurrencyMismatch() {
        var usd = Money.of(100, "USD");
        var eur = Money.of(50, "EUR");
        assertThatThrownBy(() -> usd.add(eur))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
