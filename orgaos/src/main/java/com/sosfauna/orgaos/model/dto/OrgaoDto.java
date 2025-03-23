package com.sosfauna.orgaos.model.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Date;

public record OrgaoDto(

        @NotBlank(message = "nome obrigatório")
        @Size(max = 100, message = "O nome deve ter no maximo 100 caracteres")
        String nome,

        @NotBlank(message = "CNPJ Obrigatório")
        @Pattern(regexp = "\\d{14}", message = "O CNPJ deve conter 14 caracteres")
        String cnpj,

        @Size(max = 100, message = "A descrição deve ter no maximo 100 caracteres")
        String descricao,

        @NotBlank(message = "E-mail obrigatório")
        @Email(message = "Formato de e-mail inválido")
        @Size(max = 100, message = "O Email deve ter no maximo 50 caracteres")
        String email,

        @NotBlank(message = "Senha obrigatória")
        @Size(max = 100, message = "A senha deve conter no maximo 50 caracters.")
        String senha,

        @NotBlank(message = "Telefone obrigatório")
        @Size(max = 100, message = "o telefone deve conter no maximo 11 caracteres")
        String telefone,

        @Size(max = 100, message = "A rede social deve conter no maximo 255 caracteres")
        String redeSocial,

        @NotBlank(message = "Endereço obrigatório")
        @Size(max = 100, message = "O endereço deve conter no maximo 255 caracteres")
        String endereco,

        @NotNull(message = "Acesso Obrigatório") //mutavel
        boolean acesso,

        @NotNull(message = "Data Obrigatória")
        LocalDate dataCadastro
) {
}
