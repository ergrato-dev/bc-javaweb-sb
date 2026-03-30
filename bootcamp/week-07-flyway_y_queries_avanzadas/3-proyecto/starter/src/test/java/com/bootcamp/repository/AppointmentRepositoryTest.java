package com.bootcamp.repository;

import com.bootcamp.domain.Appointment;
import com.bootcamp.domain.Appointment.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = {
    "spring.flyway.locations=classpath:db/migration",
    "spring.jpa.hibernate.ddl-auto=validate"
})
class AppointmentRepositoryTest {

    @Autowired TestEntityManager em;
    @Autowired AppointmentRepository appointmentRepository;
    @Autowired DoctorRepository doctorRepository;
    @Autowired PatientRepository patientRepository;

    @Test
    void flyway_shouldCreateTablesSuccessfully() {
        // Flyway migrations ran — tables must exist
        assertThat(doctorRepository.count()).isGreaterThanOrEqualTo(0);
        assertThat(patientRepository.count()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void findAll_withStatusSpec_shouldFilterByStatus() {
        var doctor  = doctorRepository.findAll().stream().findFirst().orElseThrow();
        var patient = patientRepository.findAll().stream().findFirst().orElseThrow();

        var appt = em.persist(new Appointment(doctor, patient,
                LocalDateTime.now().plusDays(1), "Test appointment"));
        em.flush();

        Specification<Appointment> scheduled = (root, q, cb) ->
                cb.equal(root.get("status"), Status.SCHEDULED);

        var found = appointmentRepository.findAll(scheduled);
        assertThat(found).isNotEmpty();
        assertThat(found).allMatch(a -> a.getStatus() == Status.SCHEDULED);
    }

    @Test
    void findByDoctorId_shouldReturnDoctorAppointments() {
        var doctor  = doctorRepository.findAll().stream().findFirst().orElseThrow();
        var patient = patientRepository.findAll().stream().findFirst().orElseThrow();
        em.persist(new Appointment(doctor, patient, LocalDateTime.now().plusDays(2), null));
        em.flush();

        var found = appointmentRepository.findByDoctor_IdAndStatus(
                doctor.getId(), Status.SCHEDULED, PageRequest.of(0, 10));
        assertThat(found.getContent()).isNotEmpty();
    }
}
