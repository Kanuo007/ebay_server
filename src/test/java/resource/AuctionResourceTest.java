package resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import core.BidHistory;
import core.Item;
import core.User;
import db.BidHistoryDao;
import db.ItemDao;

public class AuctionResourceTest {

  ItemDao mockedItemDao;
  BidHistoryDao mockedBidHistoryDao;
  SimpleDateFormat ft;
  BidHistory bidHistory1;
  BidHistory bidHistory2;
  BidHistory bidHistory3;
  Item item1;
  Item item2;
  Optional<Item> emptyItem = Optional.empty();
  Optional<Item> itemToBid1 = Optional.of(this.item1);
  Optional<Item> itemToBid2 = Optional.of(this.item2);

  @Before
  public void setUp() throws Exception {
    this.ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.mockedItemDao = Mockito.mock(ItemDao.class);
    this.mockedBidHistoryDao = Mockito.mock(BidHistoryDao.class);

    this.item1 = new Item(1570l, "Computer");
    this.item1.setId(1l);// item1 has been bid for two times

    this.item2 = new Item(777l, "Desk");
    this.item2.setId(2l);// item2 hasn't been ready to bid

    // 成功
    // 出价低了
    // item状态为不可bid
    // item id不存在


    this.bidHistory1 =
        new BidHistory(new Long(1), new Long(544), this.ft.parse("2016-07-21 10:40:42"), 25.0);
    this.bidHistory2 =
        new BidHistory(new Long(2), new Long(222), new Date(1, 1, 4, 8, 14, 47), 99.34);
    this.bidHistory3 = new BidHistory(new Long(1), new Long(256), new Date(1, 1, 1, 3, 7, 9), 50.0);
    Mockito.when(this.mockedItemDao.findItemByID(1l)).thenReturn(this.itemToBid1);
    Mockito.when(this.mockedItemDao.findItemByID(2l)).thenReturn(this.itemToBid2);
    Mockito.when(this.mockedItemDao.findItemByID(3l)).thenReturn(this.emptyItem);
  }

  @After
  public void tearDown() throws Exception {}



  @Test
  public void testBid() {
    User user = new User("yang", "123", "yang@gmail.com");
    BidHistory b1 =;
  }



}
