package com.example.user_management.model;

public class User {
    private Long id;       // Identificador único
    private String name;   // Nombre del usuario
    private String email;  // Email del usuario

    // Constructor vacío (requerido para deserialización JSON)
    public User() {}

    // Constructor con parámetros
    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters y Setters para cada campo

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

