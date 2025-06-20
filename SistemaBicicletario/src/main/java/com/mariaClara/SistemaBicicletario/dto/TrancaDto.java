package com.mariaClara.SistemaBicicletario.dto;

import com.mariaClara.SistemaBicicletario.model.StatusTranca;

public class TrancaDto {
    private int id;
    private int bicicleta;
    private int numero;
    private String localizacao;
    private String anoDeFabricacao;
    private String modelo;
    private StatusTranca statusTranca;
    private Integer idToten; //campo adicionado para poder associar tranca ao toten nos metodos intregrarTrancaNaRede retirarDaRedeDeTotens

    public StatusTranca getStatusTranca() {
        return statusTranca;
    }

    public void setStatusTranca(StatusTranca statusTranca) {
        this.statusTranca = statusTranca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBicicleta() {
        return bicicleta;
    }

    public void setBicicleta(int bicicleta) {
        this.bicicleta = bicicleta;
    }

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

    public Integer getIdToten() {
        return idToten;
    }

    public void setIdToten(Integer idToten) {
        this.idToten = idToten;
    }
}
