package resource;

import com.codahale.metrics.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import core.BidHistory;
import core.Feedback;
import core.Item;
import core.Notification;
import core.User;
import db.BidHistoryDao;
import db.FeedbackDao;
import db.ItemDao;
import db.NotificationDao;
import db.TransactionDao;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;


// User can use auction to leave feedback, search feedback and bid
@Path("/auction")
@Produces(MediaType.APPLICATION_JSON)
public class AuctionResource {

  private NotificationDao notificationDao;
  private ItemDao itemDao;
  private BidHistoryDao bidHistoryDao;
  private FeedbackDao feedbackDao;
  private TransactionDao transactionDao;
  private static Logger logger = LoggerFactory.getLogger(AuctionResource.class);

  public AuctionResource(BidHistoryDao bidHistoryDao, TransactionDao transactionDao,
      ItemDao itemDao, FeedbackDao feedbackDao) {
    this.bidHistoryDao = bidHistoryDao;
    this.feedbackDao = feedbackDao;
    this.itemDao = itemDao;
    this.transactionDao = transactionDao;
  }

  @POST
  @Path("/leave_feedback")
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public Feedback leaveFeedback(@Auth User user, Feedback feedback) {
    return this.feedbackDao.createFeedback(feedback);
  }


  @GET
  @Path("/search_feedback_by_transaction_id/{id}")
  @Timed
  @UnitOfWork
  public Feedback findFeedbackByTransactionID(@PathParam("id") long id) {
    Optional<Feedback> feedback = this.feedbackDao.findFeedbackByTransactionID(id);
    if (feedback.isPresent()) {
      return feedback.get();
    }
    return new Feedback(new Long(0), new Long(0), "", new Date());
  }

  @GET
  @Path("/search_feedback_by_buyer_id/{id}")
  @Timed
  @UnitOfWork
  public List<Feedback> findFeedbackByBuyerID(@PathParam("id") long id) {
    return this.feedbackDao.findFeedbackByBuyerID(id);
  }

  @GET
  @Path("/Notification/FindALL")
  @Timed
  @UnitOfWork
  public List<Notification> findALL() {
    return this.notificationDao.findALL();
  }

  @GET
  @Path("/Notification/FindByUserId/{userId}")
  @Timed
  @UnitOfWork
  public List<Notification> findNotificationByUserID(@PathParam("userId") long userId) {
    return this.notificationDao.findNotificationByUserID(userId);
  }

  @GET
  @Path("/Notification/findNotificationByTransactionID/{transaction_id}")
  @Timed
  @UnitOfWork
  public List<Notification> findNotificationByTransactionID(
      @PathParam("transaction_id") long transaction_id) {
    return this.notificationDao.findNotificationByTransactionID(transaction_id);
  }

  @POST
  @Path("/bid")
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public BidHistory bid(@Auth User user, BidHistory bidHistory) {
    long itemId = bidHistory.getItemId();
    Optional<Item> itemToBid = this.itemDao.findItemByID(itemId);
    if (itemToBid.isPresent()) {
      boolean itemAbleToBid = itemToBid.get().getStatus();
      if (itemAbleToBid) {
        BidHistory bidHistoryWithHighestPrice =
            this.bidHistoryDao.findByHighestPriceByItemId(itemId).get();
        double currentPrice = (bidHistoryWithHighestPrice == null) ? itemToBid.get().getBase_price()
            : bidHistoryWithHighestPrice.getBidPrice();
        if (bidHistory.getBidPrice() > currentPrice) {
          bidHistory.setStatus("Succeed.");
          this.bidHistoryDao.createBidHistory(bidHistory);
        } else {
          bidHistory.setStatus("Failure: bidding price is lower than current price.");
        }
      } else {
        bidHistory.setStatus("Failure: item not able to bid.");
      }
    } else {
      bidHistory.setStatus("Failure: item does not exist.");
    }
    return bidHistory;
  }

}
