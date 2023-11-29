package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Users " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(64), " +
                " lastName VARCHAR(64), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS Users";
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            System.out.printf("User с именем %s добавлен в базу данных\n", name);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            User userToBeRemoved = session.get(User.class, (long) id);
            session.remove(userToBeRemoved);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> allUsers = new ArrayList<>();


        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> cr = cb.createQuery(User.class);
            Root<User> root = cr.from(User.class);
            cr.select(root);

            Query query = session.createQuery(cr);
            allUsers = query.getResultList();

            session.getTransaction().commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        String hql = "DELETE FROM User";
        Transaction transaction = null;

        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            int a = query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
