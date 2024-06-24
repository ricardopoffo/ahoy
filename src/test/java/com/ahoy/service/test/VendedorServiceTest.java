package com.ahoy.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.ahoy.dto.VendedorDTO;
import com.ahoy.enums.TipoContratacao;
import com.ahoy.model.Vendedor;
import com.ahoy.repository.VendedorRepository;
import com.ahoy.service.SequencialMatriculaService;
import com.ahoy.service.VendedorService;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @Mock
    private SequencialMatriculaService sequencialMatriculaService;

    @InjectMocks
    private VendedorService vendedorService;

    private VendedorDTO vendedorDTO;

    private Vendedor vendedor;

    @BeforeEach
    public void setUp() {
        // Configuração do mock do serviço sequencial de matrícula
        when(sequencialMatriculaService.proximoNumeroSequencial()).thenReturn(1); // Simula o próximo número sequencial
        vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome("Fulano");
        vendedorDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
        vendedorDTO.setDocumento("12345678900");
        vendedorDTO.setEmail("fulano@example.com");
        vendedorDTO.setTipoContratacao(TipoContratacao.CLT); // Exemplo de tipo de contratação
        vendedorDTO.setFilial("Filial A");
        
        vendedor = new Vendedor();
        vendedor.setMatricula("00000001-CLT");
        vendedor.setNome(vendedorDTO.getNome());
        vendedor.setDataNascimento(vendedorDTO.getDataNascimento());
        vendedor.setDocumento(vendedorDTO.getDocumento());
        vendedor.setEmail(vendedorDTO.getEmail());
        vendedor.setTipoContratacao(vendedorDTO.getTipoContratacao());
        vendedor.setFilial(vendedorDTO.getFilial());
    }
    
    @Test
    public void testCreateVendedor() {
        // Test Setup
        when(vendedorRepository.save(vendedor)).thenReturn(vendedor);
        
        // Test execution 
        // Chama o método de criação de vendedor
        Vendedor vendedorCriado = vendedorService.createVendedor(vendedorDTO);

        // Test Verify
        // Verificações/assertivas
        assertEquals("00000001-CLT", vendedorCriado.getMatricula()); // Verifica se a matrícula foi gerada corretamente

        // Verifica se o método save do repository foi chamado uma vez
        verify(vendedorRepository, times(1)).save(any(Vendedor.class));
    }

    @Test
    public void testDocumentoInvalido() {
        // Test Setup
        // Cria um vendedor com documento inválido
        vendedorDTO.setNome("Beltrano");
        vendedorDTO.setDocumento("33999999999"); // CPF inválido
        vendedorDTO.setEmail("beltrano@example.com");

        // when(vendedorRepository.save(vendedor)).thenReturn(vendedor);

        // Verifica se o método lança uma exceção de validação ao tentar criar o vendedor
        assertThrows(ConstraintViolationException.class, () -> {
            vendedorService.createVendedor(vendedorDTO);
        });
    }



}