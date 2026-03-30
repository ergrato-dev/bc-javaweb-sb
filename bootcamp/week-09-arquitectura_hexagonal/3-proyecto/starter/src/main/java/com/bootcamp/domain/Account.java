package com.bootcamp.domain;

/**
 * Domain Entity — rich model with business logic.
 * NO Spring or JPA annotations — pure Java.
 */
public class Account {

    private final AccountId id;
    private Money balance;
    private AccountStatus status;
    private final String ownerName;

    public Account(AccountId id, Money balance, AccountStatus status, String ownerName) {
        this.id = id;
        this.balance = balance;
        this.status = status;
        this.ownerName = ownerName;
    }

    /**
     * TODO:
     *  1. Verify account is ACTIVE (throw AccountFrozenException if FROZEN or CLOSED)
     *  2. Add amount to balance using Money.add()
     * Returns the new balance.
     */
    public Money deposit(Money amount) {
        // TODO: Implement
        return balance;
    }

    /**
     * TODO:
     *  1. Verify account is ACTIVE
     *  2. Verify balance >= amount (throw InsufficientFundsException if not)
     *  3. Subtract amount from balance using Money.subtract()
     * Returns the new balance.
     */
    public Money withdraw(Money amount) {
        // TODO: Implement
        return balance;
    }

    public void freeze() {
        if (status == AccountStatus.CLOSED) throw new IllegalStateException("Cannot freeze a closed account");
        this.status = AccountStatus.FROZEN;
    }

    public AccountId getId() { return id; }
    public Money getBalance() { return balance; }
    public AccountStatus getStatus() { return status; }
    public String getOwnerName() { return ownerName; }
}
