package com.danilo.aws.controle;

import com.danilo.aws.dominio.Funcionario;
import com.danilo.aws.servico.FuncionarioServico;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/funcionario")
@AllArgsConstructor
public class FuncionarioControle {

    @Autowired
    private FuncionarioServico servico;

    @GetMapping
    public ResponseEntity<List<Funcionario>> buscarTodos() {
        try {
            List<Funcionario> funcionarios = this.servico.buscarTodos();
            return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
        }
        catch (Exception ex) {
            if (!(ex instanceof ResponseStatusException)) {
                String msg = "Erro de servidor";
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,msg);
            }
        }

        return null;
    }

}
