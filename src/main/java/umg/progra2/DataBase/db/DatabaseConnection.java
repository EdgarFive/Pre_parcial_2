package umg.progra2.DataBase.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/db_telebot"; // Cambia por tu URL
    private static final String USER = "root"; // Cambia por tu usuario de MySQL
    private static final String PASSWORD = "59104667"; // Cambia por tu contraseña

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
