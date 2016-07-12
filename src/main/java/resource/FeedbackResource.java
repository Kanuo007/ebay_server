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
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import core.Feedback;
import db.FeedbackDao;
import io.dropwizard.hibernate.UnitOfWork;

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
    @Path("/by_transaction_id")
    @Timed
    @UnitOfWork
    public Feedback findFeedbackByTransactionID(@QueryParam("transaction_id") long transaction_id){
        Optional<Feedback> feedbackoption = this.feedbackDao.findFeedbackByTransactionID(transaction_id);
        if(feedbackoption.isPresent()) {
            return new Feedback(feedbackoption.get().getBuyer_id(), feedbackoption.get().getTransaction_id(),
                    feedbackoption.get().getContent(), feedbackoption.get().getDatetime());
        }
        return new Feedback(0, 0, "", new Date());
    }

    @GET
    @Path("/by_buyer_id")
    @Timed
    @UnitOfWork
    public List<Feedback> findFeedbackByBuyerID(@QueryParam("buyer_id") long buyer_id){
        System.out.print(buyer_id);
        return this.feedbackDao.findFeedbackByBuyerID(buyer_id);
    }

}
