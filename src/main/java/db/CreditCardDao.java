package db;

import org.hibernate.SessionFactory;

import java.util.List;

import core.CreditCard;
import io.dropwizard.hibernate.AbstractDAO;

public class CreditCardDao extends AbstractDAO<CreditCard> {
  public CreditCardDao(SessionFactory factory) {
    super(factory);
  }

  public CreditCard findCreditCardByID(Long id) {
    return get(id);
  }

  public CreditCard createCreditCard(CreditCard creditCard) {
    return persist(creditCard);
  }

  public List<CreditCard> findAllCreditCard() {
    return list(namedQuery("core.creditcard.findAll"));
  }

  public List<CreditCard> findByUserId() {
    return list(namedQuery("core.creditcard.findByUserId"));
  }
}

