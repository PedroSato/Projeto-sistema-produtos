package validadores;

import model.bean.Cliente;

public class ValidadorCliente {

    public static String validarCliente(Cliente cliente) {
        if (cliente == null) {
            return "Informe um cliente válido";
        }

        if (cliente.getNome() == null || "".equals(cliente.getNome())) {
            return "Nome não pode ser um campo vazio";
        }

        if (cliente.getSobrenome() == null || "".equals(cliente.getSobrenome())) {
            return "Sobrenome não pode ser um campo vazio";
        }

        if (cliente.getBairro() == null || "".equals(cliente.getBairro())) {
            return "Bairro não pode ser um campo vazio";
        }
        if (cliente.getCidade() == null || "".equals(cliente.getCidade())) {
            return "Cidade não pode ser um campo vazio";
        }
        if (cliente.getEstado() == null || "".equals(cliente.getEstado())) {
            return "Número não pode ser um campo vazio";
        }

        if (cliente.getRua() == null || "".equals(cliente.getRua())) {
            return "Rua não pode ser um campo vazio.";
        }

        if (cliente.getGenero() == null || "".equals(cliente.getGenero()) || (!cliente.getGenero().equals("Masculino")) && (!cliente.getGenero().equals("Feminino"))) {
            return "É necessário informar o gênero do cliente";
        }

        return null;
    }
}