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
import db.BidHistoryDAO;
import db.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * Represents a BidResource class
 *
 * @author LiYang
 *
 */
@Path("/bid")
@Produces(MediaType.APPLICATION_JSON)

public class BidResource {
  private BidHistoryDAO bidHistoryDao;
  private ItemDao itemDao;
  private static Logger logger = LoggerFactory.getLogger(BidResource.class);

  /**
   * Creates an instance of BidResource class
   *
   * @param bidHistoryDao
   */
  public BidResource(BidHistoryDAO bidHistoryDao, ItemDao itemDao) {
    this.bidHistoryDao = bidHistoryDao;
    this.itemDao = itemDao;
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
        double currentPrice = itemToBid.get().getBase_price();
        double highestBidPrice =
            this.bidHistoryDao.findByHighestPriceByItemId(itemId).get().getBidPrice();
        if (highestBidPrice > currentPrice) {
          this.bidHistoryDao.createBidHistory(bidHistory);
          this.itemDao.updateCurrentPrice(highestBidPrice);
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
    return bidHistory;
  }


}
