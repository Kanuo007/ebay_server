package resource;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

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

  @GET
  @Timed
  @UnitOfWork
  public List<Item> findNamedItems(String name) {
    return this.itemDao.findItemByName(name);
  }

  @GET
  @Timed
  @UnitOfWork
  public List<Item> findNameColorSizeItems(String name, String color, int size) {
    return this.itemDao.findItemByNameColorSize(name, color, size);
  }

  @GET
  @Timed
  @UnitOfWork
  public Optional<Item> findIDItems(Long id) {
    return this.itemDao.findItemByID(id);
  }
}
