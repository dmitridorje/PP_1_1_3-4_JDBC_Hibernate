package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDao userDaoJDBCImpl = new UserDaoJDBCImpl();

        userDaoJDBCImpl.createUsersTable();

        userDaoJDBCImpl.saveUser("Luka", "Doncic", (byte) 24);
        userDaoJDBCImpl.saveUser("Anthony", "Davis", (byte) 30);
        userDaoJDBCImpl.saveUser("Nikola", "Jokic", (byte) 29);
        userDaoJDBCImpl.saveUser("LeBron", "James", (byte) 39);

        userDaoJDBCImpl.removeUserById(1);
        System.out.println(userDaoJDBCImpl.getAllUsers());
        userDaoJDBCImpl.cleanUsersTable();
        userDaoJDBCImpl.dropUsersTable();
    }
}
