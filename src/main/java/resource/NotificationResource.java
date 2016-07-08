package resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import core.Notification;
import core.Transaction;
import db.NotificationDao;

public class NotificationResource {
  private NotificationDao notificationDao;
  private static Logger logger = LoggerFactory.getLogger(NotificationResource.class);

  public NotificationResource(NotificationDao notificationDao) {
    this.notificationDao = notificationDao;
  }


  @POST
  @Timed
  @Path("/Notification")
  @Consumes(MediaType.APPLICATION_JSON)
  public Notification Notice(Transaction transaction) {
    Notification n;
    String message = "Auction " + transaction.getId() + " has done.";
    n = new Notification(transaction.getUser_id(), transaction.getId(), message);
    this.notificationDao.createNotification(n);
    return n;
  }

}


