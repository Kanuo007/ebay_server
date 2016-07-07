package resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import core.Feedback;
import db.FeedbackDao;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/feedback")
@Produces(MediaType.APPLICATION_JSON)
public class FeedbackResource {

    private static Logger logger = LoggerFactory.getLogger(FeedbackResource.class);
    private FeedbackDao  feedbackDao;
    public FeedbackResource(FeedbackDao feedbackDao){
        this.feedbackDao = feedbackDao;
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Feedback leaveFeedback(Feedback feedback){
        return this.feedbackDao.createFeedback(feedback);
    }

    @GET
    @Path("/by-transaction-id")
    public Feedback findFeedbackByTransactionID(@QueryParam("transaction_id") Long transaction_id){
        return this.feedbackDao.findFeedbackByTransactionID(transaction_id).get();
    }

    @GET
    @Path("/by-buyer-id")
    public Feedback findFeedbackByBuyerID(@QueryParam("buyer_id") Long buyer_id){
        return this.feedbackDao.findFeedbackByBuyerID(buyer_id).get();
    }

}
