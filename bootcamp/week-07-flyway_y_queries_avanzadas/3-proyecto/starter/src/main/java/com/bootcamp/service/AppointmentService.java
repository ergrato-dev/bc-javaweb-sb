package com.bootcamp.service;

import com.bootcamp.domain.Appointment;
import com.bootcamp.domain.Appointment.Status;
import com.bootcamp.dto.*;
import com.bootcamp.exception.AppointmentNotFoundException;
import com.bootcamp.exception.DoctorNotFoundException;
import com.bootcamp.exception.PatientNotFoundException;
import com.bootcamp.repository.AppointmentRepository;
import com.bootcamp.repository.DoctorRepository;
import com.bootcamp.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final DoctorRepository doctorRepository;
  private final PatientRepository patientRepository;

  public AppointmentService(AppointmentRepository a, DoctorRepository d, PatientRepository p) {
    this.appointmentRepository = a;
    this.doctorRepository = d;
    this.patientRepository = p;
  }

  /**
   * TODO:
   * Build Specification from filter and call appointmentRepository.findAll(spec,
   * pageable).
   * Map results to AppointmentResponse.
   * AppointmentFilter contains: doctorId, patientId, status, fromDate, toDate.
   */
  public Page<AppointmentResponse> search(AppointmentFilter filter, Pageable pageable) {
    // TODO: Implement with Specifications
    return Page.empty(pageable);
  }

  /**
   * TODO:
   * 1. Find doctor and patient (throw respective NotFoundException if not found)
   * 2. Create Appointment with doctor, patient, scheduledAt, notes
   * 3. Save and return AppointmentResponse
   */
  @Transactional
  public AppointmentResponse create(AppointmentCreateRequest request) {
    // TODO: Implement
    return null;
  }

  /**
   * TODO:
   * 1. Find appointment by ID (throw AppointmentNotFoundException if not found)
   * 2. Update status
   * 3. Save and return AppointmentResponse
   */
  @Transactional
  public AppointmentResponse updateStatus(Long id, Status status) {
    // TODO: Implement
    return null;
  }

  /**
   * TODO:
   * 1. Find appointment by ID (throw AppointmentNotFoundException if not found)
   * 2. Cancel — set status to CANCELLED
   * 3. Save
   */
  @Transactional
  public void cancel(Long id) {
    // TODO: Implement
  }
}
