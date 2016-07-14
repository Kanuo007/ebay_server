package db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import core.Notification;
import io.dropwizard.hibernate.AbstractDAO;

public class NotificationDao extends AbstractDAO<Notification> {
  public NotificationDao(SessionFactory factory) {
    super(factory);
  }

  public Notification createNotification(Notification notification) {
    return persist(notification);
  }

  public Optional<Notification> findNotificationByID(Long id) {
    return Optional.ofNullable(get(id));
  }

  public List<Notification> findNotificationByUserID(Long user_id) {
    return list(namedQuery("core.user.findNotificationByUserID").setParameter("user_id", user_id));
  }

  public List<Notification> findNotificationByTransactionID(Long transaction_id) {
    return list(namedQuery("core.Notification.findNotificationByTransactionID")
        .setParameter("transaction_id", transaction_id));
  }

  public Notification create_notification(Notification aNotification) {
    return persist(aNotification);
  }

}
