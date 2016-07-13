package resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import core.Item;
import db.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

  private ItemDao itemDao;
  private static Logger logger = LoggerFactory.getLogger(SearchResource.class);

  public SearchResource(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @GET
  @Timed
  @Path("/All")
  @UnitOfWork
  public List<Item> SearchAll() {
    return this.itemDao.findAllItem();
  }

  @GET
  @Timed
  @Path("/AllBidding")
  @UnitOfWork
  public List<Item> SearchAllBidding() {
    return this.itemDao.findItemByAvailability();
  }

  @GET
  @Timed
  @Path("/{item}")
  @UnitOfWork
  public List<Item> SearchItem(@PathParam("item") String item) {
    return this.itemDao.findItemByName(item);
  }

}


