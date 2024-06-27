package com.ahoy.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ahoy.dto.VendedorDTO;
import com.ahoy.enums.TipoContratacao;
import com.ahoy.model.Vendedor;
import com.ahoy.service.VendedorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(VendedorController.class)
class VendedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendedorService vendedorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Vendedor vendedor;
    private VendedorDTO vendedorDTO;

    @BeforeEach
    void setUp() {
        vendedor = new Vendedor();
        vendedor.setId(1L);
        vendedor.setMatricula("00000001-CLT");
        vendedor.setNome("Fulano");
        vendedor.setDocumento("23224904909");
        vendedor.setEmail("fulano@example.com");

        vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome("Fulano");
        vendedorDTO.setDocumento("23224904909");
        vendedorDTO.setEmail("fulano@example.com");
        vendedorDTO.setMatricula("00000001-CLT");
        vendedorDTO.setTipoContratacao(TipoContratacao.CLT);
        vendedorDTO.setFilial("1");
    }

    @Test
    void testCreateVendedor() throws Exception {
        when(vendedorService.createVendedor(vendedorDTO)).thenReturn(vendedor);

        mockMvc.perform(post("/api/vendedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vendedorDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matricula").value("00000001-CLT"));

        verify(vendedorService, times(1)).createVendedor(vendedorDTO);
    }

    @Test
    void testGetVendedorByMatricula() throws Exception {
        when(vendedorService.getVendedorByMatricula("00000001-CLT")).thenReturn(Optional.of(vendedor));

        mockMvc.perform(get("/api/vendedores/00000001-CLT")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.matricula").value("00000001-CLT"));

        verify(vendedorService, times(1)).getVendedorByMatricula("00000001-CLT");
    }

    @Test
    void testGetVendedorByMatriculaNotFound() throws Exception {
        when(vendedorService.getVendedorByMatricula("00000001-CLT")).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/vendedores/00000001-CLT")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(vendedorService, times(1)).getVendedorByMatricula("00000001-CLT");
    }

    @Test
    void testGetAllVendedores() throws Exception {
        List<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(vendedor);
        when(vendedorService.getAllVendedores()).thenReturn(vendedores);

        mockMvc.perform(get("/api/vendedores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].matricula").value("00000001-CLT"));

        verify(vendedorService, times(1)).getAllVendedores();
    }

    @Test
    void testDeleteVendedor() throws Exception {
        mockMvc.perform(delete("/api/vendedores/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(vendedorService, times(1)).deleteVendedor(1L);
    }
}
