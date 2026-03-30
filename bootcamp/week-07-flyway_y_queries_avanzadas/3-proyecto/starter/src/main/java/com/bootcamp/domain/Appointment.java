package com.bootcamp.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    public enum Status { SCHEDULED, COMPLETED, CANCELLED, NO_SHOW }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "doctor_id", nullable = false) private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "patient_id", nullable = false) private Patient patient;
    @Column(name = "scheduled_at", nullable = false) private LocalDateTime scheduledAt;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Status status = Status.SCHEDULED;
    @Column private String notes;
    @Column(name = "created_at") private LocalDateTime createdAt;

    protected Appointment() {}
    public Appointment(Doctor doctor, Patient patient, LocalDateTime scheduledAt, String notes) {
        this.doctor = doctor; this.patient = patient; this.scheduledAt = scheduledAt; this.notes = notes;
        this.createdAt = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public Doctor getDoctor() { return doctor; }
    public Patient getPatient() { return patient; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
    public Status getStatus() { return status; }
    public String getNotes() { return notes; }
    public void setStatus(Status status) { this.status = status; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setScheduledAt(LocalDateTime scheduledAt) { this.scheduledAt = scheduledAt; }
}
