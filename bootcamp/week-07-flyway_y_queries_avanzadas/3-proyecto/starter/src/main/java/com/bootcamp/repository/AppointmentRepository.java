package com.bootcamp.repository;

import com.bootcamp.domain.Appointment;
import com.bootcamp.domain.Appointment.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long>,
                JpaSpecificationExecutor<Appointment> {

    // Spring Data generates this from the method name (no implementation needed)
    Page<Appointment> findByDoctor_IdAndStatus(Long doctorId, Status status, Pageable pageable);

    /**
     * TODO:
     *  Find appointments by patient ID with JOIN FETCH for doctor and patient.
     *  @Query with JOIN FETCH a.doctor and JOIN FETCH a.patient
     */

    /**
     * TODO:
     *  @EntityGraph to find appointment by ID loading doctor and patient in one query.
     *  Use attributePaths = {"doctor", "patient"}
     */

    /**
     * TODO:
     *  @Query to find appointments scheduled between two dates, ordered by scheduledAt.
     *  Parameters: @Param("start") LocalDateTime, @Param("end") LocalDateTime
     *  Use Text Block (""" ... """) for multi-line JPQL
     */
}
