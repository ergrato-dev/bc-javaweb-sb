package com.bootcamp.repository;

import com.bootcamp.domain.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long>, JpaSpecificationExecutor<Doctor> {
    Optional<Doctor> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByLicenseNo(String licenseNo);
    Page<Doctor> findByActiveTrue(Pageable pageable);
    Page<Doctor> findBySpecialty(String specialty, Pageable pageable);
}
