package com.bootcamp.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AppointmentCreateRequest(
    @NotNull Long doctorId,
    @NotNull Long patientId,
    @NotNull @Future LocalDateTime scheduledAt,
    String notes
) {}
