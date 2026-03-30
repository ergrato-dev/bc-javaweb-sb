package com.bootcamp;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Práctica 01 — Mi Primera Migración Flyway
 *
 * Instrucciones:
 * STEP 2: Cambia application.yaml a ddl-auto: validate y agrega flyway
 * STEP 3: Crea src/main/resources/db/migration/V1__create_doctors_table.sql
 * STEP 4: Crea V2__add_phone_column.sql
 */
@SpringBootApplication
public class FlywayStarter {
  public static void main(String[] args) {
    SpringApplication.run(FlywayStarter.class, args);
  }

  @Bean
  CommandLineRunner seed(DoctorRepo repo) {
    return args -> {
      repo.save(new Doctor("Dr. Ana García", "Cardiology", "ana.garcia@hospital.com"));
      repo.save(new Doctor("Dr. Carlos López", "Neurology", "carlos.lopez@hospital.com"));
      repo.save(new Doctor("Dr. María Torres", "Pediatrics", "maria.torres@hospital.com"));
      System.out.println("Doctors saved: " + repo.count());
      repo.findAll().forEach(d -> System.out.println("  - " + d.getName() + " (" + d.getSpecialty() + ")"));
    };
  }
}

@Entity
@Table(name = "doctors")
class Doctor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String specialty;
  @Column(nullable = false, unique = true)
  private String email;

  // STEP 4: Agrega este campo cuando crees V2:
  // @Column(length = 20)
  // private String phone;

  protected Doctor() {
  }

  public Doctor(String name, String specialty, String email) {
    this.name = name;
    this.specialty = specialty;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getSpecialty() {
    return specialty;
  }
}

@Repository
interface DoctorRepo extends JpaRepository<Doctor, Long> {
}
