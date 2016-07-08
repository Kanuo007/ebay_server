package db;

import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

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
    return Optional.ofNullable((Transaction) namedQuery("core.transaction.findTransactionByItemId")
            .setParameter("item_id", id).uniqueResult());
  }

  public Optional<Transaction> findTransactionByBuyerId(Long id) {
    return Optional.ofNullable((Transaction) namedQuery("core.transaction.findTransactionByBuyerId")
            .setParameter("user_id", id).uniqueResult());
  }

  public Transaction createTransaction(Transaction aTransaction) {
    return persist(aTransaction);
  }

}
