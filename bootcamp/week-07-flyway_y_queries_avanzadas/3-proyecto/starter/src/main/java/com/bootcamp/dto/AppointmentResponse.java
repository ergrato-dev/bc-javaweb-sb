package com.bootcamp.dto;

import com.bootcamp.domain.Appointment.Status;
import java.time.LocalDateTime;

public record AppointmentResponse(
    Long id,
    Long doctorId, String doctorName, String doctorSpecialty,
    Long patientId, String patientFullName,
    LocalDateTime scheduledAt,
    Status status,
    String notes) {
}
