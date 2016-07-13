package resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import core.Notification;
import db.ItemDao;
import db.NotificationDao;
import db.TransactionDao;

public class NotificationResource {
  private NotificationDao notificationDao;
  private ItemDao itemDao;
  private static Logger logger = LoggerFactory.getLogger(NotificationResource.class);

  public NotificationResource(NotificationDao notificationDao, TransactionDao transactionDao,
      ItemDao itemDao2) {
    this.notificationDao = notificationDao;
  }


  public NotificationResource(TransactionDao transactionDao, ItemDao itemDao) {
    this.notificationDao = this.notificationDao;
  }


  @POST
  @Timed
  @Path("/Notification")
  @Consumes(MediaType.APPLICATION_JSON)
  public Notification Notice(NotificationDao notificationDao, TransactionDao transactionDao,
      ItemDao itemDao) {
    Notification n;
    String message = "Auction " + transactionDao.getId() + " has done.";
    n = new Notification(transactionDao.getUser_id(), transactionDao.getId(), message);

    m = new Notification(transactionDao.getItem_id(), transactionDao.getId(), message);
    this.notificationDao.createNotification(n);
    this.notificationDao.createNotification(m);
    return n;
  }

}


