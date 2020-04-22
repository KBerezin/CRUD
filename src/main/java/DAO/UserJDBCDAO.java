package DAO;

import model.User;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserJDBCDAO implements UserDAO {
    private Connection connection;

    public UserJDBCDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(String login, String password, String name) throws SQLException {
        connection.setAutoCommit(false);
        try {
            JDBCUtil.update(connection,
                    "INSERT INTO usr (login, password, name)" +
                            " VALUES (?, ?, ?);",
                    login, password, name);
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public User getUserById(long id) throws SQLException {
        return JDBCUtil.select(connection, "SELECT * FROM usr WHERE id=?", this::userGenerator, id);
    }

    @Override
    public User getUser(String login) throws SQLException {
        return JDBCUtil.select(connection, "SELECT * FROM usr WHERE login=?",
                this::userGenerator, login);
    }

    @Override
    public void updateUser(User user) throws SQLException {
        connection.setAutoCommit(false);
        try {
            JDBCUtil.update(connection, "UPDATE usr SET login = ?, password = ?, name = ? WHERE id = ?;",
                    user.getLogin(), user.getPassword(), user.getName(), user.getId());
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void deleteUser(long id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            JDBCUtil.update(connection, "DELETE FROM usr WHERE id = ?", id);
        } catch (Exception e) {
            e.printStackTrace();
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return JDBCUtil.select(connection, "SELECT * FROM usr",
                resultSet -> {
                    List<User> result = Collections.synchronizedList(new ArrayList<>());
                    User user;
                    while ((user = userGenerator(resultSet)) != null) {
                        result.add(user);
                    }
                    return result;
                });
    }

    private User userGenerator(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4));
        }
        return user;
    }
}
