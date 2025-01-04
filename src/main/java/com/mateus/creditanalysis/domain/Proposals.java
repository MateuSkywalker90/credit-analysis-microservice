package com.mateus.creditanalysis.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proposals {

    private Long id;

    private Double valorSolicitado;

    public Double getValorSolicitado() {
        return valorSolicitado;
    }

    private int prazoPagamento;

    public int getPrazoPagamento() {
        return prazoPagamento;
    }

    private Boolean aprovada;

    public void setAprovada(Boolean aprovada) {
        this.aprovada = aprovada;
    }

    private boolean integrada;

    private String observacao;

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    private Users usuario;

    public Users getUsuario() {
        return usuario;
    }
}
