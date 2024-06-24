package com.ahoy.validation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import com.ahoy.dto.VendedorDTO;

public class VendedorGroupSequenceProvider implements DefaultGroupSequenceProvider<VendedorDTO> {

    public List<Class<?>> getValidationGroups(VendedorDTO entity) {
      List<Class<?>> groups = new ArrayList<>();
      
      /*
       * Informamos ao HibernateValidator para usar as validações default
       * definidas na classe VendedorDTO.
       */
      groups.add(VendedorDTO.class);
      
      if (entity != null) {
        /*
         * Lógica que determina o grupo de validação a ser aplicado ao bean.
         */
        if (entity.isPessoaJuridica()) {
          groups.add(PessoaJuridica.class);
        } else {
          groups.add(PessoaFisica.class);
        }
      }
      
      return groups;
    }
  
  }