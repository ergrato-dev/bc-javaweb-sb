package com.bootcamp.application.port.in;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/** Input port — query account information */
public interface GetAccountUseCase {

  record AccountView(String id, String ownerName, double balance, String currency, String status) {
  }

  AccountView getAccount(String accountId);

  Page<AccountView> listAccounts(Pageable pageable);
}
