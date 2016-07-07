package resource;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import core.BidHistory;
import core.Item;
import db.BidHistoryDao;
import db.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/bid")
@Produces(MediaType.APPLICATION_JSON)

/**
 * Represents a BidResource class
 *
 * @author LiYang
 *
 */
public class BidResource {
  private BidHistoryDao bidHistoryDao;
  private ItemDao itemDao;
  private static Logger logger = LoggerFactory.getLogger(BidResource.class);

  /**
   * Creates an instance of BidResource class
   *
   * @param bidHistoryDao
   */
  private BidResource(BidHistoryDao bidHistoryDao) {
    this.bidHistoryDao = bidHistoryDao;
  }

  @POST
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public BidHistory bid(BidHistory bidHistory) {
    long itemId = bidHistory.getItemId();
    Optional<Item> itemToBid = this.itemDao.findItemByID(itemId);
    if (itemToBid.isPresent()) {
      boolean itemAbleToBid = itemToBid.get().getStatus();
      if (itemAbleToBid) {
        // if this item is able to bid, find the current price
        double currentPrice = itemToBid.get().getBasePrice();
        for (BidHistory each : this.bidHistoryDao.findByItemId()) {
          if (each.getBidPrice() > currentPrice) {
            currentPrice = each.getBidPrice();
          }
        }
        if (bidHistory.getBidPrice() > currentPrice) {
          this.bidHistoryDao.createBidHistory(bidHistory);
          System.out.println("success");
        } else {
          System.out.println("price lower than currentPrice");
        }
      } else {
        System.out.println("item not able to bid");
      }

    } else {
      System.out.println("item doesn't exist");
    }

  }


}
