package com.danilo.aws.repositorio;

import com.danilo.aws.dominio.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepositorio extends JpaRepository<Funcionario,Long> {

    @Query(value =
        "SELECT * " +
        "FROM funcionario " +
        "ORDER BY nome"
    ,nativeQuery = true)
    List<Funcionario> buscarTodos();

}
