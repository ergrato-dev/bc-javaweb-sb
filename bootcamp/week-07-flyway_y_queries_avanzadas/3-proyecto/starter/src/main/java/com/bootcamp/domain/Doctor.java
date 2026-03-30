package com.bootcamp.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    @Column(nullable = false) private String specialty;
    @Column(nullable = false, unique = true) private String email;
    @Column(name = "license_no", nullable = false, unique = true) private String licenseNo;
    @Column private String phone;
    @Column(nullable = false) private boolean active = true;
    @Column(name = "created_at") private LocalDateTime createdAt;

    protected Doctor() {}
    public Doctor(String name, String specialty, String email, String licenseNo) {
        this.name = name; this.specialty = specialty; this.email = email; this.licenseNo = licenseNo;
        this.createdAt = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSpecialty() { return specialty; }
    public String getEmail() { return email; }
    public String getLicenseNo() { return licenseNo; }
    public String getPhone() { return phone; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setName(String v) { this.name = v; }
    public void setSpecialty(String v) { this.specialty = v; }
    public void setPhone(String v) { this.phone = v; }
    public void setActive(boolean v) { this.active = v; }
}
