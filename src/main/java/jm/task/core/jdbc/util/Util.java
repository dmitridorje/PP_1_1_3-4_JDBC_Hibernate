package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соединения с БД

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING = "jdbc:mysql://localhost/mysql_demo";
    private static final String CONN_DRIVER = "com.mysql.jdbc.Driver";
    public static final String HIBER_DIALECT = "org.hibernate.dialect.MySQL8Dialect";

    // настройка соединения для JDBC
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
    }

    // настройка соединения для Hibernate
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws Exception {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                // Data Source
                settings.put(Environment.DRIVER, CONN_DRIVER);
                settings.put(Environment.URL, CONN_STRING);
                settings.put(Environment.USER, USERNAME);
                settings.put(Environment.PASS, PASSWORD);

                //Hinernate
                //settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.DIALECT, HIBER_DIALECT);
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
