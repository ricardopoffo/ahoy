package com.ahoy.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FilialService {
    public List<FilialDTO> getFiliais() {
        return Arrays.asList(
                new FilialDTO(1L, "Filial 1", "12345678000199", "Cidade A", "UF1", "Tipo1", true, "2021-01-01", "2021-06-01"),
                new FilialDTO(2L, "Filial 2", "98765432000199", "Cidade B", "UF2", "Tipo2", true, "2021-02-01", "2021-06-01")
        );
    }
}

class FilialDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String cidade;
    private String uf;
    private String tipo;
    private Boolean ativo;
    private String dataCadastro;
    private String ultimaAtualizacao;

    public FilialDTO(Long id, String nome, String cnpj, String cidade, String uf, String tipo, Boolean ativo, String dataCadastro, String ultimaAtualizacao) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.uf = uf;
        this.tipo = tipo;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    // Getters and setters for each field
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }
}