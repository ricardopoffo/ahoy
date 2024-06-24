package com.ahoy.enums;

public enum TipoContratacao {
    OUTSOURCING("Outsourcing (terceirizado)"),
    CLT("CLT"),
    PESSOA_JURIDICA("Pessoa Jurídica");

    private final String descricao;

    TipoContratacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}