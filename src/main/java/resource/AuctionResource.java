package resource;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import core.BidHistory;
import core.Feedback;
import core.Item;
import core.User;
import db.BidHistoryDao;
import db.FeedbackDao;
import db.ItemDao;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;


// User can use auction to leave feedback, search feedback and bid
@Path("/auction")
@Produces(MediaType.APPLICATION_JSON)
public class AuctionResource {

  private ItemDao itemDao;
  private BidHistoryDao bidHistoryDao;
  private FeedbackDao feedbackDao;
  private static Logger logger = LoggerFactory.getLogger(AuctionResource.class);

  public AuctionResource(BidHistoryDao bidHistoryDao, ItemDao itemDao, FeedbackDao feedbackDao) {
    this.bidHistoryDao = bidHistoryDao;
    this.feedbackDao = feedbackDao;
    this.itemDao = itemDao;
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
    return new Feedback(0, 0, "", new Date());
  }

  @GET
  @Path("/search_feedback_by_buyer_id/{id}")
  @Timed
  @UnitOfWork
  public List<Feedback> findFeedbackByBuyerID(@PathParam("id") long id) {
    return this.feedbackDao.findFeedbackByBuyerID(id);
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
        double basePrice = itemToBid.get().getBase_price();
        double highestBidPrice =
            this.bidHistoryDao.findByHighestPriceByItemId(itemId).get().getBidPrice();
        if ((bidHistory.getBidPrice() > basePrice)
            && (bidHistory.getBidPrice() > highestBidPrice)) {
          bidHistory.setStatus("Succeed.");
          this.bidHistoryDao.createBidHistory(bidHistory);
        } else {
          bidHistory.setStatus(
              "Failure: bidding price is lower than base price or the highest bid price.");
        }
      } else {
        bidHistory.setStatus("Failure: item not able to bid.");
      }
    } else {
      bidHistory.setStatus("Failure: item is not existed.");
    }
    return bidHistory;
  }

}
