package com.ramirezdev.simple_finances.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(

        @NotBlank
        String login,

        @NotBlank
        String senha) {

}
