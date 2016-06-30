package db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import core.Feedback;
import io.dropwizard.hibernate.AbstractDAO;


public class FeedbackDao extends AbstractDAO<Feedback> {
  public FeedbackDao(SessionFactory factory) {
    super(factory);
  }

  public Optional<Feedback> findFeedbackByID(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Optional<Feedback> findFeedbackByTransactionID(Long TransactionID) {
    return Optional.ofNullable((Feedback) (namedQuery("core.user.findFeedbackByTransactionID")
        .setParameter("TransactionID", TransactionID).uniqueResult()));
  }

  public List<Feedback> findFeedbackByUserID(Long userID) {
    List<Feedback> result = new ArrayList<Feedback>();
    result.add(
        (Feedback) (namedQuery("core.Item.findFeedbackByuserIDe").setParameter("userID", userID)));
    return result;
  }

  public Feedback createFeedback(Feedback feedback) {
    return persist(feedback);
  }


  @SuppressWarnings("unchecked")
  public List<Feedback> findAllFeedback() {
    return namedQuery("").list();
  }
}
