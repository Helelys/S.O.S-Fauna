package com.security.demo.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrarOrgaoDTO {
    private String email;
    private String senha;

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }
}
