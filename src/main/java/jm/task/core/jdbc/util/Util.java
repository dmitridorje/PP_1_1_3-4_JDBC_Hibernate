package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING = "jdbc:mysql://localhost/mysql_demo";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }
}
