package resource;

import com.codahale.metrics.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import core.Item;
import db.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/selling")
@Produces(MediaType.APPLICATION_JSON)
public class SellResource {
  private ItemDao itemDao;

  private static Logger logger = LoggerFactory.getLogger(SellResource.class);

  public SellResource(ItemDao itemDao) {
    this.itemDao = itemDao;
  }

  @POST
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public void sell(Item item) {
    this.itemDao.createItem(item);
  }

}
