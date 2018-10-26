package model.bean;

import java.util.Date;
import java.util.List;

public class Venda {

    private Integer id;
    private Date data;
    private Cliente cliente;
    private List<ItemVenda> carrinho;
    private double total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemVenda> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<ItemVenda> carrinho) {
        this.carrinho = carrinho;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
