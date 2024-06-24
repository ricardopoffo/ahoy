package com.ahoy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahoy.dto.VendedorDTO;
import com.ahoy.model.Vendedor;
import com.ahoy.repository.VendedorRepository;

import jakarta.transaction.Transactional;
@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private SequencialMatriculaService sequencialMatriculaService;

    @Transactional
    public Vendedor createVendedor(VendedorDTO vendedorDTO) {
        Vendedor vendedor = new Vendedor();

        // Gera a matrícula sequencial
        String matricula = String.format("%08d", sequencialMatriculaService.proximoNumeroSequencial()) + "-" + vendedorDTO.getTipoContratacao().getSigla();

        // map fields from DTO to entity
        vendedor.setMatricula(matricula);
        vendedor.setNome(vendedorDTO.getNome());
        vendedor.setDataNascimento(vendedorDTO.getDataNascimento());
        vendedor.setDocumento(vendedorDTO.getDocumento());
        vendedor.setEmail(vendedorDTO.getEmail());
        vendedor.setTipoContratacao(vendedorDTO.getTipoContratacao());
        vendedor.setFilial(vendedorDTO.getFilial());
        return vendedorRepository.save(vendedor);
    }

    public Optional<Vendedor> getVendedorByMatricula(String matricula) {
        return Optional.ofNullable(vendedorRepository.findByMatricula(matricula));
    }

    public List<Vendedor> getAllVendedores() {
        return vendedorRepository.findAll();
    }

    @Transactional
    public void deleteVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }

}