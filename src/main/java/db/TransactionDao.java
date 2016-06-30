package db;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;

public class TransactionDao extends AbstractDao<Transaction> {

  public TransactionDao(SessionFactory factory) {
    super(factory);
  }

  public Optional<Transaction> findTransactionByID(Long id) {

  }

}
