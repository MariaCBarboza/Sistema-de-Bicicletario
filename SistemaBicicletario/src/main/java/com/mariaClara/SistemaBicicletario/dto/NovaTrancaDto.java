package com.mariaClara.SistemaBicicletario.dto;

import com.mariaClara.SistemaBicicletario.model.StatusTranca;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class NovaTrancaDto {
    @Min(1)
    private int numero;
    @NotBlank
    private String localizacao;
    private String anoDeFabricacao;
    private String modelo;
    @NotNull
    private StatusTranca statusTranca;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getAnoDeFabricacao() {
        return anoDeFabricacao;
    }

    public void setAnoDeFabricacao(String anoDeFabricacao) {
        this.anoDeFabricacao = anoDeFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public StatusTranca getStatusTranca() {
        return statusTranca;
    }

    public void setStatusTranca(StatusTranca statusTranca) {
        this.statusTranca = statusTranca;
    }
}
