package com.ahoy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ahoy.dto.VendedorDTO;
import com.ahoy.enums.TipoContratacao;
import com.ahoy.model.Vendedor;
import com.ahoy.repository.VendedorRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@ExtendWith(MockitoExtension.class)
class VendedorServiceTest {

    @Mock
    private VendedorRepository vendedorRepository;

    @Mock
    private SequencialMatriculaService sequencialMatriculaService;

    @InjectMocks
    private VendedorService vendedorService;

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void createVendedor_shouldCreateAndSaveVendedor_withCorrectMatricula() {
        // Arrange
        VendedorDTO vendedorDTO = new VendedorDTO();
        vendedorDTO.setNome("João da Silva");
        vendedorDTO.setDataNascimento(LocalDate.of(1990, 1, 1));
        vendedorDTO.setDocumento("12345678901");
        vendedorDTO.setEmail("joao.silva@example.com");
        vendedorDTO.setTipoContratacao(TipoContratacao.CLT);
        vendedorDTO.setFilial("1");

        Vendedor expectedVendedor = createTestVendedor();
        expectedVendedor.setMatricula("00000001-CLT"); // Assuming sequencialMatriculaService.proximoNumeroSequencial() returns 1

        when(sequencialMatriculaService.proximoNumeroSequencial()).thenReturn(1);
        when(vendedorRepository.save(any(Vendedor.class))).thenReturn(expectedVendedor);

        // Act
        Vendedor createdVendedor = vendedorService.createVendedor(vendedorDTO);

        // Assert
        assertEquals(expectedVendedor, createdVendedor);
        verify(vendedorRepository, times(1)).save(any(Vendedor.class));
        verify(sequencialMatriculaService, times(1)).proximoNumeroSequencial();
    }

    @Test
    void getVendedorByMatricula_shouldReturnVendedor_whenExists() {
        // Arrange
        String matricula = "00000001-CLT";
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula(matricula);
        when(vendedorRepository.findByMatricula(matricula)).thenReturn(vendedor);

        // Act
        Optional<Vendedor> result = vendedorService.getVendedorByMatricula(matricula);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(vendedor, result.get());
        verify(vendedorRepository, times(1)).findByMatricula(matricula);
    }

    @Test
    void getVendedorByMatricula_shouldReturnEmptyOptional_whenNotExists() {
        // Arrange
        String matricula = "00000001-CLT";
        when(vendedorRepository.findByMatricula(matricula)).thenReturn(null);

        // Act
        Optional<Vendedor> result = vendedorService.getVendedorByMatricula(matricula);

        // Assert
        assertFalse(result.isPresent());
        verify(vendedorRepository, times(1)).findByMatricula(matricula);
    }

    @Test
    void getAllVendedores_shouldReturnListOfVendedores() {
        // Arrange
        List<Vendedor> vendedores = List.of(new Vendedor(), new Vendedor());
        when(vendedorRepository.findAll()).thenReturn(vendedores);

        // Act
        List<Vendedor> result = vendedorService.getAllVendedores();

        // Assert
        assertEquals(vendedores, result);
        verify(vendedorRepository, times(1)).findAll();
    }

    @Test
    void deleteVendedor_shouldDeleteVendedor_whenExists() {
        // Arrange
        Vendedor vendedor = createTestVendedor();
        Long id = 1L;
        vendedor.setId(id);

        // Simular que o vendedor existe inicialmente
        when(vendedorRepository.findById(id)).thenReturn(Optional.of(vendedor));

        // Act
        Optional<Vendedor> foundBeforeDeletion = vendedorService.getVendedorById(id);
        vendedorService.deleteVendedor(id);
        when(vendedorRepository.findById(id)).thenReturn(Optional.empty());  // Simular que o vendedor foi deletado após a chamada de delete
        Optional<Vendedor> foundAfterDeletion = vendedorService.getVendedorById(id);

        // Assert
        assertTrue(foundBeforeDeletion.isPresent(), "Vendedor should exist before deletion");
        verify(vendedorRepository, times(1)).deleteById(id);
        verify(vendedorRepository, times(2)).findById(id); // Verifica se o findById foi chamado duas vezes, antes e depois da deleção
        assertFalse(foundAfterDeletion.isPresent(), "Vendedor should not exist after deletion");
    }

    @Test
    void testValidEmail() {
        // Arrange
        Vendedor vendedor = createTestVendedor();

        // Act
        Set<ConstraintViolation<Vendedor>> violations = validator.validate(vendedor);

        // Assert
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidEmail() {
        // Arrange
        Vendedor vendedor = createTestVendedor();
        vendedor.setEmail("null@null");

        // Act
        Set<ConstraintViolation<Vendedor>> violations = validator.validate(vendedor);

        // Assert
        assertFalse(violations.isEmpty());

        ConstraintViolation<Vendedor> violation = violations.iterator().next();
        assertEquals("Email deve ser válido", violation.getMessage());
    }

    // Utility method to create a Vendedor object for tests
    private Vendedor createTestVendedor() {
        Vendedor vendedor = new Vendedor();
        vendedor.setMatricula("000000001-CLT");
        vendedor.setNome("João da Silva");
        vendedor.setEmail("joao.silva@example.com"); // Assuming a valid email is needed for the test
        vendedor.setTipoContratacao(TipoContratacao.CLT);
        vendedor.setDocumento("12345678901");
        vendedor.setFilial("1");
        return vendedor;
    }
}