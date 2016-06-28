package resource;

import com.codahale.metrics.annotation.Timed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dropwizard.jersey.caching.CacheControl;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HomepageResource {

    private static Logger logger = LoggerFactory.getLogger(HomepageResource.class);

    public HomepageResource(){}

    @GET
    @Timed(name="get-homepage")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public String sayWelcome(){
        return "Welcome";
    }
}
