package com.ahoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ahoy.model.SequencialMatricula;
import com.ahoy.repository.SequencialMatriculaRepository;

@Service
public class SequencialMatriculaService {

    @Autowired
    private SequencialMatriculaRepository sequencialMatriculaRepository;

    @Transactional
    public int proximoNumeroSequencial() {
        SequencialMatricula sequencialMatricula = sequencialMatriculaRepository.findById(1L)
                .orElseGet(() -> new SequencialMatricula()); // Cria uma nova entrada se não existir

        int proximoNumero = sequencialMatricula.getProximoNumero();
        sequencialMatricula.setProximoNumero(proximoNumero + 1);

        sequencialMatriculaRepository.save(sequencialMatricula);

        return proximoNumero;
    }
}