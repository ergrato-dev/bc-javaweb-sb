package com.bootcamp;

// ============================================================
// AdaptersApp.java — Práctica 02: Adaptadores en Spring Boot
// Conecta el dominio puro con Spring JPA y REST
// Descomenta cada PASO en orden
// ============================================================

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@SpringBootApplication
public class AdaptersApp {
  public static void main(String[] args) {
    SpringApplication.run(AdaptersApp.class, args);
  }
}

// ============================================================
// PASO 1: Puertos de entrada — interfaces del Use Case
// ============================================================
// Descomenta las siguientes líneas:
//
// interface CreateAccountUseCase {
// record CreateAccountCommand(String currency, double initialBalance) {}
// String createAccount(CreateAccountCommand command); // returns account ID
// }
//
// interface DepositMoneyUseCase {
// record DepositCommand(String accountId, double amount, String currency) {}
// void deposit(DepositCommand command);
// }
//
// interface GetAccountUseCase {
// record AccountView(String id, double balance, String currency) {}
// AccountView getAccount(String accountId);
// }

// ============================================================
// PASO 2: Entidad JPA — SOLO persiste, sin lógica de negocio
// ============================================================
// Descomenta las siguientes líneas:
//
// @Entity @Table(name = "accounts_hex")
// class AccountJpaEntity {
// @Id private String id;
// @Column(nullable = false) private BigDecimal balance;
// @Column(nullable = false) private String currency;
//
// protected AccountJpaEntity() {}
// AccountJpaEntity(String id, BigDecimal balance, String currency) {
// this.id = id; this.balance = balance; this.currency = currency;
// }
//
// String getId() { return id; }
// BigDecimal getBalance() { return balance; }
// String getCurrency() { return currency; }
// void setBalance(BigDecimal balance) { this.balance = balance; }
// }
//
// interface AccountJpaEntityRepository extends JpaRepository<AccountJpaEntity,
// String> {}

// ============================================================
// PASO 3: Servicio de aplicación — implementa los Use Cases
// ============================================================
// Descomenta las siguientes líneas:
//
// @Service
// @Transactional
// class BankingService implements CreateAccountUseCase, DepositMoneyUseCase,
// GetAccountUseCase {
//
// private final AccountJpaEntityRepository repository;
//
// BankingService(AccountJpaEntityRepository repository) {
// this.repository = repository;
// }
//
// @Override
// public String createAccount(CreateAccountCommand command) {
// var id = UUID.randomUUID().toString();
// var entity = new AccountJpaEntity(id,
// BigDecimal.valueOf(command.initialBalance()), command.currency());
// repository.save(entity);
// return id;
// }
//
// @Override
// public void deposit(DepositCommand command) {
// var entity = repository.findById(command.accountId())
// .orElseThrow(() -> new RuntimeException("Account not found: " +
// command.accountId()));
//
// // Regla de negocio en el servicio (o en el dominio en hexagonal puro)
// if (!entity.getCurrency().equals(command.currency())) {
// throw new IllegalArgumentException("Currency mismatch");
// }
// entity.setBalance(entity.getBalance().add(BigDecimal.valueOf(command.amount())));
// }
//
// @Override
// @Transactional(readOnly = true)
// public AccountView getAccount(String accountId) {
// return repository.findById(accountId)
// .map(e -> new AccountView(e.getId(), e.getBalance().doubleValue(),
// e.getCurrency()))
// .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
// }
// }

// ============================================================
// PASO 4: Adaptador de entrada — Controller REST
// ============================================================
// Descomenta las siguientes líneas:
//
// @RestController @RequestMapping("/api/accounts")
// class AccountController {
//
// private final CreateAccountUseCase createAccountUseCase;
// private final DepositMoneyUseCase depositMoneyUseCase;
// private final GetAccountUseCase getAccountUseCase;
//
// AccountController(CreateAccountUseCase c, DepositMoneyUseCase d,
// GetAccountUseCase g) {
// this.createAccountUseCase = c; this.depositMoneyUseCase = d;
// this.getAccountUseCase = g;
// }
//
// @PostMapping
// ResponseEntity<Map<String, String>> create(@Valid @RequestBody CreateRequest
// req) {
// var id = createAccountUseCase.createAccount(
// new CreateAccountUseCase.CreateAccountCommand(req.currency(),
// req.initialBalance()));
// return ResponseEntity.status(201).body(Map.of("accountId", id));
// }
//
// @PostMapping("/{id}/deposits")
// ResponseEntity<Void> deposit(@PathVariable String id, @Valid @RequestBody
// DepositRequest req) {
// depositMoneyUseCase.deposit(
// new DepositMoneyUseCase.DepositCommand(id, req.amount(), req.currency()));
// return ResponseEntity.noContent().build();
// }
//
// @GetMapping("/{id}")
// ResponseEntity<GetAccountUseCase.AccountView> get(@PathVariable String id) {
// return ResponseEntity.ok(getAccountUseCase.getAccount(id));
// }
// }
//
// record CreateRequest(@NotBlank String currency, @Positive double
// initialBalance) {}
// record DepositRequest(@Positive double amount, @NotBlank String currency) {}
