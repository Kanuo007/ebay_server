package com.betterebay.core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreditCardTest {
  CreditCard c1;
  CreditCard c2;
  CreditCard c3;
  CreditCard c4;

  @Before
  public void setUp() throws Exception {
    this.c1 = new CreditCard(new Long(256), "Master Card", new Long("5239598888888888"),
        "Lily Yang", 223, "2021", "07", "225 Terry Ave, Seattle, WA, 98109");
    this.c2 = new CreditCard(new Long(256), "Master Card", new Long("5239598888888888"),
        "Lily Yang", 223, "2021", "07", "225 Terry Ave, Seattle, WA, 98109");
    this.c3 = new CreditCard(new Long(256), "Master Card", new Long("5239598888888888"),
        "Lily Yang", 223, "2021", "07", "225 Terry Ave, Seattle, WA, 98109");
    this.c4 = new CreditCard(new Long(256), "Visa Card", new Long("5239598888888888"), "Lily Yang",
        223, "2021", "07", "225 Terry Ave, Seattle, WA, 98109");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testHashCode() {
    Assert.assertEquals(this.c1.hashCode(), this.c2.hashCode());
    Assert.assertNotEquals(this.c1.hashCode(), new Integer(2).hashCode());
  }

  @Test
  public void testGetId() {
    Assert.assertEquals(this.c1.getId(), null);
  }

  @Test
  public void testGetUserId() {
    Assert.assertEquals(this.c1.getUserId(), new Long(256));
  }

  @Test
  public void testGetCreditCardType() {
    Assert.assertEquals(this.c1.getCreditCardType(), "Master Card");
  }

  @Test
  public void testGetCardNumber() {
    Assert.assertEquals(this.c1.getCardNumber(), new Long("5239598888888888"));
  }

  @Test
  public void testGetCardHolder() {
    Assert.assertEquals(this.c1.getCardHolder(), "Lily Yang");
  }

  @Test
  public void testGetCvv() {
    Assert.assertEquals(this.c1.getCvv(), 223);
  }

  @Test
  public void testGetExpirationYear() {
    Assert.assertEquals(this.c1.getExpirationYear(), "2021");
  }

  @Test
  public void testGetExpirationMonth() {
    Assert.assertEquals(this.c1.getExpirationMonth(), "07");
  }

  @Test
  public void testGetBillingAddress() {
    Assert.assertEquals(this.c1.getBillingAddress(), "225 Terry Ave, Seattle, WA, 98109");
  }

  @Test
  public void testSetId() {
    Assert.assertEquals(this.c1.getId(), null);
    this.c1.setId(new Long(9));
    Assert.assertEquals(this.c1.getId(), new Long(9));
  }

  @Test
  public void testSetUserId() {
    Assert.assertEquals(this.c1.getUserId(), new Long(256));
    this.c1.setUserId(new Long(1));
    Assert.assertEquals(this.c1.getUserId(), new Long(1));
  }

  @Test
  public void testSetCreditCardType() {
    Assert.assertEquals(this.c1.getCreditCardType(), "Master Card");
    this.c1.setCreditCardType("Visa Card");
    Assert.assertEquals(this.c1.getCreditCardType(), "Visa Card");

  }

  @Test
  public void testSetCardNumber() {
    Assert.assertEquals(this.c1.getCardNumber(), new Long("5239598888888888"));
    this.c1.setCardNumber(new Long("5239598888888899"));
    Assert.assertEquals(this.c1.getCardNumber(), new Long("5239598888888899"));

  }

  @Test
  public void testSetCardHolder() {
    Assert.assertEquals(this.c1.getCardHolder(), "Lily Yang");
    this.c1.setCardHolder("Bao");
    Assert.assertEquals(this.c1.getCardHolder(), "Bao");
  }

  @Test
  public void testSetCvv() {
    Assert.assertEquals(this.c1.getCvv(), 223);
    this.c1.setCvv(111);
    Assert.assertEquals(this.c1.getCvv(), 111);
  }

  @Test
  public void testSetExpirationYear() {
    Assert.assertEquals(this.c1.getExpirationYear(), "2021");
    this.c1.setExpirationYear("2025");
    Assert.assertEquals(this.c1.getExpirationYear(), "2025");
  }

  @Test
  public void testSetExpirationMonth() {
    Assert.assertEquals(this.c1.getExpirationMonth(), "07");
    this.c1.setExpirationMonth("12");
    Assert.assertEquals(this.c1.getExpirationMonth(), "12");

  }

  @Test
  public void testSetBillingAddress() {
    Assert.assertEquals(this.c1.getBillingAddress(), "225 Terry Ave, Seattle, WA, 98109");
    this.c1.setBillingAddress("2601 SW Archer Road, Gainesville, FL, 32608");
    Assert.assertEquals(this.c1.getBillingAddress(), "2601 SW Archer Road, Gainesville, FL, 32608");

  }

  @Test
  public void testEqualsObject() {
    Assert.assertFalse(this.c1.equals(null));
    Assert.assertFalse(this.c1.equals(new Integer(1314)));
    Assert.assertFalse(this.c1.equals(this.c4));

    // reflexive
    Assert.assertTrue(this.c1.equals(this.c1));

    // symmetric
    Assert.assertTrue(this.c1.equals(this.c2) && this.c2.equals(this.c1));

    // transitive
    Assert.assertTrue(this.c1.equals(this.c2) && this.c2.equals(this.c1));
    Assert.assertTrue(this.c2.equals(this.c3) && this.c3.equals(this.c2));
    Assert.assertTrue(this.c1.equals(this.c3));

    // consistent
    Assert.assertTrue(this.c1.equals(this.c2));
  }

}
