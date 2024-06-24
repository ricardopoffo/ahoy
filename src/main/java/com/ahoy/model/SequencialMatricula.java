package com.ahoy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sequencial_matricula")
public class SequencialMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "proximo_numero")
    private int proximoNumero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProximoNumero() {
        return proximoNumero;
    }

    public void setProximoNumero(int proximoNumero) {
        this.proximoNumero = proximoNumero;
    }

}
