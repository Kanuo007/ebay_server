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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


// User can use auction to leave feedback, search feedback and bid
@Path("/auction")
@Api(value = "/auction", description = "Operations about bidding, leaving feedback and search them.")
@Produces(MediaType.APPLICATION_JSON)
public class AuctionResource {

  private NotificationDao notificationDao;
  private ItemDao itemDao;
  private BidHistoryDao bidHistoryDao;
  private FeedbackDao feedbackDao;
  private TransactionDao transactionDao;
  private static Logger logger = LoggerFactory.getLogger(AuctionResource.class);

  public AuctionResource(NotificationDao notificationDao, BidHistoryDao bidHistoryDao,
      TransactionDao transactionDao, ItemDao itemDao, FeedbackDao feedbackDao) {
    this.bidHistoryDao = bidHistoryDao;
    this.feedbackDao = feedbackDao;
    this.itemDao = itemDao;
    this.transactionDao = transactionDao;
    this.notificationDao = notificationDao;
  }

  @POST
  @Path("/leave_feedback")
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "Leave feedback for a transaction",
          response = Feedback.class)
  public Feedback leaveFeedback(@Auth User user,
                                @ApiParam(value = "Update Feedback", required = true) Feedback feedback) {
    return this.feedbackDao.createFeedback(feedback);
  }


  @GET
  @Path("/search_feedback_by_transaction_id/{id}")
  @Timed
  @UnitOfWork
  @ApiOperation(value = "Search feedback by transaction id",
          notes = "Pass the transaction id as path parameter",
          response = Feedback.class)
  public Feedback findFeedbackByTransactionID(
          @PathParam("id") @ApiParam(value = "id that needs to searched", required = true) Long id) {
    Optional<Feedback> feedback = this.feedbackDao.findFeedbackByTransactionID(id);
    if (feedback.isPresent()) {
      return feedback.get();
    }
    return new Feedback(new Long(0), new Long(0), "feedback already existed", new Date());
  }

  @GET
  @Path("/search_feedback_by_buyer_id/{id}")
  @Timed
  @UnitOfWork
  @ApiOperation(value = "search feedback by buyer id",
          notes = "Pass the buyer id as path parameter",
          response = Feedback.class,
          responseContainer = "List")
  public List<Feedback> findFeedbackByBuyerID(
          @PathParam("id") @ApiParam(value = "id that needs to searched", required = true) Long id) {
    return this.feedbackDao.findFeedbackByBuyerID(id);
  }

  @GET
  @Path("/notification/findALL")
  @Timed
  @UnitOfWork
  @ApiOperation(value = "search all notifications",
          response = Notification.class,
          responseContainer = "List")
  public List<Notification> findALL() {
    return this.notificationDao.findALL();
  }

  @GET
  @Path("/notification/findByUserId/{userId}")
  @Timed
  @UnitOfWork
  @ApiOperation(value = "search notifications by user id",
          notes = "Pass the user id as path parameter",
          response = Notification.class,
          responseContainer = "List")
  public List<Notification> findNotificationByUserID(
          @PathParam("userId") @ApiParam(value = "id that needs to searched", required = true) Long userId) {
    return this.notificationDao.findNotificationByUserID(userId);
  }

  @GET
  @Path("/notification/findNotificationByTransactionID/{transaction_id}")
  @Timed
  @UnitOfWork
  @ApiOperation(value = "search notifications by transaction id",
          notes = "Pass the transaction id as path parameter",
          response = Notification.class,
          responseContainer = "List")
  public List<Notification> findNotificationByTransactionID(
      @PathParam("transaction_id") @ApiParam(value = "id that needs to searched", required = true) Long transaction_id) {
    return this.notificationDao.findNotificationByTransactionID(transaction_id);
  }

  @POST
  @Path("/bid")
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "List an item",
          notes = "Pass BidHistory json object. Return back object with different status field."+
                  "Only Succeed will create bid.",
          response = BidHistory.class)
  public BidHistory bid(@Auth User user,
                        @ApiParam(value = "Bid that needs to added", required = true) BidHistory bidHistory) {
    long itemId = bidHistory.getItemId();
    Optional<Item> itemToBid = this.itemDao.findItemByID(itemId);
    if (itemToBid.isPresent()) {
      boolean itemAbleToBid = itemToBid.get().getStatus();
      if (itemAbleToBid) {
        BidHistory bidHistoryWithHighestPrice =
            this.bidHistoryDao.findByHighestPriceByItemId(itemId).isPresent()
                ? this.bidHistoryDao.findByHighestPriceByItemId(itemId).get() : null;
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
