package com.bootcamp.repository;

import com.bootcamp.domain.Payment;
import com.bootcamp.domain.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
  Page<Payment> findByOwnerUsername(String ownerUsername, Pageable pageable);

  Optional<Payment> findByIdAndOwnerUsername(Long id, String ownerUsername);

  Page<Payment> findByStatus(PaymentStatus status, Pageable pageable);
}
