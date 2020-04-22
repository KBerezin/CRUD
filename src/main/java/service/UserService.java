package service;

import exception.DBException;
import model.User;

import java.util.List;

public interface UserService {
    boolean addUser(String login, String password, String name) throws DBException;

    User getUserById(long id) throws DBException;

    User getUser(String login) throws DBException;

    boolean updateUser(User user) throws DBException;

    void deleteUser(long id) throws DBException;

    List<User> getAllUsers() throws DBException;
}
