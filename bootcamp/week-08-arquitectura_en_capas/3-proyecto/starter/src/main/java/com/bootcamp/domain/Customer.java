package com.bootcamp.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false) private String name;
    @Column(nullable = false, unique = true) private String email;
    @Column private String phone;
    @Column(name = "created_at") private LocalDateTime createdAt;

    protected Customer() {}
    public Customer(String name, String email, String phone) {
        this.name = name; this.email = email; this.phone = phone;
        this.createdAt = LocalDateTime.now();
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}
