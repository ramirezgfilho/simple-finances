package com.ramirezdev.simple_finances.domain.conta;

import com.ramirezdev.simple_finances.domain.user.User;

import java.sql.Date;

public record ContaDTOPagar(Long id, String descricao, Double valor, Date dataalterada, User user, Integer mes, Integer ano, Boolean pago) {
}
