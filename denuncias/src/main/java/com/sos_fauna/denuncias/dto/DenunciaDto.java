package com.sos_fauna.denuncias.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sos_fauna.denuncias.model.StatusDenuncia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class DenunciaDto {
    private Integer id;
    private String animal;
    private String denunciado;
    private String descricao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataOcorrido;

    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime horaOcorrido;

    private String bairro;
    private String rua;
    private StatusDenuncia statusDenuncia;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataCriacao;

    private String publicId;


    public DenunciaDto() {}


    public DenunciaDto(Integer id, String animal, String denunciado, String descricao, LocalDate dataOcorrido, LocalTime horaOcorrido, String bairro, String rua, StatusDenuncia statusDenuncia, LocalDateTime dataCriacao, String publicId) {
        this.id = id;
        this.animal = animal;
        this.denunciado = denunciado;
        this.descricao = descricao;
        this.dataOcorrido = dataOcorrido;
        this.horaOcorrido = horaOcorrido;
        this.bairro = bairro;
        this.rua = rua;
        this.statusDenuncia = statusDenuncia;
        this.dataCriacao = dataCriacao;
        this.publicId = publicId;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getAnimal() { return animal; }
    public void setAnimal(String animal) { this.animal = animal; }

    public String getDenunciado() { return denunciado; }
    public void setDenunciado(String denunciado) { this.denunciado = denunciado; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public LocalDate getDataOcorrido() { return dataOcorrido; }
    public void setDataOcorrido(LocalDate dataOcorrido) { this.dataOcorrido = dataOcorrido; }

    public LocalTime getHoraOcorrido() { return horaOcorrido; }
    public void setHoraOcorrido(LocalTime horaOcorrido) { this.horaOcorrido = horaOcorrido; }

    public String getBairro() { return bairro; }
    public void setBairro(String bairro) { this.bairro = bairro; }

    public String getRua() { return rua; }
    public void setRua(String rua) { this.rua = rua; }

    public StatusDenuncia getStatusDenuncia() { return statusDenuncia; }
    public void setStatusDenuncia(StatusDenuncia statusDenuncia) { this.statusDenuncia = statusDenuncia; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getPublicId() { return publicId; }
    public void setPublicId(String publicId) { this.publicId = publicId; }
}
