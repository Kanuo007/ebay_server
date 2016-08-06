package com.betterebay.db;

import com.betterebay.core.Feedback;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import io.dropwizard.hibernate.AbstractDAO;


public class FeedbackDao extends AbstractDAO<Feedback> {
  public FeedbackDao(SessionFactory factory) {
    super(factory);
  }

  public Optional<Feedback> findFeedbackByID(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Optional<Feedback> findFeedbackByTransactionID(long TransactionID) {
    return Optional.ofNullable((Feedback) (namedQuery("com.betterebay.core.feedback.findFeedbackByTransactionID")
            .setLong("transaction_id", TransactionID).uniqueResult()));
  }

  public List<Feedback> findFeedbackByBuyerID(long buyerID) {
    return  list(namedQuery("com.betterebay.core.feedback.findFeedbackByBuyerID").setLong("buyer_id", buyerID));
  }

  public Feedback createFeedback(Feedback feedback) {
    return persist(feedback);
  }

  public List<Feedback> findAllFeedback() {
    return namedQuery("com.betterebay.core.feedback.findAll").list();
  }
}
