package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {

            // Для возраста - TINYINT, т.к. это прямой аналог типа byte в MySQL

            String sql = "CREATE TABLE IF NOT EXISTS Users " +
                    "(id BIGINT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(64), " +
                    " lastName VARCHAR(64), " +
                    " age TINYINT, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "DROP TABLE IF EXISTS Users;";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStmt = conn.prepareStatement(sql)) {
            preparedStmt.setString(1, name);
            preparedStmt.setString(2, lastName);

            // Для возраста - setByte
            preparedStmt.setByte(3, age);
            preparedStmt.executeUpdate();
            System.out.printf("User с именем %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM Users WHERE id=?";
        try (Connection conn = Util.getConnection();
             PreparedStatement preparedStmt = conn.prepareStatement(sql)) {
            preparedStmt.setLong(1, id);
            preparedStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age FROM Users";

        try (Connection conn = Util.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));

                // Для возраста - getByte
                user.setAge(rs.getByte("age"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM Users";
        try (Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement()){
            stmt.executeUpdate(query);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

}
