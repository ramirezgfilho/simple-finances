package com.ramirezdev.simple_finances.domain.conta;

import com.ramirezdev.simple_finances.domain.user.User;

import java.sql.Date;

public record ContaDTO(String descricao, Double valor, Date dataalterada, User user, Integer mes, Integer ano, Boolean pago) {
}

