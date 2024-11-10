package com.ramirezdev.simple_finances.domain.renda;

import com.ramirezdev.simple_finances.domain.user.User;

public record RendaDTO(String descricao, Double valor, User user, Integer mes, Integer ano) {
}
