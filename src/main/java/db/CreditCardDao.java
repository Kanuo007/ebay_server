package db;

import java.util.List;

import org.hibernate.SessionFactory;

import core.CreditCard;
import io.dropwizard.hibernate.AbstractDAO;

/**
 * Represents a CreditCardDao class which manages CreditCard
 *
 * @author LiYang
 *
 */
public class CreditCardDao extends AbstractDAO<CreditCard> {

  /**
   * Creates an instance of CreditCardDao given a session provider
   *
   * @param factory The session provider
   */
  public CreditCardDao(SessionFactory factory) {
    super(factory);
  }

  /**
   * Return the Credit Card object based on its id
   *
   * @param id The credit_card_id of a CreditCard object
   * @return the Credit Card object based on its id
   */
  public CreditCard findCreditCardByID(Long id) {
    return get(id);
  }

  /**
   * Save the creditCard and return it
   *
   * @param creditCard An instance of CreditCard which is to be saved
   * @return the creditCard which is saved
   */
  public CreditCard createCreditCard(CreditCard creditCard) {
    return persist(creditCard);
  }

  /**
   *
   * @return the results of all credit cards
   */
  public List<CreditCard> findAllCreditCard() {
    return list(namedQuery("core.creditcard.findAll"));
  }

  /**
   * @param userId The userId
   *
   * @return the results of all credit cards based on user_id
   */
  public List<CreditCard> findByUserId(Long userId) {
    return list(namedQuery("core.creditcard.findByUserId").setParameter("user_id", userId));
  }
}

