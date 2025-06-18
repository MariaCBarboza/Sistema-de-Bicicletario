package com.mariaClara.SistemaBicicletario.dto;

import jakarta.validation.constraints.NotBlank;

public class NovoTotenDto {
    @NotBlank
    private String localizacao;
    @NotBlank
    private String descricao;

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
