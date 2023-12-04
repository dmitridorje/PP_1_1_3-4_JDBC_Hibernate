package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userServiceImpl = new UserServiceImpl();

        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Luka", "Doncic", (byte) 24);
        userServiceImpl.saveUser("Anthony", "Davis", (byte) 30);
        userServiceImpl.saveUser("Nikola", "Jokic", (byte) 29);
        userServiceImpl.saveUser("LeBron", "James", (byte) 39);

        userServiceImpl.removeUserById(2);
        userServiceImpl.getAllUsers().forEach(System.out::println);
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();

    }
}