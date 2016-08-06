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
import core.User;
import db.ItemDao;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path("/item")
@Api(value = "/item", description = "Operations about listing and searching items")
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
  @ApiOperation(value = "Finds all Items",
          response = Item.class,
          responseContainer = "List")
  // list all items
  public List<Item> SearchAll() {
    return this.itemDao.findAllItem();
  }

  @GET
  @Timed
  @Path("/search_item_name/{item}")
  @UnitOfWork
  // search item by name
  @ApiOperation(value = "Finds Items by item name",
          notes = "Pass the item as Path Param",
          response = Item.class,
          responseContainer = "List")
  public List<Item> Search(@ApiParam(value = "name that need to be search", required = true)
                             @PathParam("item") String item) {
    return this.itemDao.findItemByName(item);
  }

  @GET
  @Timed
  @Path("/search_item_onBid")
  @UnitOfWork
  @ApiOperation(value = "Finds Items that are able to bid on",
          response = Item.class,
          responseContainer = "List")
  // list all item available to bid on
  public List<Item> findAllAvailableItems() {
    return this.itemDao.findItemByAvailability();
  }

  @GET
  @Timed
  @Path("/search_item_id/{itemid}")
  @UnitOfWork
  @ApiOperation(value = "Finds Items by item id",
          response = Item.class)
  // search item by itemID
  public Item findIDItems(@ApiParam(value = "id number that need to be searched", required = true)
                            @PathParam("itemid") Long id) {
    return this.itemDao.findItemByID(id).get();
  }

  @POST
  @Path("/create_item")
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  @ApiOperation(value = "List an item",
          notes = "Return the exact item back if create success, otherwise item with null values ",
          response = Item.class)
  // create a new item and put it into database
  public Item createItem(@Auth User user,
                         @ApiParam(value = "Item that needs to be listed", required = true) Item item) {
    Date d = new Date();
    if (d.getTime() < item.getBid_end_time().getTime()) {
      return this.itemDao.createItem(item);
    }
    return new Item(null, null,null,null,null,null,null,null,null,null,null);
  }
}
