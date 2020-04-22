package util;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBHelper {

    private static DBHelper instance;

    public static synchronized DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException, NamingException {
        DataSource ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/crud");
        return ds.getConnection();
    }
}