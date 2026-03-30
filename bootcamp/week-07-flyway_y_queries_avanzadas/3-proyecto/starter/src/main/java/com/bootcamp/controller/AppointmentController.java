package com.bootcamp.controller;

import com.bootcamp.domain.Appointment.Status;
import com.bootcamp.dto.*;
import com.bootcamp.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

/**
 * REST Controller for Appointment management.
 *
 * TODO:
 * 1. Add @RestController and @RequestMapping("/api/appointments")
 * 2. Add constructor with AppointmentService
 * 3. GET /api/appointments
 * - @RequestParam(required=false): doctorId, patientId, status
 * (AppointmentStatus),
 * from (LocalDateTime @DateTimeFormat(iso=DATE_TIME)), to (LocalDateTime)
 * - Pageable
 * - Build AppointmentFilter and call service.search(filter, pageable)
 * - Return Page<AppointmentResponse>
 * 4. POST /api/appointments — @Valid body — return 201 Created
 * 5. PUT /api/appointments/{id}/status — @RequestParam Status status — return
 * 200
 * 6. DELETE /api/appointments/{id} — cancel — return 204 No Content
 */
public class AppointmentController {
  // TODO: Implement
}
