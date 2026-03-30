package com.bootcamp.elibrary.controller;

import com.bootcamp.elibrary.dto.Dtos.*;
import com.bootcamp.elibrary.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<LoanResponse> findMyLoans(Principal principal) {
        return loanService.findByUsername(principal.getName());
    }

    @GetMapping("/overdue")
    public List<LoanResponse> findOverdue() {
        return loanService.findOverdue();
    }

    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(@Valid @RequestBody LoanCreateRequest request,
                                                    Principal principal) {
        var loan = loanService.createLoan(principal.getName(), request);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(loan.id()).toUri();
        return ResponseEntity.created(location).body(loan);
    }

    @PatchMapping("/{id}/return")
    public LoanResponse returnBook(@PathVariable Long id, Principal principal) {
        return loanService.returnBook(principal.getName(), id);
    }
}
