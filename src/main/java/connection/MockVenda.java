package connection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.bean.Venda;
import model.bean.ItemVenda;
import utils.ConnectionUtils;

public class MockVenda {

    public void inserir(Venda venda) throws Exception {
        String SQL = "INSERT INTO venda (id_cliente, data, total) VALUES (?, ?, ?)";
        ResultSet result = null;
        Connection conn = null;
        PreparedStatement prepStmt = null;

        try {

            conn = ConnectionUtils.getConnection();

            prepStmt = conn.prepareStatement(SQL, prepStmt.RETURN_GENERATED_KEYS);
            prepStmt.setInt(1, venda.getCliente().getId());
            prepStmt.setDate(2, new Date(venda.getData().getTime()));
            prepStmt.setDouble(3, venda.getTotal());

            prepStmt.execute();

            result = prepStmt.getGeneratedKeys();
            result.next();

            int idVenda = result.getInt(1);

            for (ItemVenda iv : venda.getCarrinho()) {
                SQL = "INSERT INTO item_venda (id_produto, id_venda, quantidade, valorunitario) VALUES (?, ?, ?, ?)";
                prepStmt = conn.prepareStatement(SQL);
                prepStmt.setInt(1, iv.getProduto().getId());
                prepStmt.setInt(2, idVenda);
                prepStmt.setInt(3, iv.getQuantidade());
                prepStmt.setFloat(4, iv.getValorUnitario());
                prepStmt.execute();

            }
        } catch (Exception e) {
            //log de erro
        }
    }

    public void atualizar(Venda venda) throws Exception {
        String SQL = "UPDATE venda SET id_cliente=?, data=?, total=? WHERE id_venda=?";
        ResultSet result = null;
        Connection conn = null;
        PreparedStatement prepStmt = null;

        try {
            
            conn = ConnectionUtils.getConnection();
            prepStmt = conn.prepareStatement(SQL);
            
            prepStmt.setInt(1, venda.getCliente().getId());
            prepStmt.setDate(2, new Date(venda.getData().getTime()));
            prepStmt.setDouble(3, venda.getTotal());
            prepStmt.setInt(4, venda.getId());
            int idVenda = venda.getId();
            prepStmt.execute();

            SQL = "DELETE FROM item_venda WHERE id_venda=?";
            prepStmt = conn.prepareStatement(SQL);
            prepStmt.setInt(1, idVenda);
            prepStmt.execute();
        }

        for (ItemVenda iv : venda.getItens()) {
            if (iv.getCodigo() == 0) {
                SQL = "INSERT INTO item_venda (id, id_venda, quantidade, valorUnitario) VALUES (?, ?, ?, ?)";
                ps = c.getConexao().prepareStatement(sql);
                ps.setInt(1, iv.getProduto().getCodigo());
                ps.setInt(2, iv.getVenda().getCodigo());
                ps.setInt(3, iv.getQuantidade());
                ps.setDouble(4, iv.getValorUnitario());
                ps.execute();
            } else {
                sql = "UPDATE TBITEMVENDA SET CODIGOPRODUTO=?, CODIGOVENDA=?, QUANTIDADE=?, VALORUNITARIO=? WHERE CODIGO=?";
                ps = c.getConexao().prepareStatement(sql);
                ps.setInt(1, iv.getProduto().getCodigo());
                ps.setInt(2, iv.getVenda().getCodigo());
                ps.setInt(3, iv.getQuantidade());
                ps.setDouble(4, iv.getValorUnitario());
                ps.setInt(5, iv.getCodigo());
                ps.execute();
            }

            if (venda.getSituacao() == Situacao.FINALIZADA) {
                ProdutoDAO produtoDAO = new ProdutoDAO();
                produtoDAO.saidaEstoque(c, iv.getProduto().getCodigo(), iv.getQuantidade());
            }
        }

        c.confirmar();
    }
}
