package com.mariaClara.SistemaBicicletario.dto;

import com.mariaClara.SistemaBicicletario.model.StatusBicicleta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//@Data
public class NovaBicicletaDto {
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @Pattern(regexp = "\\d{4}")
    private String ano;
    @NotNull
    private Integer numero;
    @NotNull
    private StatusBicicleta status;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public StatusBicicleta getStatus() {
        return status;
    }

    public void setStatus(StatusBicicleta status) {
        this.status = status;
    }
}
