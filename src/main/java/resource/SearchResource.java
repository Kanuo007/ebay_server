package resource;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import api.Register;
import core.Item;
import core.User;
import db.ItemDao;
import io.dropwizard.hibernate.UnitOfWork;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

  private ItemDao itemDao;
  private static Logger logger = LoggerFactory.getLogger(SearchResource.class);

  public SearchResource() {}

  @GET
  @Timed
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Item> Search(@QueryParam("item_name") String name) {
    if (!this.itemDao.findItemByName(name).) {
      return new LinkedList<>();
    }

  }

}


