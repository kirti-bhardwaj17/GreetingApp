package com.example.greeting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity  // ✅ Marks this as a JPA entity
public class Greeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ✅ Auto-generate ID
    private long id;

    private String message;

    // Default constructor (needed for JPA)
    public Greeting() {}

    // Constructor with ID and message
    public Greeting(long id, String message) {
        this.id = id;
        this.message = message;
    }

    // Constructor with only message
    public Greeting(String message) {
        this.message = message;
    }

    public long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
