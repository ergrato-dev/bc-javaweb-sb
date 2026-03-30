package com.bootcamp.infrastructure.adapter.in.web;

import com.bootcamp.application.port.in.*;
import com.bootcamp.application.port.in.CreateAccountUseCase.CreateAccountCommand;
import com.bootcamp.application.port.in.TransferMoneyUseCase.TransferCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Primary adapter — exposes use cases via REST HTTP.
 *
 * TODO:
 *  1. Add @RestController and @RequestMapping("/api/accounts")
 *  2. Add constructor with CreateAccountUseCase, TransferMoneyUseCase, GetAccountUseCase
 *
 *  3. POST /api/accounts
 *     Body: { ownerName, currency, initialBalance }
 *     Delegate to createAccountUseCase.createAccount(command)
 *     Return 201 Created with Location: /api/accounts/{id}
 *
 *  4. GET /api/accounts
 *     Pageable
 *     Delegate to getAccountUseCase.listAccounts(pageable)
 *     Return 200 Page<AccountView>
 *
 *  5. GET /api/accounts/{id}
 *     Delegate to getAccountUseCase.getAccount(id)
 *     Return 200 AccountView
 *
 *  6. POST /api/accounts/{sourceId}/transfers
 *     Body: { targetAccountId, amount, currency }
 *     Delegate to transferMoneyUseCase.transfer(command)
 *     Return 200 TransferResult
 */
public class AccountController {
    // TODO: Implement
}
