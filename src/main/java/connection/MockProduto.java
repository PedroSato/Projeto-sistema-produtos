package connection;

import utils.ConnectionUtils;
import model.bean.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockProduto {

    public static void atualizar(Produto produto)
            throws SQLException, Exception {
        String SQL = "UPDATE produto SET nome=?, estoque=?, fabricante=?, preco=? WHERE (id=?)";

        Connection conn = null;

        PreparedStatement prepStmt = null;
        try {

            conn = ConnectionUtils.getConnection();

            prepStmt = conn.prepareStatement(SQL);

            prepStmt.setString(1, produto.getNome());
            prepStmt.setInt(2, produto.getEstoque());
            prepStmt.setString(3, produto.getFabricante());
            prepStmt.setFloat(4, produto.getPreco());

        } catch (Exception e) {

        }
    }

    public static Produto obter(Integer id)
            throws SQLException, Exception {
        String SQL = "SELECT * FROM produto WHERE (id=? AND ativo=?)";

        Connection conn = null;
        PreparedStatement prepStmt = null;
        ResultSet result = null;

        try {
            conn = ConnectionUtils.getConnection();

            prepStmt = conn.prepareStatement(SQL);
            prepStmt.setInt(1, id);
            prepStmt.setBoolean(2, true);
            result = prepStmt.executeQuery();

            if (result.next()) {
                Produto produto = new Produto();
                produto.setId(result.getInt("id"));
                produto.setEstoque(result.getInt("estoque"));
                produto.setFabricante(result.getString("fabricante"));
                produto.setNome(result.getString("nome"));
                produto.setPreco(result.getFloat("preco"));

                return produto;
            }
        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            if (prepStmt != null && !prepStmt.isClosed()) {
                prepStmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return null;
    }

    public static void inserir(Produto produto)
            throws SQLException, Exception {
        String SQL = "INSERT INTO produto (estoque, nome, fabricante, preco, ativo) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement prepStmt = null;

        try {
            conn = ConnectionUtils.getConnection();
            prepStmt = conn.prepareStatement(SQL);
            prepStmt.setInt(1, produto.getEstoque());
            prepStmt.setString(2, produto.getNome());
            prepStmt.setString(3, produto.getFabricante());
            prepStmt.setFloat(4, produto.getPreco());
            prepStmt.setBoolean(5, true);

            prepStmt.execute();
        } catch (Exception e) {
            //gerar log de erro
        } finally {

            if (prepStmt != null && !prepStmt.isClosed()) {
                prepStmt.close();
            }

            if (conn != null & !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public static void excluir(Integer id)
            throws SQLException, Exception {
        String SQL = "UPDATE produto SET ativo=? WHERE (id=?)";

        Connection conn = null;
        PreparedStatement prepStmt = null;

        try {
            conn = ConnectionUtils.getConnection();
            prepStmt = conn.prepareStatement(SQL);
            prepStmt.setBoolean(1, false);
            prepStmt.setInt(2, id);

            prepStmt.execute();
        } catch (Exception e) {

        } finally {
            if (prepStmt != null && !prepStmt.isClosed()) {
                prepStmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

    public static List<Produto> listar()
            throws SQLException, Exception {
        String SQL = "SELECT * FROM produto WHERE (ativo=?)";

        List<Produto> listaProdutos = null;

        PreparedStatement prepStmt = null;
        Connection conn = null;

        ResultSet result = null;

        try {
            conn = ConnectionUtils.getConnection();
            prepStmt.setBoolean(1, true);

            result = prepStmt.executeQuery();

            while (result.next()) {

                if (listaProdutos == null) {
                    listaProdutos = new ArrayList<Produto>();
                }

                Produto produto = new Produto();
                produto.setId(result.getInt("id"));
                produto.setEstoque(result.getInt("estoque"));
                produto.setFabricante(result.getString("fabricante"));
                produto.setNome(result.getString("nome"));
                produto.setPreco(result.getFloat("preco"));
                listaProdutos.add(produto);
            }

        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }

            if (prepStmt != null && !prepStmt.isClosed()) {
                prepStmt.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
        return listaProdutos;
    }

    public static List<Produto> procurar(String valor)
            throws SQLException, Exception {
        String SQL = "SELECT * FROM produto WHERE ((UPPER(nome) LIKE UPPER(?) AND ativo=?))";
        List<Produto> listaProdutos = null;

        Connection connection = null;

        PreparedStatement preparedStatement = null;

        ResultSet result = null;
        try {

            connection = ConnectionUtils.getConnection();

            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, valor);
            preparedStatement.setBoolean(2, true);
            result = preparedStatement.executeQuery();

            while (result.next()) {
                if (listaProdutos == null) {
                    listaProdutos = new ArrayList<Produto>();
                }

                Produto produto = new Produto();
                produto.setId(result.getInt("id"));
                produto.setNome(result.getString("nome"));
                produto.setEstoque(result.getInt("estoque"));
                produto.setFabricante("fabricante");
                produto.setPreco(result.getFloat("preco"));

                listaProdutos.add(produto);
            }
        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return listaProdutos;
    }
}

