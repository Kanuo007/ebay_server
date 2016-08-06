package com.betterebay.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class ItemTest {

  private Item a1;
  private Item a2;
  private Item a3;
  private Item a4;
  private Item a5;

  @Before
  public void setUp() throws Exception {
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.a1 = new Item(new Long(1), "book", 10.05, true, ft.parse("2016-07-21 10:10:10"),
            ft.parse("2016-07-22 10:10:10"));
    this.a2 = new Item(new Long(1), "book", 10.05, true, ft.parse("2016-07-21 10:10:10"),
            ft.parse("2016-07-22 10:10:10"));
    this.a3 = new Item(new Long(1), "book", 10.05, true, ft.parse("2016-07-21 10:10:10"),
            ft.parse("2016-07-22 10:10:10"));

    this.a4 = new Item(new Long(2), "shoes", 100.0, false, ft.parse("2016-07-21 1:1:1"),
            ft.parse("2016-07-21 1:1:1"), "A-catagory", 7, "green", 10, "AAA");
    this.a5 = new Item(new Long(2), "64", 89.0, true, ft.parse("2016-07-22 1:1:1"),
            ft.parse("2016-07-21 1:1:1"), "B-catagory", 8, "red", 11, "BBB");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetId() {
    Assert.assertEquals(this.a1.getId(), null);
  }

  @Test
  public void testGetUserID() {
    Assert.assertEquals(this.a1.getUserID(), new Long(1));
    Assert.assertNotEquals(this.a1.getUserID(), new Long(2));
  }

  @Test
  public void testGetName() {
    Assert.assertEquals(this.a1.getName(), "book");
    Assert.assertNotEquals(this.a4.getName(), "book");
  }

  @Test
  public void testGetColor() {
    Assert.assertEquals(this.a1.getColor(), null);
    Assert.assertEquals(this.a4.getColor(), "green");
  }

  @Test
  public void testGetSize() {
    Assert.assertEquals(this.a1.getSize(), null);
    Assert.assertEquals(this.a4.getSize(), new Integer(7));
  }

  @Test
  public void testGetCatagory() {
    Assert.assertEquals(this.a1.getCatagory(), null);
    Assert.assertEquals(this.a4.getCatagory(), "A-catagory");
    Assert.assertEquals(this.a5.getCatagory(), "B-catagory");
  }

  @Test
  public void testGetDiscription() {
    Assert.assertEquals(this.a1.getDescription(), null);
    Assert.assertEquals(this.a4.getDescription(), "AAA");
    Assert.assertNotEquals(this.a4.getDescription(), "JJJ");
  }

  @Test
  public void testGetStatus() {
    Assert.assertEquals(this.a1.getStatus(), true);
    Assert.assertEquals(this.a4.getStatus(), false);
  }

  @Test
  public void testGetBid_start_time() throws ParseException {
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Assert.assertEquals(this.a1.getBid_start_time(), ft.parse("2016-07-21 10:10:10"));
    Assert.assertNotEquals(this.a1.getBid_start_time(), ft.parse("2016-07-22 11:13:10"));
  }

  @Test
  public void testGetBid_end_time() throws ParseException {
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Assert.assertEquals(this.a1.getBid_end_time(), ft.parse("2016-07-22 10:10:10"));
    Assert.assertNotEquals(this.a1.getBid_end_time(), ft.parse("2016-07-21 11:13:10"));
  }

  @Test
  public void testGetBase_price() {
    Assert.assertTrue(this.a1.getBase_price() == 10.05);
    Assert.assertFalse(this.a4.getBase_price() == 1);
  }

  @Test
  public void testGetDeliver_fee() {
    Assert.assertNotEquals(this.a1.getDeliver_fee(), new Double(10));
    Assert.assertEquals(this.a4.getDeliver_fee(), new Integer(10));
  }

  @Test
  public void testSetStatuse() {
    this.a1.setStatus(false);
    Assert.assertEquals(false, this.a1.getStatus());
  }

  @Test
  public void testCheckEndTime() throws ParseException {
    Assert.assertEquals(this.a1.checkEndTime(this.a1), true);
  }


  @Test
  public void testEqualsObject() {
    Assert.assertFalse(this.a1.equals(1));
    Assert.assertFalse(this.a1.equals(this.a4));
    Assert.assertFalse(this.a4.equals(this.a5));
    Assert.assertTrue(this.a1.equals(this.a2));
    Assert.assertTrue(this.a1.equals(this.a3));
    Assert.assertTrue(this.a2.equals(this.a3));
    Assert.assertTrue(this.a2.equals(this.a3) && this.a3.equals(this.a2));
  }

}
