"use client";
import "bootstrap/dist/css/bootstrap.css";
import {useState,useEffect} from "react";

const configPagina = {
    "Access-Control-Allow-Origin": "*",
    "Content-Type": "application/json"
};

export default function Home() {
    const [funcionarios,alteraFuncionarios] = useState([]);
    const [esperar,alteraEsperar] = useState(false);

    useEffect(() => {
        buscarFuncionarios();
    },[]);

    async function obtemMensagemErro(resposta) {
        if (resposta && !resposta.ok) {
            if (resposta.status) {
                const msg = await resposta.text();
                return msg;
            }

            return "Erro de servidor.";
        }
        
        return "";
    }

    async function buscarFuncionarios() {
        try {
            alteraEsperar(true);
            //const url = "http://localhost:8080/funcionario";
            const url = "http://ec2-52-14-172-211.us-east-2.compute.amazonaws.com:8080/funcionario";
            const resposta = await fetch(url,{method: "GET",headers: configPagina});
            const msg = await obtemMensagemErro(resposta);

            if (msg && msg !== "")
                throw new Error(msg);

            const dados = await resposta.json();
            alteraFuncionarios(dados);
        }
        catch(err) {
            console.log(err.message);
        }
        finally {
            alteraEsperar(false);
        }
    }

    function imprimirDadosTabela() {
        return funcionarios.map((funcionario) => (
            <tr key={funcionario.id}>
                <td>{funcionario.id}</td>
                <td>{funcionario.nome}</td>
                <td>{funcionario.cpf}</td>
                <td>{funcionario.salario}</td>
                <td>{funcionario.idade}</td>
                <td>{funcionario.dataAdmissao}</td>
            </tr>
        ));
    }

    return (
        <div className="container-fluid">
            <div className="row flex-nowrap">
                {
                    esperar
                    ?
                        <div className="position-absolute top-50 start-50 w-auto">
                            <div className="spinner-border text-secondary" role="status">
                                <span className="visually-hidden">Carregando...</span>
                            </div>
                        </div>
                    :
                        undefined
                }
                <div className="table-responsive">
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">Nome</th>
                                <th scope="col">CPF</th>
                                <th scope="col">Salário</th>
                                <th scope="col">Idade</th>
                                <th scope="col">Data de Admissão</th>
                            </tr>
                        </thead>
                        <tbody>
                            {imprimirDadosTabela()}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}
