package com.ramirezdev.simple_finances.domain.conta;

import com.ramirezdev.simple_finances.domain.user.User;
import jakarta.validation.constraints.NotBlank;

import java.sql.Date;

public record ContaDTO(

        @NotBlank(message = "Descrição não pode estar vazio.")
        String descricao,

        @NotBlank
        Double valor,

        Date dataalterada,

        User user,

        @NotBlank
        Integer mes,

        @NotBlank
        Integer ano,

        @NotBlank
        Boolean pago
) {
}

