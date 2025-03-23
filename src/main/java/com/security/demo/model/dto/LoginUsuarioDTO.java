package com.security.demo.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoginUsuarioDTO {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
