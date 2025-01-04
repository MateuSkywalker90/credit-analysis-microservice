package com.mateus.creditanalysis.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Users {

    private Long id;

    private String nome;

    private String sobrenome;

    private String cpf;

    private String telefone;

    private Double renda;

}
