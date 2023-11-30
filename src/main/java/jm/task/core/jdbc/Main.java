package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        UserDao userDaoHibernateImpl = new UserDaoHibernateImpl();

        userDaoHibernateImpl.dropUsersTable();
        userDaoHibernateImpl.createUsersTable();
        userDaoHibernateImpl.saveUser("Luka", "Doncic", (byte) 24);
        userDaoHibernateImpl.saveUser("Anthony", "Davis", (byte) 30);
        userDaoHibernateImpl.saveUser("Nikola", "Jokic", (byte) 29);
        userDaoHibernateImpl.saveUser("LeBron", "James", (byte) 39);

        userDaoHibernateImpl.removeUserById(2);
        userDaoHibernateImpl.getAllUsers().forEach(System.out::println);
        userDaoHibernateImpl.cleanUsersTable();
        userDaoHibernateImpl.dropUsersTable();

    }
}