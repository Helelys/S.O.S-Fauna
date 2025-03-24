package com.security.demo.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginUsuarioDTO {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
