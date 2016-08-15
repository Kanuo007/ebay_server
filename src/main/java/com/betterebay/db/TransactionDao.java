package com.betterebay.db;

import java.util.List;
import java.util.Optional;

import org.hibernate.SessionFactory;

import com.betterebay.core.Transaction;

import io.dropwizard.hibernate.AbstractDAO;


public class TransactionDao extends AbstractDAO<Transaction> {

  public TransactionDao(SessionFactory factory) {
    super(factory);
  }

  public List<Transaction> findAll() {
    return list(namedQuery("com.betterebay.core.transaction.findAll"));
  }


  public Optional<Transaction> findTransactionByItemId(Long id) {
    return Optional.ofNullable(
        (Transaction) namedQuery("com.betterebay.core.transaction.findTransactionByItemId")
            .setParameter("item_id", id).uniqueResult());
  }

  public Optional<Transaction> findTransactionByBuyerId(Long id) {
    return Optional.ofNullable(
        (Transaction) namedQuery("com.betterebay.core.transaction.findTransactionByBuyerId")
            .setParameter("user_id", id).uniqueResult());
  }

  public Optional<Transaction> findTransactionByBidHistory_id(Long id) {
    return Optional.ofNullable(
        (Transaction) namedQuery("com.betterebay.core.transaction.findTransactionByBidHistory_id")
            .setParameter("bidHistory_id", id).uniqueResult());
  }

  public Transaction createTransaction(Transaction aTransaction) {
    return persist(aTransaction);
  }

}
