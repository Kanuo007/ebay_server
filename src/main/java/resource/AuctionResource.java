package resource;

import com.codahale.metrics.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import core.Item;
import db.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/auction")
@Produces(MediaType.APPLICATION_JSON)
public class AuctionResource {

  private ItemDao itemDao;
  private static Logger logger = LoggerFactory.getLogger(AuctionResource.class);

  public AuctionResource(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @GET
  @Timed
  @UnitOfWork
  public List<Item> findAllAvailableItems() {
    return this.itemDao.findItemByAvailability();
  }
}
