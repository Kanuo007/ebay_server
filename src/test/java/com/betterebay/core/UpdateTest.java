package com.betterebay.core;

import com.betterebay.db.BidHistoryDao;
import com.betterebay.db.ItemDao;
import com.betterebay.db.NotificationDao;
import com.betterebay.db.TransactionDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;

public class UpdateTest {

  private static final ItemDao mockedItemDao = Mockito.mock(ItemDao.class);
  private static final BidHistoryDao mockedBidHistoryDao = Mockito.mock(BidHistoryDao.class);
  private static final TransactionDao mockedTransactionDao = Mockito.mock(TransactionDao.class);
  private static final NotificationDao mockedNotificationDao = Mockito.mock(NotificationDao.class);
  private static final SessionFactory sessionFactory = Mockito.mock(SessionFactory.class);
  private static final Session session = Mockito.mock(Session.class);

  private Item item1;
  private BidHistory bidHistory1;
  private Transaction transaction;
  private Notification notification1;
  private Notification notification2;
  private Update update;

  @Before
  public void setUp() throws Exception {
    update = new Update(UpdateTest.mockedNotificationDao,UpdateTest.mockedTransactionDao,
            UpdateTest.mockedBidHistoryDao, UpdateTest.mockedItemDao, UpdateTest.sessionFactory);
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.item1 = new Item(new Long(1), "book", 10.05, true, ft.parse("2016-07-21 10:10:10"),
            ft.parse("2016-08-15 10:10:10"));
    this.item1.setId(1l);
    this.bidHistory1=
            new BidHistory(new Long(1), new Long(1), ft.parse("2016-08-15 10:00:00"), 25.34);
    this.bidHistory1.setId(1l);
    this.transaction = new Transaction(1l, 1l, 1l, ft.parse("2016-08-15 10:10:10"));
    this.transaction.setId(1l);
    this.notification1 = new Notification(1l, 1l, "Auction has end.");
    this.notification2 = new Notification(2l, 1l, "Auction has end.");

    Mockito.when(UpdateTest.sessionFactory.openSession()).thenReturn(UpdateTest.session);
    Mockito.when(UpdateTest.session.getSessionFactory()).thenReturn(UpdateTest.sessionFactory);

    Mockito.when(UpdateTest.mockedItemDao.findAllItem()).thenReturn(Arrays.asList(item1));
    UpdateTest.mockedItemDao.updateStatus(true, 1l);
    this.item1.setStatus(false);

    Mockito.when(UpdateTest.mockedTransactionDao.findTransactionByItemId(1l)).thenReturn(Optional.empty());
    Mockito.when(UpdateTest.mockedBidHistoryDao.findByHighestPriceByItemId(1l)).thenReturn(Optional.ofNullable(this.bidHistory1));
    Mockito.when(UpdateTest.mockedTransactionDao.createTransaction(this.transaction)).thenReturn(this.transaction);
    Mockito.when(UpdateTest.mockedNotificationDao.createNotification(this.notification1)).thenReturn(this.notification1);
    Mockito.when(UpdateTest.mockedNotificationDao.createNotification(this.notification2)).thenReturn(this.notification2);
    Mockito.when(UpdateTest.mockedNotificationDao.findALL()).thenReturn(Arrays.asList(this.notification1, this.notification2));
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testUpdateEverything() {
    update.updateEverything();
    Assert.assertEquals(this.item1.getStatus(), false);
    Assert.assertEquals(UpdateTest.mockedNotificationDao.findALL(), Arrays.asList(this.notification1, this.notification2));
  }
}
