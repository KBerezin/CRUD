package DAO;


import util.DBHelper;

import javax.naming.NamingException;
import java.sql.SQLException;

public class UserDAOFactory {

    private static UserDAOFactory userDAOFactory;

    private UserDAOFactory() {
    }

    public static synchronized UserDAOFactory getInstance(){
        if (userDAOFactory == null) {
            userDAOFactory = new UserDAOFactory();
        }
        return userDAOFactory;
    }

    public UserDAO getUserDAO() throws SQLException, NamingException {
        return new UserJDBCDAO(DBHelper.getInstance().getConnection());
    }
}
