package com.ahoy.controller;

import com.ahoy.model.Vendedor;
import com.ahoy.dto.VendedorDTO;
import com.ahoy.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    @PostMapping
    public ResponseEntity<Vendedor> createVendedor(@Validated @RequestBody VendedorDTO vendedorDTO) {
        Vendedor createdVendedor = vendedorService.createVendedor(vendedorDTO);
        return ResponseEntity.ok(createdVendedor);
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Vendedor> getVendedorByMatricula(@PathVariable String matricula) {
        return vendedorService.getVendedorByMatricula(matricula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Vendedor> getAllVendedores() {
        return vendedorService.getAllVendedores();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendedor(@PathVariable Long id) {
        vendedorService.deleteVendedor(id);
        return ResponseEntity.noContent().build();
    }
}