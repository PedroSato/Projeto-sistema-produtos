package validadores;

import model.bean.Produto;

public class ValidadorProduto {

    public static String validarProduto(Produto produto) {
        if (produto == null) {
            return "Informe um produto válido";
        }

        if (produto.getEstoque() <= 0) {
            return "Quantidade não pode ser um campo vazio ou menor ou igual a zero.";
        }

        if (produto.getFabricante() == null || "".equals(produto.getFabricante())) {
            return "Fabricante não pode ser um campo vazio";
        }

        if (produto.getPreco() < 0) {
            return "Preço não pode ser menor que zero.";
        }
        
        if (produto.getNome() == null){
            return "Informe um nome válido.";
        }
        
        return null;
    }
}
