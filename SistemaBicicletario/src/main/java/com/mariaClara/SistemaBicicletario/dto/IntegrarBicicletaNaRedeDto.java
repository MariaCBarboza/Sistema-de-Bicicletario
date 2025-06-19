package com.mariaClara.SistemaBicicletario.dto;

import jakarta.validation.constraints.NotNull;

//na minha cabeça faz szentido criar um Dto de entrada pra isso pq eu preciso de informações de entidades diferentes
public class IntegrarBicicletaNaRedeDto {
    @NotNull
    private int idTranca;
    @NotNull
    private int idBicicleta;
    @NotNull
    private int idFuncionario;

    public int getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(int idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public int getIdTranca() {
        return idTranca;
    }

    public void setIdTranca(int idTranca) {
        this.idTranca = idTranca;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}
