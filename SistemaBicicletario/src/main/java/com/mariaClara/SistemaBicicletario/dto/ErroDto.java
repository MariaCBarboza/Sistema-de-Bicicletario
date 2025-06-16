package com.mariaClara.SistemaBicicletario.dto;

public class ErroDto {
    private String codigo;
    private String menssagem;

    public ErroDto(String codigo, String menssagem) {
        this.codigo = codigo;
        this.menssagem = menssagem;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMenssagem() {
        return menssagem;
    }

    public void setMenssagem(String menssagem) {
        this.menssagem = menssagem;
    }
}
