package com.betterebay.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NotificationTest {

  private Notification a1;
  private Notification a2;


  @Before
  public void setUp() throws Exception {
    this.a1 = new Notification(new Long(1), new Long(11), "Bid ends");
    this.a2 = new Notification(new Long(2), new Long(22), "BBB");


  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetId() {
    Assert.assertEquals(this.a1.getId(), null);
  }

  @Test
  public void testGetUser_id() {
    Assert.assertEquals(this.a1.getUser_id(), new Long(1));
    Assert.assertNotEquals(this.a1.getUser_id(), new Long(2));
  }

  @Test
  public void testGetTransaction_id() {
    Assert.assertEquals(this.a1.getTransaction_id(), new Long(11));
    Assert.assertNotEquals(this.a2.getTransaction_id(), new Long(111));
  }

  @Test
  public void testGetContent() {
    Assert.assertEquals(this.a2.getContent(), "BBB");
    Assert.assertNotEquals(this.a1.getTransaction_id(), "asdfad");
  }

  @Test
  public void testSetUser_id() {
    this.a1.setUser_id(new Long(000));
    Assert.assertEquals(this.a1.getUser_id(), new Long(000));
  }

  @Test
  public void testSetTransaction_id() {
    this.a1.setTransaction_id(new Long(123));
    Assert.assertEquals(this.a1.getTransaction_id(), new Long(123));
  }

  @Test
  public void testSetContent() {
    this.a1.setContent("ddd");
    Assert.assertEquals(this.a1.getContent(), "ddd");
  }

}
