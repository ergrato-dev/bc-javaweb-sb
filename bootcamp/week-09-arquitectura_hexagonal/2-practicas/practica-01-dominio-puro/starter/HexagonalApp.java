package com.bootcamp;

// ============================================================
// HexagonalApp.java — Práctica 01: Dominio puro con Value Objects
// Sin Spring, sin JPA — solo Java puro
// Descomenta cada PASO en orden
// ============================================================

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class HexagonalApp {
  public static void main(String[] args) {
    System.out.println("=== Arquitectura Hexagonal — Dominio Puro ===");
    // Prueba tu código aquí después de descomentar los pasos
  }
}

// ============================================================
// PASO 1: Value Object Money — inmutable, validado, con operaciones
// ============================================================
// Los Value Objects no tienen id — se comparan por valor, no por referencia.
// Descomenta las siguientes líneas:
//
// record Money(BigDecimal amount, String currency) {
//
// // Compact constructor con validaciones
// Money {
// Objects.requireNonNull(amount, "Amount cannot be null");
// Objects.requireNonNull(currency, "Currency cannot be null");
// if (amount.compareTo(BigDecimal.ZERO) < 0) {
// throw new IllegalArgumentException("Amount cannot be negative: " + amount);
// }
// // Normalizar escala a 2 decimales
// amount = amount.setScale(2, RoundingMode.HALF_UP);
// }
//
// Money add(Money other) {
// requireSameCurrency(other);
// return new Money(amount.add(other.amount), currency);
// }
//
// Money subtract(Money other) {
// requireSameCurrency(other);
// var result = amount.subtract(other.amount);
// if (result.compareTo(BigDecimal.ZERO) < 0) {
// throw new IllegalArgumentException("Subtraction would result in negative
// amount");
// }
// return new Money(result, currency);
// }
//
// boolean isLessThan(Money other) {
// requireSameCurrency(other);
// return amount.compareTo(other.amount) < 0;
// }
//
// private void requireSameCurrency(Money other) {
// if (!currency.equals(other.currency)) {
// throw new IllegalArgumentException("Currency mismatch: " + currency + " vs "
// + other.currency);
// }
// }
//
// static Money of(double amount, String currency) {
// return new Money(BigDecimal.valueOf(amount), currency);
// }
// }

// ============================================================
// PASO 2: Value Object AccountId — ID fuertemente tipado
// ============================================================
// Evita confundir accountId con customerId o transactionId
// Descomenta las siguientes líneas:
//
// record AccountId(String value) {
// AccountId {
// if (value == null || value.isBlank()) {
// throw new IllegalArgumentException("AccountId cannot be blank");
// }
// }
// static AccountId generate() { return new
// AccountId(UUID.randomUUID().toString()); }
// @Override public String toString() { return value; }
// }

// ============================================================
// PASO 3: Entidad de Dominio Account — con comportamiento rico
// ============================================================
// La entidad tiene IDENTIDAD (AccountId) y COMPORTAMIENTO (deposit/withdraw)
// Los invariantes del negocio se aplican dentro de la entidad.
// Descomenta las siguientes líneas:
//
// class Account {
// private final AccountId id;
// private Money balance;
//
// Account(AccountId id, Money initialBalance) {
// this.id = id;
// this.balance = initialBalance;
// }
//
// void deposit(Money amount) {
// if (amount.isLessThan(Money.of(0.01, amount.currency()))) {
// throw new IllegalArgumentException("Deposit amount must be positive");
// }
// this.balance = balance.add(amount);
// System.out.println("Deposited " + amount + " → new balance: " + balance);
// }
//
// void withdraw(Money amount) {
// if (balance.isLessThan(amount)) {
// throw new IllegalStateException(
// "Insufficient funds: balance " + balance + " < withdrawal " + amount);
// }
// this.balance = balance.subtract(amount);
// System.out.println("Withdrew " + amount + " → new balance: " + balance);
// }
//
// AccountId getId() { return id; }
// Money getBalance() { return balance; }
// }

// ============================================================
// PASO 4: Puerto de salida AccountRepository — interfaz pura
// ============================================================
// El dominio DEFINE la interfaz — la infraestructura la IMPLEMENTA
// Descomenta las siguientes líneas:
//
// interface AccountRepository {
// Account findById(AccountId id);
// void save(Account account);
// }
//
// // Implementación en memoria (para testing/práctica)
// class InMemoryAccountRepository implements AccountRepository {
// private final Map<String, Account> store = new HashMap<>();
//
// @Override
// public Account findById(AccountId id) {
// var account = store.get(id.value());
// if (account == null) throw new RuntimeException("Account not found: " + id);
// return account;
// }
//
// @Override
// public void save(Account account) {
// store.put(account.getId().value(), account);
// }
// }

// ============================================================
// PASO 5: Use Case DepositMoneyUseCase — caso de uso de aplicación
// ============================================================
// Coordina: carga la cuenta → aplica la operación del dominio → persiste
// Descomenta las siguientes líneas:
//
// record DepositCommand(AccountId accountId, Money amount) {}
//
// class DepositMoneyUseCase {
// private final AccountRepository accountRepository; // puerto de salida
//
// DepositMoneyUseCase(AccountRepository accountRepository) {
// this.accountRepository = accountRepository;
// }
//
// void deposit(DepositCommand command) {
// var account = accountRepository.findById(command.accountId());
// account.deposit(command.amount()); // lógica en el dominio
// accountRepository.save(account);
// }
// }
