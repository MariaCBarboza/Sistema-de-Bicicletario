package com.mariaClara.SistemaBicicletario.dto;

import com.mariaClara.SistemaBicicletario.model.StatusBicicleta;
import jakarta.validation.constraints.NotNull;

public class RetirarBicicletaDaRedeDto {
    @NotNull
    private int idTranca;
    @NotNull
    private int idBicicleta;
    @NotNull
    private int idFuncionario;
    @NotNull
    private StatusBicicleta statusAcaoReparador;

    public int getIdTranca() {
        return idTranca;
    }

    public void setIdTranca(int idTranca) {
        this.idTranca = idTranca;
    }

    public int getIdBicicleta() {
        return idBicicleta;
    }

    public void setIdBicicleta(int idBicicleta) {
        this.idBicicleta = idBicicleta;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public StatusBicicleta getStatusAcaoReparador() {
        return statusAcaoReparador;
    }

    public void setStatusAcaoReparador(StatusBicicleta statusAcaoReparador) {
        this.statusAcaoReparador = statusAcaoReparador;
    }
}
