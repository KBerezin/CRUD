package service;


import DAO.UserDAO;
import DAO.UserDAOFactory;
import exception.DBException;
import model.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userService;

    private UserDAO userDAO;

    private UserServiceImpl() throws SQLException, NamingException {
        this.userDAO = UserDAOFactory.getInstance().getUserDAO();
    }

    public static synchronized UserServiceImpl getUserService() {
        if (userService == null) {
            try {
                userService = new UserServiceImpl();
            } catch (SQLException | NamingException e) {
                e.printStackTrace();
                throw new DBException(e);
            }
        }
        return userService;
    }

    @Override
    public boolean addUser(String login, String password, String name) throws DBException {
        try {
            if (!userDAO.isUserExists(login)) {
                userDAO.addUser(login, password, name);
                return true;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return false;
    }

    @Override
    public User getUserById(long id) throws DBException {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public User getUser(String login) throws DBException {
        try {
            return userDAO.getUser(login);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws DBException {
        try {
            if (getUser(user.getLogin()) != null) {
                userDAO.updateUser(user);
                return true;
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
        return false;
    }

    @Override
    public void deleteUser(long id) throws DBException {
        try {
            if (getUserById(id) != null) {
                userDAO.deleteUser(id);
            }
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}
