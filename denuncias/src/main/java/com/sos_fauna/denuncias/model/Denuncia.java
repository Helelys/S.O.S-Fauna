package com.sos_fauna.denuncias.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "denuncias")
public class Denuncia {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "animal", nullable = false)
    @NotNull
    @Size(max = 50)
    private String animal;

    @Column(name = "denunciado")
    private String denunciado;


    @Column(name = "descricao")
    @NotNull
    @Size(max = 255)
    private String descricao;

    @Column(name = "publicId")
    private String publicId;

    @Column(name = "dataOcorrido")
    private LocalDate dataOcorrido;


    @Column(name = "horaOcorrido")
    private LocalTime horaOcorrido;


    @Column(name = "bairro")
    @NotNull
    @Size(max = 100)
    private String bairro;

    @Column(name = "numero")
    private String numero;


    @Column(name = "rua",nullable = false)
    @NotNull
    @Size(max = 100)
    private String rua;

    @Size(max = 11)
    @Column(name = "cep")
    private String cep;

    @CreationTimestamp
    @Column(name = "dataCriacao")
    private LocalDateTime dataCriacao;

    //Lembrete Alterei aqui (Alterei o set)

    @Enumerated(EnumType.STRING)
    @Column(name = "statusDenuncia")
    private StatusDenuncia statusDenuncia;


    public Denuncia(){}


    public Denuncia(String animal, String descricao, LocalDate dataOcorrido, LocalTime horaOcorrido, String bairro, String rua, StatusDenuncia statusDenuncia) {
        this.animal = animal;
        this.descricao = descricao;
        this.dataOcorrido = dataOcorrido;
        this.horaOcorrido = horaOcorrido;
        this.bairro = bairro;
        this.rua = rua;
        this.statusDenuncia = statusDenuncia;
    }
    //Alterei no construtor >

    public String getPublicId() { return publicId; }

    public void setPublicId(String publicId) { this.publicId = publicId; }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getDenunciado() {
        return denunciado;
    }

    public void setDenunciado(String denunciado) {
        this.denunciado = denunciado;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataOcorrido() {
        return dataOcorrido;
    }

    public void setDataOcorrido(LocalDate dataOcorrido) {
        this.dataOcorrido = dataOcorrido;
    }

    public LocalTime getHoraOcorrido() {
        return horaOcorrido;
    }

    public void setHoraOcorrido(LocalTime horaOcorrido) {
        this.horaOcorrido = horaOcorrido;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusDenuncia getStatusDenuncia() {
        return statusDenuncia;
    }

    public void setStatusDenuncia(StatusDenuncia statusDenuncia) {
        this.statusDenuncia = statusDenuncia;
    }

}
