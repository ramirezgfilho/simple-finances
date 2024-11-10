package com.ramirezdev.simple_finances.domain.conta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ramirezdev.simple_finances.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosConta(
        @JsonAlias("Descricao") String descricao,
        @JsonAlias("Valor") Double valor,
        @JsonAlias("Vencimento") Date vencimento,
        @JsonAlias("Mes") Integer mes,
        @JsonAlias("Ano") Integer ano,
        @JsonAlias("Pago") Boolean pago
)

{
    @Override
    public String toString() {
        return "Dados da série:" +
                "\n Descricao: " + descricao +
                "\n Valor: " + valor +
                "\n Vencimento: " + vencimento +
                "\n Mes: " + mes +
                "\n Ano: " + ano +
                "\n Pago: " + pago;
    }
}



