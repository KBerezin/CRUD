package DAO;


import util.DBHelper;

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

    public UserDAO getUserDAO() throws SQLException {
        return new UserJDBCDAO(DBHelper.getInstance().getConnection());
//        return new UserHiberDAO(DBHelper.getInstance().getSessionFactory());
    }

}
