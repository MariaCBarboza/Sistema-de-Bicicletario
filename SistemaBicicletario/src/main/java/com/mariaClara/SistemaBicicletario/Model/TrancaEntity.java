package com.mariaClara.SistemaBicicletario.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "trancas")
public class TrancaEntity {
    @Id
    @GeneratedValue
    private int id;
    private int bicicleta;
    @Column (nullable = false)
    private int numero;
    @Column(nullable = false)
    private String localizacao;
    private String anoDeFabricacao;
    private String modelo;
    @Enumerated(EnumType.STRING)
    private StatusTranca statusTranca;

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

    public StatusTranca getStatusTranca() {
        return statusTranca;
    }

    public void setStatusTranca(StatusTranca statusTranca) {
        this.statusTranca = statusTranca;
    }
}
