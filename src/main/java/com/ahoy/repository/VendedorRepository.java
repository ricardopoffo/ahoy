package com.ahoy.repository;

import com.ahoy.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Vendedor findByMatricula(String matricula);
}