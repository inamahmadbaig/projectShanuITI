package com.sunshineiti.profees.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String pin;

    @Column(nullable = false)
    private String role;

    public User() {}

    public User(String username, String pin, String role) {
        this.username = username;
        this.pin = pin;
        this.role = role;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
