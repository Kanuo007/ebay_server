package com.betterebay.db;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.betterebay.core.Transaction;

public class TransactionDaoTest {
  private TransactionDao transactionDao1;
  private Transaction tran1;
  private Transaction tran2;


  @Before
  public void setUp() throws Exception {
    this.transactionDao1 = Mockito.mock(TransactionDao.class);
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.tran1 =
        new Transaction(new Long(1), new Long(1), new Long(1234), ft.parse("2016-10-18 13:30:00"));
    this.tran2 =
        new Transaction(new Long(2), new Long(2), new Long(2234), ft.parse("2016-10-18 03:30:00"));
    Mockito.when(this.transactionDao1.findAll()).thenReturn(Arrays.asList(this.tran1, this.tran2));
    Mockito.when(this.transactionDao1.findTransactionByBuyerId(new Long(1234)))
        .thenReturn(Optional.ofNullable(this.tran1));
    Mockito.when(this.transactionDao1.findTransactionByItemId(new Long(1)))
        .thenReturn(Optional.ofNullable(this.tran1));
    Mockito.when(this.transactionDao1.createTransaction(this.tran1)).thenReturn(this.tran1);
    Mockito.when(this.transactionDao1.createTransaction(this.tran2)).thenReturn(this.tran2);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testFindAll() {
    List<Transaction> transactions = this.transactionDao1.findAll();
    Assert.assertEquals(transactions.size(), 2);
    Assert.assertEquals(transactions.get(0), this.tran1);
    Assert.assertEquals(transactions.get(1), this.tran2);
  }

  @Test
  public void testFindTransactionByItemId() {
    Optional<Transaction> transaction = this.transactionDao1.findTransactionByItemId(new Long(1));
    Assert.assertEquals(transaction.get().getItem_id(), new Long(1));
  }

  @Test
  public void testFindTransactionByBuyerId() {
    Optional<Transaction> transaction =
        this.transactionDao1.findTransactionByBuyerId(new Long(1234));
    Assert.assertEquals(transaction.get().getUser_id(), new Long(1234));
  }

  @Test
  public void testCreateTransaction() {
    Assert.assertEquals(this.transactionDao1.createTransaction(this.tran1), this.tran1);
    Assert.assertEquals(this.transactionDao1.createTransaction(this.tran2), this.tran2);
  }

}
