package com.mariaClara.SistemaBicicletario.dto;

import com.mariaClara.SistemaBicicletario.model.StatusBicicleta;

//@Data
public class BicicletaDto {
    private Long id;
    private String marca;
    private String modelo;
    private String ano;
    private Integer numero;
    private StatusBicicleta status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
