package com.ahoy.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import com.ahoy.enums.TipoContratacao;
import com.ahoy.validation.PessoaFisica;
import com.ahoy.validation.PessoaJuridica;
import com.ahoy.validation.VendedorGroupSequenceProvider;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@GroupSequenceProvider(value = VendedorGroupSequenceProvider.class)
public class VendedorDTO {
    
    private String matricula;

    @NotBlank
    private String nome;

    private LocalDate dataNascimento;

    @NotBlank
    @CPF(groups = PessoaFisica.class, message = "CPF inválido")
    @CNPJ(groups = PessoaJuridica.class, message = "CNPJ inválido")
    private String documento;

    @NotBlank
    @Email(message = "Email deve ser válido")
    @Pattern(regexp = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Email deve ser válido")
    private String email;

    @NotNull
    private TipoContratacao tipoContratacao;

    @NotBlank
    private String filial;

    // Getters and setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoContratacao getTipoContratacao() {
        return tipoContratacao;
    }

    public void setTipoContratacao(TipoContratacao tipoContratacao) {
        this.tipoContratacao = tipoContratacao;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public boolean isPessoaJuridica() {
        return TipoContratacao.PESSOA_JURIDICA.equals(tipoContratacao);
    }

    @Override
    public String toString() {
        return "VendedorDTO [dataNascimento=" + dataNascimento + ", documento=" + documento + ", email=" + email
                + ", filial=" + filial + ", matricula=" + matricula + ", nome=" + nome + ", tipoContratacao="
                + tipoContratacao + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
        result = prime * result + ((documento == null) ? 0 : documento.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((tipoContratacao == null) ? 0 : tipoContratacao.hashCode());
        result = prime * result + ((filial == null) ? 0 : filial.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VendedorDTO other = (VendedorDTO) obj;
        if (matricula == null) {
            if (other.matricula != null)
                return false;
        } else if (!matricula.equals(other.matricula))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (dataNascimento == null) {
            if (other.dataNascimento != null)
                return false;
        } else if (!dataNascimento.equals(other.dataNascimento))
            return false;
        if (documento == null) {
            if (other.documento != null)
                return false;
        } else if (!documento.equals(other.documento))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (tipoContratacao == null) {
            if (other.tipoContratacao != null)
                return false;
        } else if (!tipoContratacao.equals(other.tipoContratacao))
            return false;
        if (filial == null) {
            if (other.filial != null)
                return false;
        } else if (!filial.equals(other.filial))
            return false;
        return true;
    }

    
}