package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.sql.SQLException;
import java.util.List;

public class UserHiberDAO implements UserDAO {
    private SessionFactory sessionFactory;

    public UserHiberDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(String login, String password, String name) {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.persist(new User(login, password, name));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserById(long id) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            user = session.createQuery(
                    "FROM User WHERE id = :id", User.class)
                    .setParameter("id", id)
                    .getSingleResult();
        }
        return user;
    }

    @Override
    public User getUser(String login) {
        User user;
        try (Session session = sessionFactory.openSession()) {
            user = session.createQuery(
                    "FROM User WHERE login = :login", User.class)
                    .setParameter("login", login)
                    .uniqueResult();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        final Transaction transaction = session.getTransaction();
        transaction.begin();
        try {
            session.createQuery("DELETE FROM User WHERE id = :id")
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public boolean isUserExists(String login) throws SQLException {
        return getUser(login) != null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allCars;
        try (Session session = sessionFactory.openSession()) {
            allCars = session.createQuery("FROM User", User.class).getResultList();
        }
        return allCars;
    }
}
