package resource;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import core.Item;
import db.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/selling")
@Produces(MediaType.APPLICATION_JSON)
public class ListResource {
  private ItemDao itemDao;

  private static Logger logger = LoggerFactory.getLogger(ListResource.class);

  public ListResource(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @POST
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public void sell(Item item) {
    // check if the bid time is valid and the bid status is true
    Date d = new Date();
    if (!d.before(item.getBid_start_time()) && (item.getStatus() == true)) {
      this.itemDao.createItem(item);
    }
  }

}
