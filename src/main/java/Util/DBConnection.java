package Util;

import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3306/banco-inmobiliaria";
    private static final String USER = "root";
    private static final String PASSWORD = "4321";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
