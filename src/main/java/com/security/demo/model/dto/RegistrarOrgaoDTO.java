package com.security.demo.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegistrarOrgaoDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
