package connection;

import utils.ConnectionUtils;
import model.bean.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockCliente {
    
    
    public static void inserir(Cliente cliente)
            throws SQLException, Exception {
        
        String sql = "INSERT INTO cliente (nome, sobrenome, data_nasc, numero_contato, email, genero, cpf, rua, numero_endereco, bairro, cidade, " 
                + "estado, complemento, ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
            try {
            connection = ConnectionUtils.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getSobrenome());
            Timestamp t = new Timestamp(cliente.getDataNasc().getTime());
            preparedStatement.setTimestamp(3, t);
            preparedStatement.setString(4, cliente.getNumeroContato());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getGenero());
            preparedStatement.setString(7, cliente.getCpf());
            preparedStatement.setString(8, cliente.getRua());
            preparedStatement.setString(9, cliente.getNumeroEndereco());
            preparedStatement.setString(10, cliente.getBairro());
            preparedStatement.setString(11, cliente.getCidade());
            preparedStatement.setString(12, cliente.getEstado());
            preparedStatement.setString(13, cliente.getComplemento());
            preparedStatement.setBoolean(14, true);

            preparedStatement.execute();
        } finally {
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
    
    public static void atualizar(Cliente cliente)
            throws SQLException, Exception {
        String sql = "UPDATE cliente SET nome=?, sobrenome=?, data_nasc=?, numero_contato=?, email=?, genero=?, cpf=?, rua=?, numero_endereco=?,"
                + "bairro=?, cidade=?, estado=?, complemento=? WHERE (id=?)";

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        try {

            connection = ConnectionUtils.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getSobrenome());
            Timestamp t = new Timestamp(cliente.getDataNasc().getTime());
            preparedStatement.setTimestamp(3, t);
            preparedStatement.setString(4, cliente.getNumeroContato());
            preparedStatement.setString(5, cliente.getEmail());
            preparedStatement.setString(6, cliente.getGenero());
            preparedStatement.setString(7, cliente.getCpf());
            preparedStatement.setString(8, cliente.getRua());
            preparedStatement.setString(9, cliente.getNumeroEndereco());
            preparedStatement.setString(10, cliente.getBairro());
            preparedStatement.setString(11, cliente.getCidade());
            preparedStatement.setString(12, cliente.getEstado());
            preparedStatement.setString(13, cliente.getComplemento());
            preparedStatement.setInt(14, cliente.getId());

            preparedStatement.execute();
        } finally {

            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }

            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
    
    public static void excluir(Integer id) throws SQLException, Exception {

        String sql = "UPDATE cliente SET ativo=? WHERE (id=?)";

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        try {

            connection = ConnectionUtils.getConnection();

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, id);

            preparedStatement.execute();
        } finally {
            //Se o statement ainda estiver aberto, realiza seu fechamento
            if (preparedStatement != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
            }
            //Se a conexão ainda estiver aberta, realiza seu fechamento
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }
    
    public static List<Cliente> listar()
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM cliente WHERE (ativo=?)";
        List<Cliente> listaClientes = null;

        Connection connection = null;

        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {

            connection = ConnectionUtils.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBoolean(1, true);

            result = preparedStatement.executeQuery();

            while (result.next()) {
                if (listaClientes == null) {
                    listaClientes = new ArrayList<Cliente>();
                }

                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("id"));
                cliente.setNome(result.getString("nome"));
                cliente.setSobrenome(result.getString("sobrenome"));
                Date d = new Date(result.getTimestamp("data_nasc").getTime());
                cliente.setDataNasc(d);
                cliente.setGenero(result.getString("genero"));
                listaClientes.add(cliente);
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
        return listaClientes;
    }
    
    public static List<Cliente> procurar(String valor)
            throws SQLException, Exception {
        
        String SQL = "SELECT * FROM cliente WHERE ((UPPER(nome) LIKE UPPER(?) "
                + "OR UPPER(cliente.sobrenome) LIKE UPPER(?)) AND ativo=?)";
        List<Cliente> listaClientes = null;

        Connection connection = null;


        PreparedStatement preparedStatement = null;

        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();

            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, "%" + valor + "%");
            preparedStatement.setString(2, "%" + valor + "%");
            preparedStatement.setBoolean(3, true);

            result = preparedStatement.executeQuery();


            while (result.next()) {
                if (listaClientes == null) {
                    listaClientes = new ArrayList<Cliente>();
                }
                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("id"));
                cliente.setNome(result.getString("nome"));
                cliente.setSobrenome(result.getString("sobrenome"));
                Date d = new Date(result.getTimestamp("data_nasc").getTime());
                cliente.setDataNasc(d);
                cliente.setGenero(result.getString("genero"));
                listaClientes.add(cliente);
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
        return listaClientes;
    }
    
    public static Cliente obter(Integer id)
            throws SQLException, Exception {
        String SQL = "SELECT * FROM cliente WHERE (id=? AND ativo=?)";

        Connection connection = null;

        PreparedStatement preparedStatement = null;

        ResultSet result = null;
        try {

            connection = ConnectionUtils.getConnection();

            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, true);
            result = preparedStatement.executeQuery();

            if (result.next()) {

                Cliente cliente = new Cliente();
                cliente.setId(result.getInt("id"));
                cliente.setNome(result.getString("nome"));
                cliente.setSobrenome(result.getString("sobrenome"));
                Date d = new Date(result.getTimestamp("data_nasc").getTime());
                cliente.setDataNasc(d);
                cliente.setGenero(result.getString("genero"));

                return cliente;
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
        return null;
    }
}