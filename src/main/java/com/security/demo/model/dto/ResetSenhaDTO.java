package com.security.demo.model.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResetSenhaDTO {

    private String codigo;

    private String novaSenha;

    public String getCodigo() {
        return codigo;
    }

    public String getNovaSenha() {
        return novaSenha;
    }
}
