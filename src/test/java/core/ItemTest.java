package core;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
  private Item a1;
  private Item a2;
  private Item a3;
  private Item a4;
  private Item a5;

  @Before
  public void setUp() throws Exception {
    this.a1 = new Item(new Integer(1), "book", 10.05, true, new Date(2016, 07, 22, 10, 10, 10),
        new Date(2016, 07, 23, 10, 10, 10));

    this.a2 = new Item(new Integer(1), "book", 10.05, true, new Date(2016, 07, 22, 10, 10, 10),
        new Date(2016, 07, 23, 10, 10, 10));
    this.a3 = new Item(new Integer(1), "book", 10.05, true, new Date(2016, 07, 22, 10, 10, 10),
        new Date(2016, 07, 23, 10, 10, 10));

    this.a4 = new Item(new Integer(2), "shoes", 100, false, new Date(2016, 07, 22, 10, 10, 10),
        new Date(2016, 07, 23, 10, 10, 10), "A-catagory", 7, "green", 10, "AAA");
    this.a5 = new Item(new Integer(2), "shoes", 100, false, new Date(2016, 07, 22, 10, 10, 10),
        new Date(2016, 07, 23, 10, 10, 10), "A-catagory", 7, "green", 10, "AAA");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetId() {
    Assert.assertEquals(this.a1.getId(), null);
  }

  @Test
  public void testGetUserID() {
    Assert.assertEquals(this.a1.getUserID(), 1);
    Assert.assertNotEquals(this.a1.getUserID(), 2);
  }

  @Test
  public void testGetName() {
    Assert.assertEquals(this.a1.getName(), "book");
    Assert.assertNotEquals(this.a2.getName(), "book");
  }

  @Test
  public void testGetColor() {
    Assert.assertEquals(this.a1.getColor(), null);
    Assert.assertEquals(this.a2.getColor(), "green");
  }

  @Test
  public void testGetSize() {
    Assert.assertEquals(this.a1.getSize(), null);
    Assert.assertEquals(this.a2.getColor(), 10);
  }

  @Test
  public void testGetCatagory() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testGetStatus() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testGetBid_start_time() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testGetBid_end_time() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testGetBase_price() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testGetDeliver_fee() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testGetDescription() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetId() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetUserID() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetName() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetColor() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetSize() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetCatagory() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetStatus() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetBid_start_time() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetBid_end_time() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetBase_price() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetDeliver_fee() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testSetDescription() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testEqualsObject() {
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testCheckEndTime() {
    Assert.fail("Not yet implemented");
  }

}
