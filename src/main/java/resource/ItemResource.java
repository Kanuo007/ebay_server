package resource;

import com.codahale.metrics.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public List<Item> SearchAll() {
        return this.itemDao.findAllItem();
    }

    @GET
    @Timed
    @Path("/search_item_name/{item}")
    @UnitOfWork
    public List<Item> Search(@PathParam("item") String item) {
        return this.itemDao.findItemByName(item);
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Item> findAllAvailableItems() {
        return this.itemDao.findItemByAvailability();
    }

    @GET
    @Timed
    @Path("/search_item_id/{itemid}")
    @UnitOfWork
    public Optional<Item> findIDItems(@PathParam("itemid") Long id) {
        return this.itemDao.findItemByID(id);
    }

    @POST
    @Path("/list_item")
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public void sell(@Auth User user, Item item) {
        // check if the bid time is valid and the bid status is true
        Date d = new Date();
        if (!d.before(item.getBid_start_time()) && (item.getStatus() == true)) {
            this.itemDao.createItem(item);
        }
    }
}
