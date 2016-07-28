package db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import core.BidHistory;

public class BidHistoryDAOTest {
  BidHistoryDao mockedBidHistoryDao;
  List<BidHistory> list;
  BidHistory bidHistory;
  SimpleDateFormat ft;

  @Before
  public void setUp() throws Exception {
    this.ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.mockedBidHistoryDao = Mockito.mock(BidHistoryDao.class);
    this.bidHistory = new BidHistory(32l, 1043l, this.ft.parse("2014-11-21 23:00:00"), 10.5);

    Mockito.when(this.mockedBidHistoryDao.findBidHistoryByID(Matchers.anyLong()))
        .thenReturn(this.bidHistory);
    Mockito.when(this.mockedBidHistoryDao.createBidHistory(this.bidHistory))
        .thenReturn(this.bidHistory);
    Mockito.when(this.mockedBidHistoryDao.findAllBidHistory())
        .thenReturn(Arrays.asList(this.bidHistory));
    Mockito.when(this.mockedBidHistoryDao.findByBidderId(Matchers.anyLong()))
        .thenReturn(Arrays.asList(this.bidHistory));
    Mockito.when(this.mockedBidHistoryDao.findByItemId(Matchers.anyLong()))
        .thenReturn(Arrays.asList(this.bidHistory));
    Mockito.when(this.mockedBidHistoryDao.findByHighestPriceByItemId(Matchers.anyLong()))
        .thenReturn(Optional.ofNullable(this.bidHistory));
    Mockito.when(this.mockedBidHistoryDao.findByBidTime((Date) Matchers.any()))
        .thenReturn(Arrays.asList(this.bidHistory));
  }

  @After
  public void tearDown() throws Exception {}


  @Test
  public void testFindBidHistoryByID() {
    Assert.assertEquals(this.mockedBidHistoryDao.findBidHistoryByID(5432l), this.bidHistory);
    Mockito.verify(this.mockedBidHistoryDao).findBidHistoryByID(Matchers.anyLong());
  }

  @Test
  public void testCreateBidHistory() {
    Assert.assertEquals(this.mockedBidHistoryDao.createBidHistory(this.bidHistory),
        this.bidHistory);
    Mockito.verify(this.mockedBidHistoryDao).createBidHistory(this.bidHistory);
  }

  @Test
  public void testFindAllBidHistory() {
    Assert.assertEquals(this.mockedBidHistoryDao.findAllBidHistory(),
        Arrays.asList(this.bidHistory));
    Mockito.verify(this.mockedBidHistoryDao).findAllBidHistory();
  }

  @Test
  public void testFindByBidderId() {
    Assert.assertEquals(this.mockedBidHistoryDao.findByBidderId(1043l),
        Arrays.asList(this.bidHistory));
    Mockito.verify(this.mockedBidHistoryDao).findByBidderId(Matchers.anyLong());
  }

  @Test
  public void testFindByItemId() {
    Assert.assertEquals(this.mockedBidHistoryDao.findByItemId(32l), Arrays.asList(this.bidHistory));
    Mockito.verify(this.mockedBidHistoryDao).findByItemId(Matchers.anyLong());
  }

  @Test
  public void testFindByTopPriceById() {
    Assert.assertEquals(this.mockedBidHistoryDao.findByHighestPriceByItemId(32l),
        Optional.ofNullable(this.bidHistory));
    Mockito.verify(this.mockedBidHistoryDao).findByHighestPriceByItemId(Matchers.anyLong());
  }

  @Test
  public void testFindByBidTime() throws ParseException {
    Assert.assertEquals(
        this.mockedBidHistoryDao.findByBidTime(this.ft.parse("2014-11-21 23:00:00")),
        Arrays.asList(this.bidHistory));
    Mockito.verify(this.mockedBidHistoryDao).findByBidTime((Date) Matchers.any());
  }

}
