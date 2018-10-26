package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {

    public static Connection getConnection() throws SQLException {
        
        Connection connection = null;
        
        String dbURL = "jdbc:mysql://localhost:3306/jdbc_projeto_sistema_produtos?useTimezone=true&serverTimezone=UTC";
        
        Properties properties = new Properties();
        properties.put("user","root");
        properties.put("password", "");
        
        connection = DriverManager.getConnection(dbURL, properties);
        
        return connection;
    }
}
