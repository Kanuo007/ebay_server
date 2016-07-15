package db;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DAOTests {
  SessionFactory sessionFactory;

  public DAOTests() {
    Configuration config = new Configuration();
    config.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/betterebay");
    config.setProperty("hibernate.connection.username", "onepiece");
    config.setProperty("hibernate.connection.password", "sa");
    config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
    config.setProperty("hibernate.current_session_context_class", "thread");
    config.setProperty("hibernate.show_sql", "true");
    // config.addAnnotatedClass(Address.class);
    StandardServiceRegistry serviceRegistry =
        new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    this.sessionFactory = config.buildSessionFactory(serviceRegistry);
  }

  public Session getSession() {
    Session session;

    try {
      session = this.sessionFactory.getCurrentSession();
    } catch (SessionException se) {
      session = this.sessionFactory.openSession();
    }

    return session;
  }
}
