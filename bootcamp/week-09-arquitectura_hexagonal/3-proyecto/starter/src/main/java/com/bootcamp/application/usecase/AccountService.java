package com.bootcamp.application.usecase;

import com.bootcamp.application.port.in.*;
import com.bootcamp.application.port.out.AccountRepositoryPort;
import com.bootcamp.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Application service — implements all input ports (use cases).
 * Uses output ports (AccountRepositoryPort) for persistence.
 */
@Service
@Transactional(readOnly = true)
public class AccountService implements CreateAccountUseCase, TransferMoneyUseCase, GetAccountUseCase {

  private final AccountRepositoryPort accountRepository;

  public AccountService(AccountRepositoryPort accountRepository) {
    this.accountRepository = accountRepository;
  }

  /**
   * TODO:
   * 1. Create Account domain object: AccountId.generate(),
   * Money.of(initialBalance, currency), ACTIVE
   * 2. Save via accountRepository.save()
   * 3. Return AccountCreatedResult with saved account data
   */
  @Override
  @Transactional
  public AccountCreatedResult createAccount(CreateAccountCommand command) {
    // TODO: Implement
    return null;
  }

  /**
   * TODO:
   * 1. Load source account (throw AccountNotFoundException if not found)
   * 2. Load target account (throw AccountNotFoundException if not found)
   * 3. Call source.withdraw(Money.of(amount, currency))
   * 4. Call target.deposit(Money.of(amount, currency))
   * 5. Save both accounts
   * 6. Return TransferResult with new balances
   */
  @Override
  @Transactional
  public TransferResult transfer(TransferCommand command) {
    // TODO: Implement
    return null;
  }

  /**
   * TODO:
   * 1. Load account (throw AccountNotFoundException if not found)
   * 2. Map to AccountView record
   */
  @Override
  public AccountView getAccount(String accountId) {
    // TODO: Implement
    return null;
  }

  /**
   * TODO:
   * 1. Call accountRepository.findAll(pageable)
   * 2. Map each Account to AccountView using map()
   */
  @Override
  public Page<AccountView> listAccounts(Pageable pageable) {
    // TODO: Implement
    return Page.empty(pageable);
  }

  private AccountView toView(Account a) {
    return new AccountView(a.getId().value(), a.getOwnerName(),
        a.getBalance().amount().doubleValue(), a.getBalance().currency(),
        a.getStatus().name());
  }
}
