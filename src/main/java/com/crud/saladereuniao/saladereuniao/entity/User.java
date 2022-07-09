package com.crud.saladereuniao.saladereuniao.entity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable=false)
    private String nome;

    @Column(name = "sobrenome", nullable=false)
    private String sobrenome;

    @Column(name = "idade", nullable=false)
    private Integer idade;

    @Column(name = "profissao", nullable=false)
    private String profissao;

    public User(Long id, String nome, String sobrenome, Integer idade, String profissao) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
        this.profissao = profissao;
    }

    public User(){};

    @Override
    public String toString() {
        return "User[" +
                "id=" + id +
                ", nome='" + nome +
                ", sobrenome='" + sobrenome  +
                ", idade='" + idade +
                ", profissao='" + profissao +
                ']';
    }
}
