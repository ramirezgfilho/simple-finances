package com.ramirezdev.simple_finances.domain.conta;


import com.ramirezdev.simple_finances.domain.user.User;
import jakarta.persistence.*;


import java.sql.Date;
import java.time.LocalDate;

@Table(name = "contas")
@Entity(name = "Contas")
public class    Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "valor")
    private Double valor;
    @Column(name = "dataalterada")
    private Date dataalterada;

    @Column(name = "mes")
    private Integer mes;
    @Column (name = "ano")
    private Integer ano;
    @Column (name = "pago")
    private Boolean pago;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Conta() {}




    public Conta(ContaDTO contaCadastrar) {
        this.descricao = contaCadastrar.descricao();
        this.valor = contaCadastrar.valor();
        this.user = contaCadastrar.user();
        this.dataalterada = contaCadastrar.dataalterada();
        this.mes = contaCadastrar.mes();
        this.ano = contaCadastrar.ano();
        this.pago = contaCadastrar.pago();
    }

    public void atualizaConta(ContaDTO contaDTO) {
        this.descricao = contaDTO.descricao();
        this.valor = contaDTO.valor();
        this.user = contaDTO.user();
        this.dataalterada = Date.valueOf(LocalDate.now());
        this.mes = contaDTO.mes();
        this.ano = contaDTO.ano();
        this.pago = contaDTO.pago();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Date getDataalterada() {
        return dataalterada;
    }

    public void setDataalterada(Date dataalterada) {
        this.dataalterada = dataalterada;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
