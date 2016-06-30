package resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/feedback")
@Produces(MediaType.APPLICATION_JSON)
public class FeedbackResource {

    private static Logger logger = LoggerFactory.getLogger(FeedbackResource.class);

    public FeedbackResource(){}

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public String leaveFeedback(@QueryParam("transaction_id") LongParam transaction_id){
        return "Posted!";
    }
}
