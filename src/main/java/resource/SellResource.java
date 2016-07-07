package resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import db.ItemDao;
import db.UserDao;

@Path("/selling")
@Produces(MediaType.APPLICATION_JSON)
public class SellResource {
  private UserDao userDao;
  private ItemDao itemDao;

  private static Logger logger = LoggerFactory.getLogger(SellResource.class);

  public SellResource(UserDao userDao, ItemDao itemDao) {
    this.userDao = userDao;
    this.itemDao = itemDao;
  }

}
