package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static DataSource dataSource;

    private static SessionFactory sessionFactory;

    private static DBHelper instance;

    public static synchronized DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();

            try {
                dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/crud");
            } catch (NamingException e) {
                e.printStackTrace();
            }
            sessionFactory = createSessionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getConfiguration(PropertyReader.getProperty("db.properties"));
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private static Configuration getConfiguration(Properties properties) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperties(properties);
        return configuration;
    }
}