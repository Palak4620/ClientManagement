package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "clients", indexes = {
    @Index(name = "idx_email", columnList = "emailId"),
    @Index(name = "idx_mobile", columnList = "mobileNumber")
})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    private String emailId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String mobileNumber;

    @Column(nullable = false)
    private Boolean isActive = true;

    // Getters and Setters
}
