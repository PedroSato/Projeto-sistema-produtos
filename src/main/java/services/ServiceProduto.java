package services;

import connection.MockProduto;
import model.bean.Produto;
import validadores.ValidadorProduto;
import java.util.List;

public class ServiceProduto {

    public static String atualizarProduto(Produto produto) {
        String resposta;
        resposta = ValidadorProduto.validarProduto(produto);
        if (resposta == null) {
            try {
                MockProduto.atualizar(produto);
            } catch (Exception e) {
                return "erro na fonte de dados.";
            }
        }
        return resposta;
    }

    public static String cadastrarProduto(Produto produto) {
        String resposta = null;
        resposta = ValidadorProduto.validarProduto(produto);
        if (resposta == null) {
            try {
                MockProduto.inserir(produto);
            } catch (Exception e) {
                return "erro na fonte de dados.";
            }
        }
        return resposta;
    }

    public static String excluirProduto(Produto produto) {
        String resposta;
        resposta = ValidadorProduto.validarProduto(produto);
        if (resposta == null) {
            try {
                MockProduto.excluir(produto.getId());
            } catch (Exception e) {
                return "erro na fonte de dados";
            }
        }
        return resposta;
    }

    public static List<Produto> buscarProduto(String nome) {
        List<Produto> listaProdutos = null;

        try {
            if (nome == null || "".equals(nome)) {
                listaProdutos = MockProduto.listar();
            } else {
                listaProdutos = MockProduto.procurar(nome);
            }
        } catch (Exception e) {
            //log de erro
        }
        return listaProdutos;
    }

    public static Produto obterProduto(Produto produto) {
        Produto resposta = null;

        try {
            resposta = MockProduto.obter(produto.getId());
        } catch (Exception e) {
            //log
        }
        return resposta;
    }
}
