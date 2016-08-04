package resource;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.jersey.caching.CacheControl;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class HomepageResource {

  private static Logger logger = LoggerFactory.getLogger(HomepageResource.class);

  public HomepageResource() {}

  @GET
  @Timed(name = "get-homepage")
  @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
  @Produces(MediaType.TEXT_PLAIN)
  public String sayWelcome() {
    return new String("Welcome");
  }
}
