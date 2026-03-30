package com.bootcamp.dto;

import com.bootcamp.domain.Appointment.Status;
import java.time.LocalDateTime;

public record AppointmentFilter(
    Long doctorId,
    Long patientId,
    Status status,
    LocalDateTime fromDate,
    LocalDateTime toDate
) {}
