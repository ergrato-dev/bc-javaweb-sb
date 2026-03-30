package com.bootcamp;

import jakarta.persistence.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Práctica 02 — JPA Specifications para filtros dinámicos
 *
 * Instrucciones: descomenta cada sección en orden.
 */
@SpringBootApplication
public class SpecsApp {
    public static void main(String[] args) {
        SpringApplication.run(SpecsApp.class, args);
    }

    @Bean
    CommandLineRunner seed(ApptRepo repo, DoctorJpaRepo doctorRepo) {
        return args -> {
            var garcia  = doctorRepo.save(new DocSimple("Dr. García",  "Cardiology"));
            var rodrigo = doctorRepo.save(new DocSimple("Dr. Rodrigo", "Neurology"));
            repo.save(new Appt(garcia,  "2025-01-15T09:00:00", "SCHEDULED"));
            repo.save(new Appt(garcia,  "2025-01-16T10:00:00", "COMPLETED"));
            repo.save(new Appt(rodrigo, "2025-01-17T11:00:00", "SCHEDULED"));
            repo.save(new Appt(rodrigo, "2025-01-18T14:00:00", "CANCELLED"));
        };
    }
}

@Entity
@Table(name = "docs_simple")
class DocSimple {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    @Column private String specialty;
    protected DocSimple() {}
    public DocSimple(String name, String specialty) { this.name = name; this.specialty = specialty; }
    public Long getId() { return id; }
    public String getName() { return name; }
}

@Entity
@Table(name = "appts")
class Appt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "doctor_id") private DocSimple doctor;
    @Column(name = "scheduled_at") private LocalDateTime scheduledAt;
    @Column(nullable = false) private String status;
    protected Appt() {}
    public Appt(DocSimple doctor, String dateTime, String status) {
        this.doctor = doctor; this.scheduledAt = LocalDateTime.parse(dateTime); this.status = status;
    }
    public Long getId() { return id; }
    public String getStatus() { return status; }
    public DocSimple getDoctor() { return doctor; }
    public LocalDateTime getScheduledAt() { return scheduledAt; }
}

@Repository
interface DoctorJpaRepo extends JpaRepository<DocSimple, Long> {}

// ============================================
// STEP 2: Agregar JpaSpecificationExecutor
// Descomenta la extensión:
// ============================================
@Repository
interface ApptRepo extends JpaRepository<Appt, Long>
    // , JpaSpecificationExecutor<Appt>
{}

// ============================================
// STEP 3 + 4: AppointmentSpec con Specifications estáticas
// Descomenta esta clase:
// ============================================
// class AppointmentSpec {
//
//     // STEP 3: Spec por status
//     static Specification<Appt> hasStatus(String status) {
//         return (root, query, cb) ->
//             status == null ? null : cb.equal(root.get("status"), status);
//     }
//
//     // STEP 4: Spec por nombre de doctor (JOIN)
//     static Specification<Appt> hasDoctorName(String name) {
//         return (root, query, cb) -> {
//             if (name == null) return null;
//             var doctorJoin = root.join("doctor", JoinType.INNER);
//             return cb.like(cb.lower(doctorJoin.get("name")), "%" + name.toLowerCase() + "%");
//         };
//     }
// }

@Service
@Transactional(readOnly = true)
class ApptService {
    private final ApptRepo repo;
    public ApptService(ApptRepo repo) { this.repo = repo; }

    // SIN Specifications (versión inicial — con if/else):
    public List<Appt> search(String status, String doctorName) {
        if (status != null && doctorName != null) {
            return repo.findAll().stream()
                    .filter(a -> a.getStatus().equals(status))
                    .filter(a -> a.getDoctor().getName().contains(doctorName))
                    .toList();
        } else if (status != null) {
            return repo.findAll().stream().filter(a -> a.getStatus().equals(status)).toList();
        } else if (doctorName != null) {
            return repo.findAll().stream().filter(a -> a.getDoctor().getName().contains(doctorName)).toList();
        }
        return repo.findAll();
    }

    // CON Specifications (descomenta en STEP 4 y reemplaza el método anterior):
    // public List<Appt> search(String status, String doctorName) {
    //     var spec = Specification
    //             .where(AppointmentSpec.hasStatus(status))
    //             .and(AppointmentSpec.hasDoctorName(doctorName));
    //     return repo.findAll(spec);
    // }
}

@RestController
@RequestMapping("/api/appointments")
class ApptController {
    private final ApptService service;
    public ApptController(ApptService service) { this.service = service; }

    @GetMapping
    public List<Appt> search(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String doctorName) {
        return service.search(status, doctorName);
    }
}
