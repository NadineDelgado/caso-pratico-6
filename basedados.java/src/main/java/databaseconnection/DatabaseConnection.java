package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Metodo para obter conexão com o baco de dados
    public static Connection getConnection(String url, String user, String password) throws SQLException {
        // Obter conexão utilizando o DriveManager
        return DriverManager.getConnection(url, user, password);
    }
}
