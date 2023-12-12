package com.startup.m3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String companyName;
    private String password;
    private Instant created_at;
    private String roles;

    public User() {
    }

    public User(
            final Long id,
            final String email,
            final String password,
            final String companyName,
            final Instant created_at,
            final String roles
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.created_at = created_at;
        this.companyName = companyName;
        this.roles = roles;
    }

    public static User newUser(
            final String email,
            final String password,
            final String companyName
    ){
        return new User(
                0L, email, password, companyName, Instant.now(), "ROLE_USER"
        );
    }

    public Long getId() {
        return id;
    }

    public String getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getPassword() {
        return password;
    }

    public Instant getCreated_at() {
        return created_at;
    }
}