package com.sosfauna.orgaos.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orgaos")
public class Orgao {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false,unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String descricao;

    @Email
    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false) // Mutavel
    private String senha;

    @Column(nullable = false)
    private String telefone;

    private String redeSocial;

    @Column(nullable = false)
    private String endereco;

    @Column(name = "acesso", nullable = false, columnDefinition = "boolean default true")
    private boolean acesso;

    @Column(nullable = false)
    private LocalDate dataCadastro;

}
