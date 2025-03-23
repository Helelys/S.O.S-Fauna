package com.security.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orgaos")
public class Ong {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "nome")
    private String name;

    @Column(name = "senha")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ong(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Ong() {
    }
}