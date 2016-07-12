package db;

import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class DAOTests {
  SessionFactory sessionFactory;

  public DAOTests() {
    AnnotationConfiguration config = new AnnotationConfiguration();
    config.setProperty("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/dbname");
    config.setProperty("hibernate.connection.username", "username");
    config.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
    config.setProperty("hibernate.current_session_context_class", "thread");
    config.setProperty("hibernate.show_sql", "false");
    config.addAnnotatedClass(Persistable.class);

    this.sessionFactory = config.buildSessionFactory();
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
