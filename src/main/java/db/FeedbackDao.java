package db;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import core.Feedback;
import io.dropwizard.hibernate.AbstractDAO;


public class FeedbackDao extends AbstractDAO<Feedback> {
  public FeedbackDao(SessionFactory factory) {
    super(factory);
  }

  public Optional<Feedback> findFeedbackByID(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Optional<Feedback> findFeedbackByTransactionID(long TransactionID) {
    return Optional.ofNullable((Feedback) (namedQuery("core.feedback.findFeedbackByTransactionID")
            .setLong("transaction_id", TransactionID).uniqueResult()));
  }

  public List<Feedback> findFeedbackByBuyerID(long buyerID) {
    return  list(namedQuery("core.feedback.findFeedbackByBuyerID").setLong("buyer_id", buyerID));
  }

  public Feedback createFeedback(Feedback feedback) {
    return persist(feedback);
  }

  public List<Feedback> findAllFeedback() {
    return namedQuery("core.feedback.findAll").list();
  }
}
