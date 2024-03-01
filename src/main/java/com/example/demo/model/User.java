package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String surname;
    public String email;
    public String pass;

    public User() {
    }

    public User(String name, String surname, String email, String pass) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pass = pass;
    }
}
