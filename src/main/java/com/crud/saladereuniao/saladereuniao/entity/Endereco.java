package com.crud.saladereuniao.saladereuniao.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="endereco")
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cep", nullable=false)
    private String cep;

    @Column(name = "logradouro", nullable=false)
    private String logradouro;
    @Column(name = "complemento", nullable=false)
    private String complemento;
    @Column(name = "bairro", nullable=false)
    private String bairro;
    @Column(name = "localidade", nullable=false)
    private String localidade;
    @Column(name = "uf", nullable=false)
    private String uf;
    @Column(name = "ibge", nullable=false)
    private String ibge;
    @Column(name = "ddd", nullable=false)
    private String DDD;
}
