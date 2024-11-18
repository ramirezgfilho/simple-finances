package com.ramirezdev.simple_finances.domain.renda;

import com.ramirezdev.simple_finances.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Renda")
@Table(name = "renda")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Renda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "mes")
    private Integer mes;

    @Column(name = "ano")
    private Integer ano;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Renda(RendaDTO rendaDTO) {
        this.descricao = rendaDTO.descricao();
        this.valor = rendaDTO.valor();
        this.user = rendaDTO.user();
        this.mes = rendaDTO.mes();
        this.ano = rendaDTO.ano();
    }


    public Double getValor() {
        return valor;
    }
}
