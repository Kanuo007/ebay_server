package db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import core.Transaction;
import io.dropwizard.hibernate.AbstractDAO;


public class TransactionDao extends AbstractDAO<Transaction> {

  public TransactionDao(SessionFactory factory) {
    super(factory);
  }

  public List<Transaction> findAll() {
    return list(namedQuery("core.transaction.findAll"));
  }

  public Optional<Transaction> findTransactionByUserId(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Optional<Transaction> findTransactionByItemId(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Transaction createTransaction(Transaction aTransaction) {
    return persist(aTransaction);
  }

}
