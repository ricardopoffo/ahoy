package com.ahoy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahoy.model.SequencialMatricula;

public interface SequencialMatriculaRepository extends JpaRepository<SequencialMatricula, Long> {
}
