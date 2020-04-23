package DAO;


import util.DBHelper;
import util.PropertyReader;

import java.sql.SQLException;
import java.util.Properties;

public class UserDAOFactory {

    private static UserDAOType userDAOType;
    private static UserDAOFactory userDAOFactory;

    private UserDAOFactory() {
    }

    public static synchronized UserDAOFactory getInstance() {
        if (userDAOFactory == null) {
            Properties properties = PropertyReader.getProperty("dao.properties");
            if ("hibernate".equalsIgnoreCase(properties.getProperty("daotype"))) {
                userDAOType = UserDAOType.HIBERNATE;
            } else {
                userDAOType = UserDAOType.JDBC;
            }
            userDAOFactory = new UserDAOFactory();
        }
        return userDAOFactory;
    }

    public UserDAO getUserDAO() throws SQLException {
        if (userDAOType == UserDAOType.HIBERNATE) {
            return new UserHiberDAO(DBHelper.getInstance().getSessionFactory());
        } else {
            return new UserJDBCDAO(DBHelper.getInstance().getConnection());
        }
    }

    public enum UserDAOType {
        HIBERNATE, JDBC
    }
}
