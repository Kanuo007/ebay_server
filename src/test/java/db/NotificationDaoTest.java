package db;

import java.util.Arrays;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import core.Notification;

public class NotificationDaoTest {
  NotificationDao notificationDao;
  Notification a1;
  Notification a2;


  @Before
  public void setUp() throws Exception {
    this.notificationDao = Mockito.mock(NotificationDao.class);
    this.a1 = new Notification(1111, 11, "Bid ends");
    this.a2 = new Notification(2222, 22, "BBB");
    Mockito.when(this.notificationDao.createNotification(this.a1)).thenReturn(this.a1);
    Mockito.when(this.notificationDao.createNotification(this.a2)).thenReturn(this.a2);
    Mockito.when(this.notificationDao.findALL()).thenReturn(Arrays.asList(this.a1, this.a2));
    Mockito.when(this.notificationDao.findALL()).thenReturn(Arrays.asList(this.a1, this.a2));
    Mockito.when(this.notificationDao.findNotificationByID(new Long(1)))
        .thenReturn(Optional.ofNullable(this.a1));
    Mockito.when(this.notificationDao.findNotificationByID(new Long(2)))
        .thenReturn(Optional.ofNullable(this.a2));
    Mockito.when(this.notificationDao.findNotificationByUserID(new Long(1111)))
        .thenReturn(Arrays.asList(this.a1));
    Mockito.when(this.notificationDao.findNotificationByUserID(new Long(2222)))
        .thenReturn(Arrays.asList(this.a2));
    Mockito.when(this.notificationDao.findNotificationByTransactionID(new Long(11)))
        .thenReturn(Arrays.asList(this.a1));
    Mockito.when(this.notificationDao.findNotificationByTransactionID(new Long(22)))
        .thenReturn(Arrays.asList(this.a2));

  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testCreateNotification() {
    Assert.assertEquals(this.notificationDao.createNotification(this.a1), this.a1);
    Assert.assertEquals(this.notificationDao.createNotification(this.a2), this.a2);

  }

  @Test
  public void testFindAllNotification() {
    Assert.assertEquals(this.notificationDao.findALL(), Arrays.asList(this.a1, this.a2));
  }

  @Test
  public void testFindNotificationByID() {
    Assert.assertEquals(this.notificationDao.findNotificationByID(new Long(1)),
        Optional.ofNullable(this.a1));
    Assert.assertEquals(this.notificationDao.findNotificationByID(new Long(2)),
        Optional.ofNullable(this.a2));
  }

  @Test
  public void testFindNotificationByUserID() {
    Assert.assertEquals(this.notificationDao.findNotificationByUserID(new Long(1111)),
        Arrays.asList(this.a1));
    Assert.assertEquals(this.notificationDao.findNotificationByUserID(new Long(2222)),
        Arrays.asList(this.a2));
  }

  @Test
  public void testFindNotificationByTransactionID() {
    Assert.assertEquals(this.notificationDao.findNotificationByTransactionID(new Long(11)),
        Arrays.asList(this.a1));
    Assert.assertEquals(this.notificationDao.findNotificationByTransactionID(new Long(22)),
        Arrays.asList(this.a2));
  }


}
