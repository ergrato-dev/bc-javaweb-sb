package com.bootcamp.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
public class Patient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "first_name", nullable = false)
  private String firstName;
  @Column(name = "last_name", nullable = false)
  private String lastName;
  @Column
  private String email;
  @Column
  private String phone;
  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;
  @Column(name = "blood_type")
  private String bloodType;
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  protected Patient() {
  }

  public Patient(String firstName, String lastName, String email, LocalDate birthDate, String bloodType) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.birthDate = birthDate;
    this.bloodType = bloodType;
    this.createdAt = LocalDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public String getBloodType() {
    return bloodType;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setFirstName(String v) {
    this.firstName = v;
  }

  public void setLastName(String v) {
    this.lastName = v;
  }

  public void setEmail(String v) {
    this.email = v;
  }

  public void setPhone(String v) {
    this.phone = v;
  }
}
