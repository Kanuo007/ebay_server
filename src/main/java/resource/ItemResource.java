package resource;

import com.codahale.metrics.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import core.Item;
import db.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/item")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {
  private ItemDao itemDao;
  private static Logger logger = LoggerFactory.getLogger(ItemResource.class);

  public ItemResource(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @GET
  @Timed
  @Path("/search_all")
  @UnitOfWork
  // list all items
  public List<Item> SearchAll() {
    return this.itemDao.findAllItem();
  }

  @GET
  @Timed
  @Path("/search_item_name/{item}")
  @UnitOfWork
  // search item by name
  public List<Item> Search(@PathParam("item") String item) {
    return this.itemDao.findItemByName(item);
  }

  @GET
  @Timed
  @Path("/search_item_onBid")
  @UnitOfWork
  // list all item available to bid on
  public List<Item> findAllAvailableItems() {
    return this.itemDao.findItemByAvailability();
  }

  @GET
  @Timed
  @Path("/search_item_id/{itemid}")
  @UnitOfWork
  // search item by itemID
  public Item findIDItems(@PathParam("itemid") Long id) {
    return this.itemDao.findItemByID(id).get();
  }

  @POST
  @Path("/create_item")
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  // create a new item and put it into database
  public Item createItem(Item item) {
    Date d = new Date();
    if (!d.after(item.getBid_end_time())) {
      return this.itemDao.createItem(item);
    }
    return null;
  }
}
