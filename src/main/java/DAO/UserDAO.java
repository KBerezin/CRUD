package DAO;


import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    void addUser(String login, String password, String name) throws SQLException;

    User getUserById(long id) throws SQLException;

    User getUser(String login) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(long id) throws SQLException;

    boolean isUserExists(String login) throws SQLException;

    List<User> getAllUsers() throws SQLException;
}
