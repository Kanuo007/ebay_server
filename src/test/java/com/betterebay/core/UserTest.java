package com.betterebay.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
  private User user1;
  private User user2;
  private User user3;

  @Before
  public void setUp() throws Exception {
    this.user1 = new User("John Snow", "winterfall", "johnsnow@gmail.com");
    this.user2 = new User("John Snow", "winterfall", "johnsnow@gmail.com");
    this.user3 = new User("Lady Bella", "moutain", "ladybella@gmail.com");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testHashCode() {
    Assert.assertTrue(this.user1.hashCode() == this.user1.hashCode());
    Assert.assertTrue(this.user1.hashCode() == this.user2.hashCode());
  }

  @Test
  public void testGetName() {
    Assert.assertTrue(this.user1.getName() == "John Snow");
  }

  @Test
  public void testGetUser_password() {
    Assert.assertTrue(this.user1.getUser_password() == "winterfall");
  }

  @Test
  public void testSetUser_password() {
    this.user3.setUser_password("12345");
    Assert.assertTrue(this.user3.getUser_password() == "12345");
  }

  @Test
  public void testGetUser_email() {
    Assert.assertTrue(this.user1.getUser_email() == "johnsnow@gmail.com");
  }

  @Test
  public void testSetUser_email() {
    this.user3.setUser_email("guest@gmail.com");
    Assert.assertTrue(this.user3.getUser_email() == "guest@gmail.com");
  }

  @Test
  public void testEqualsObject() {
    // test Reflexive: for any non-null reference value x, x.equals(x) should return true
    Assert.assertEquals(this.user1.equals(this.user1), true);
    Assert.assertEquals(this.user2.equals(this.user2), true);
    Assert.assertEquals(this.user1.equals(new Integer(4)), false);
    Assert.assertEquals(this.user1.equals(null), false);

    Assert.assertEquals(this.user1.equals(this.user2), true);
    Assert.assertEquals(this.user2.equals(this.user1), true);

    Assert.assertEquals(this.user1.equals(this.user3), false);

    Assert.assertEquals(this.user1.equals(null), false);
    Assert.assertEquals(this.user2.equals(null), false);

    Assert.assertTrue(
        this.user1.equals(this.user2) ? this.user1.hashCode() == this.user2.hashCode() : true);
    Assert.assertTrue(this.user1.hashCode() == this.user2.hashCode());
  }

}
