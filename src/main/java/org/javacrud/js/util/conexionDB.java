package org.javacrud.js.util;

import java.sql.*;

public class conexionDB {
    private static String url = "jdbc:postgresql://localhost/java_crud";
    private static String username = "javier";
    private static String password = "2525_ap";
    private static Connection connection;

    //singleton
    public static Connection getInstance() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}
