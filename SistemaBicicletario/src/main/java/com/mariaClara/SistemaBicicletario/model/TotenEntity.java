package com.mariaClara.SistemaBicicletario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "totens")
public class TotenEntity {
    @Id
    @GeneratedValue
    private int id_toten;
    private String localizacao;
    private String descricao;

    public int getId() {
        return id_toten;
    }

    public void setId(int id) {
        this.id_toten = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
