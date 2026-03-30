package com.bootcamp.elibrary.repository;

import com.bootcamp.elibrary.domain.Loan;
import com.bootcamp.elibrary.domain.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, Long> {
  List<Loan> findByUsername(String username);

  List<Loan> findByUsernameAndStatus(String username, LoanStatus status);

  Optional<Loan> findByIdAndUsername(Long id, String username);

  List<Loan> findByDueDateBeforeAndStatus(LocalDate date, LoanStatus status);

  long countByUsernameAndStatus(String username, LoanStatus status);
}
