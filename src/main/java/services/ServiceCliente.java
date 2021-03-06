package services;
//PACOTE COM OS SERVIÇOS DE CRUD DE CLIENTES

import connection.MockCliente;
import model.bean.Cliente;
import validadores.ValidadorCliente;
import java.util.List;

public class ServiceCliente {

    /* CADASTRA UM CLIENTE SE A RESPOSTA DO VALIDADOR FOR NULL E RETORNA UM ERRO CASO TENHA HAVIDO UM ERRO NA 
     INSERÇÃO NO BANCO, SALVANDO O ERRO NO LOG*/
    public static String cadastrarCliente(Cliente cliente) {
        String resposta;

        resposta = ValidadorCliente.validarCliente(cliente);

        if (resposta == null) {
            try {
                MockCliente.inserir(cliente);
            } catch (Exception e) {
                resposta = "erro na fonte de dados";
                //escrever o erro no log
            }
        }

        return resposta;
    }
    //EXCLUI CLIENTE RECEBENDO UM CLIENTE COMO PARAMETRO 
    public static String excluirCliente(Cliente cliente) {
        String resposta;

        resposta = ValidadorCliente.validarCliente(cliente);

        if (resposta == null) {
            try {
                MockCliente.excluir(cliente.getId());
            } catch (Exception e) {
                return "erro na fonte de dados";
            }
        }
        return resposta;
    }

    public static List<Cliente> buscarCliente(String nome) {
        List<Cliente> listaResposta = null;

        try {
            if (nome == null || "".equals(nome)) {
                listaResposta = MockCliente.listar();
            } else {
                listaResposta = MockCliente.procurar(nome);
            }
        } catch (Exception e) {
            //gerar o arquivo de log de erro
        }
        return listaResposta;
    }

    public static String atualizarCliente(Cliente cliente) {
        String resposta;

        resposta = ValidadorCliente.validarCliente(cliente);

        if (resposta == null) {
            try {
                MockCliente.atualizar(cliente);
            } catch (Exception e) {
                resposta = "erro na fonte de dados";
            }
        }

        return resposta;
    }

    public static Cliente obterCliente(Cliente cliente) {
       Cliente resposta = null;

        try {
            MockCliente.obter(cliente.getId());
        } catch (Exception e) {
            //criar arquivo de log de erro
        }

        return resposta;
    }
}
