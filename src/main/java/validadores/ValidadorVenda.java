package validadores;

import model.bean.Venda;

public class ValidadorVenda {

    public static String validarVenda(Venda venda) {
        String resposta = null;

        if (venda == null) {
            return "Venda Inválida";
        }
        if (venda.getCliente() == null) {
            return "Venda precisa de um cliente";
        }
        if (venda.getCarrinho().isEmpty()) {
            return "Venda precisa de ao menos um produto";
        }

        return resposta;
    }
}
