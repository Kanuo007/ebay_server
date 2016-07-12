package core;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BidHistoryTest {
  BidHistory b1;
  BidHistory b2;
  BidHistory b3;
  BidHistory b4;

  @Before
  public void setUp() throws Exception {
    this.b1 = new BidHistory(new Long(111), new Long(222), new Date(1, 1, 1, 1, 1, 1), 25.34);
    this.b2 = new BidHistory(new Long(111), new Long(222), new Date(1, 1, 1, 1, 1, 1), 25.34);
    this.b3 = new BidHistory(new Long(111), new Long(222), new Date(1, 1, 1, 1, 1, 1), 25.34);
    this.b4 = new BidHistory(new Long(222), new Long(222), new Date(1, 1, 1, 1, 1, 1), 25.34);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testHashCode() {
    Assert.assertEquals(this.b1.hashCode(), this.b2.hashCode());
    Assert.assertNotEquals(this.b1.hashCode(), new Integer(1314).hashCode());
  }


  @Test
  public void testGetId() {
    Assert.assertEquals(this.b1.getId(), null);
  }

  @Test
  public void testGetItemId() {
    Assert.assertEquals(this.b1.getItemId(), new Long(111));

  }

  @Test
  public void testGetBidderId() {
    Assert.assertEquals(this.b1.getBidderId(), new Long(222));
  }

  @Test
  public void testGetBidTime() {
    Assert.assertEquals(this.b1.getBidTime(), new Date(1, 1, 1, 1, 1, 1));
  }

  @Test
  public void testGetBidPrice() {
    Assert.assertEquals(this.b1.getBidPrice(), 25.34, 0);
  }

  @Test
  public void testSetId() {
    Assert.assertEquals(this.b1.getId(), null);
    this.b1.setId(new Long(15));
    Assert.assertEquals(this.b1.getId(), new Long(15));
  }

  @Test
  public void testSetItemId() {
    Assert.assertEquals(this.b1.getItemId(), new Long(111));
    this.b1.setItemId(new Long(112));
    Assert.assertEquals(this.b1.getItemId(), new Long(112));
  }

  @Test
  public void testSetBidderId() {
    Assert.assertEquals(this.b1.getBidderId(), new Long(222));
    this.b1.setBidderId(new Long(333));
    Assert.assertEquals(this.b1.getBidderId(), new Long(333));
  }

  @Test
  public void testSetBidTime() {
    Assert.assertEquals(this.b1.getBidTime(), new Date(1, 1, 1, 1, 1, 1));
    this.b1.setBidTime(new Date(2, 2, 2, 2, 2, 2));
    Assert.assertEquals(this.b1.getBidTime(), new Date(2, 2, 2, 2, 2, 2));

  }

  @Test
  public void testSetBidPrice() {
    Assert.assertEquals(this.b1.getBidPrice(), 25.34, 0);
    this.b1.setBidPrice(11.11);
    Assert.assertEquals(this.b1.getBidPrice(), 11.11, 0);
  }

  @Test
  public void testEqualsObject() {
    Assert.assertFalse(this.b1.equals(null));
    Assert.assertFalse(this.b1.equals(new Integer(1314)));
    Assert.assertFalse(this.b1.equals(this.b4));

    // reflexive
    Assert.assertTrue(this.b1.equals(this.b1));

    // symmetric
    Assert.assertTrue(this.b1.equals(this.b2) && this.b2.equals(this.b1));

    // transitive
    Assert.assertTrue(this.b1.equals(this.b2) && this.b2.equals(this.b1));
    Assert.assertTrue(this.b2.equals(this.b3) && this.b3.equals(this.b2));
    Assert.assertTrue(this.b1.equals(this.b3));

    // consistent
    Assert.assertTrue(this.b1.equals(this.b2));
  }

}
