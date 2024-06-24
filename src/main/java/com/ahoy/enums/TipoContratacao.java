package com.ahoy.enums;

public enum TipoContratacao {
    OUTSOURCING("Outsourcing (terceirizado)", "OUT"),
    CLT("CLT", "CLT"),
    PESSOA_JURIDICA("Pessoa Jurídica", "PJ");

    private final String descricao;
    private final String sigla;

    TipoContratacao(String descricao, String sigla) {
        this.descricao = descricao;
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSigla() {
        return sigla;
    }

    // Método estático para obter TipoContratacao pelo código/sigla
    public static TipoContratacao fromSigla(String sigla) {
        for (TipoContratacao tipo : TipoContratacao.values()) {
            if (tipo.sigla.equals(sigla)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de contratação inválido: " + sigla);
    }
}