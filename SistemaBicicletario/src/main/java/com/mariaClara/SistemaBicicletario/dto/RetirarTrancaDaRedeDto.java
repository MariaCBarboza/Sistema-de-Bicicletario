package com.mariaClara.SistemaBicicletario.dto;

import com.mariaClara.SistemaBicicletario.model.StatusTranca;
import jakarta.validation.constraints.NotNull;

public class RetirarTrancaDaRedeDto {
    @NotNull
    private int idTranca;
    @NotNull
    private int idToten;
    @NotNull
    private int idFuncionario;
    @NotNull
    private StatusTranca statusTranca;

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

    public StatusTranca getStatusTranca() {
        return statusTranca;
    }

    public void setStatusTranca(StatusTranca statusTranca) {
        this.statusTranca = statusTranca;
    }
}
