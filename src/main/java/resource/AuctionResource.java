package resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import core.Item;
import db.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

public class AuctionResource {

  private ItemDao itemDao;

  public AuctionResource(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @POST
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Item> itemStatus(Boolean status) {
    return this.itemDao.findItemByAvailability(status);
  }
}
