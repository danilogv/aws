package com.danilo.aws.servico;

import com.danilo.aws.dominio.Funcionario;
import com.danilo.aws.repositorio.FuncionarioRepositorio;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class FuncionarioServico {

    @Autowired
    private FuncionarioRepositorio repositorio;

    @Transactional
    public List<Funcionario> buscarTodos() {
        List<Funcionario> funcionarios = this.repositorio.buscarTodos();

        if (funcionarios.isEmpty()) {
            String msg = "Nenhum funcionário na base de dados.";
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,msg);
        }

        return funcionarios;
    }

}
