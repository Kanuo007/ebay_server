package com.betterebay.db;

import com.betterebay.core.Notification;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;

public class NotificationDao extends AbstractDAO<Notification> {

  private static Logger logger = LoggerFactory.getLogger(NotificationDao.class);

  public NotificationDao(SessionFactory factory) {
    super(factory);
  }

  public Notification createNotification(Notification notification) {
    NotificationDao.logger.info(notification.toString());
    return persist(notification);
  }

  public Optional<Notification> findNotificationByID(Long id) {
    return Optional.ofNullable(get(id));
  }

  public List<Notification> findALL() {
    return list(namedQuery("com.betterebay.core.notification.findAll"));
  }

  public List<Notification> findNotificationByUserID(Long user_id) {
    return list(
        namedQuery("com.betterebay.core.notification.findNotificationByUserID").setParameter("user_id", user_id));
  }

  public List<Notification> findNotificationByTransactionID(Long transaction_id) {
    return list(namedQuery("com.betterebay.core.notification.findNotificationByTransactionID")
        .setParameter("transaction_id", transaction_id));
  }

}
