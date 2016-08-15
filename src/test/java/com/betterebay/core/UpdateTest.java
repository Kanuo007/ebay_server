package com.betterebay.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.betterebay.db.BidHistoryDao;
import com.betterebay.db.ItemDao;
import com.betterebay.db.NotificationDao;
import com.betterebay.db.TransactionDao;

public class UpdateTest {
  private static final ItemDao mockedItemDao = Mockito.mock(ItemDao.class);
  private static final BidHistoryDao mockedBidHistoryDao = Mockito.mock(BidHistoryDao.class);
  private static final TransactionDao mockedTransactionDao = Mockito.mock(TransactionDao.class);
  private static final NotificationDao mockedNotificationDao = Mockito.mock(NotificationDao.class);

  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testUpdateEverything() {
    Assert.fail("Not yet implemented");
  }

}
