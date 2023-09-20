package com.danilo.aws.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "funcionario")
@Getter
@Setter
@EqualsAndHashCode(of = {"id","cpf"})
@ToString
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf",unique = true)
    private String cpf;

    @Column(name = "salario")
    private BigDecimal salario;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "data_admissao")
    private LocalDate dataAdmissao;

}
