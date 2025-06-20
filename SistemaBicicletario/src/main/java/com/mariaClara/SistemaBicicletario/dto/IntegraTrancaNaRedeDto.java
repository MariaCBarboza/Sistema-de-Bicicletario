package com.mariaClara.SistemaBicicletario.dto;

import jakarta.validation.constraints.NotNull;

public class IntegraTrancaNaRedeDto {
    @NotNull
    private int idTranca;
    @NotNull
    private int idToten;
    @NotNull
    private int idFuncionario;

    public int getIdTranca() {
        return idTranca;
    }

    public void setIdTranca(int idTranca) {
        this.idTranca = idTranca;
    }

    public int getIdToten() {
        return idToten;
    }

    public void setIdToten(int idToten) {
        this.idToten = idToten;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}
