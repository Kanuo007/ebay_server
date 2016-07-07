package resource;

import java.util.List;

import javax.ws.rs.Consumes;
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
  private List<Item> res;
  private static Logger logger = LoggerFactory.getLogger(SearchResource.class);


  public SearchResource() {}


  @GET
  @Timed
  @Path("/ALL")
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Item> SearchAll() {
    List<Item> pre = this.itemDao.findAllItem();
    this.res = null;
    if (pre != null) {
      for (int i = 0; i < pre.size(); i++) {
        if (pre.get(i).isStatus() == true) {
          this.res.add(pre.get(i));
        }
      }
    }
    return this.res;
  }



  @GET
  @Timed
  @Path("/{item}")
  @UnitOfWork
  @Consumes(MediaType.APPLICATION_JSON)
  public List<Item> Search(@PathParam("item") String item) {
    List<Item> pre = this.itemDao.findItemByName(item);
    this.res = null;
    if (pre != null) {
      for (int i = 0; i < pre.size(); i++) {
        if (pre.get(i).isStatus() == true) {
          this.res.add(pre.get(i));
        }
      }
    }
    return this.res;
  }



}


