package com.security.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orgaos_login")
public class Ong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Ong(Long id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public Ong() {
    }
}