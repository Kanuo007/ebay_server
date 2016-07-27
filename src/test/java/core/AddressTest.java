package core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressTest {
  private Address add1;
  private Address add2;
  private Address add3;

  @Before
  public void setUp() throws Exception {
    this.add1 = new Address("110 Baker St", "Seattle", "USA", new Long(98111), new Long(23));
    this.add2 = new Address("110 Baker St", "Seattle", "USA", new Long(98111), new Long(23));
    this.add3 = new Address("111 Baker St", "Seattle", "USA", new Long(98111), new Long(24));

  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testHashCode() {
    Assert.assertTrue(this.add1.hashCode() == this.add1.hashCode());
    Assert.assertTrue(this.add1.hashCode() == this.add2.hashCode());
  }


  @Test
  public void testGetStreet() {
    Assert.assertEquals(this.add1.getStreet(), "110 Baker St");
  }

  @Test
  public void testSetStreet() {
    this.add3.setStreet("Plum St");
    Assert.assertEquals(this.add3.getStreet(), "Plum St");
  }

  @Test
  public void testGetCity() {
    Assert.assertEquals(this.add1.getCity(), "Seattle");
  }

  @Test
  public void testSetCity() {
    this.add3.setCity("New York");
    Assert.assertEquals(this.add3.getCity(), "New York");
  }

  @Test
  public void testGetCountry() {
    Assert.assertEquals(this.add1.getCountry(), "USA");
  }

  @Test
  public void testSetCountry() {
    this.add3.setCountry("China");
    Assert.assertEquals(this.add3.getCountry(), "China");
  }

  @Test
  public void testGetZipcode() {
    Assert.assertEquals(this.add1.getZipcode(), new Long(98111));
  }

  @Test
  public void testSetZipcode() {
    this.add3.setZipcode(new Long(98102));
    Assert.assertEquals(this.add3.getZipcode(), new Long(98102));
  }

  @Test
  public void testGetUser_id() {
    Assert.assertEquals(this.add1.getUser_id(), new Long(23));
  }

  @Test
  public void testSetUser_id() {
    this.add3.setUser_id(new Long(22));
    Assert.assertEquals(this.add3.getUser_id(), new Long(22));
  }

  @Test
  public void testEqualsObject() {
    // test Reflexive: for any non-null reference value x, x.equals(x) should return true
    Assert.assertEquals(this.add1.equals(this.add1), true);
    Assert.assertEquals(this.add2.equals(this.add2), true);
    Assert.assertEquals(this.add1.equals(new Integer(4)), false);
    Assert.assertEquals(this.add1.equals(null), false);
    // test Symmetric: for any non-null reference values x and y, x.equals(y) should return true if
    // and only if y.equals(x) returns true.
    Assert.assertEquals(this.add1.equals(this.add2), true);
    Assert.assertEquals(this.add2.equals(this.add1), true);


    Assert.assertEquals(this.add1.equals(this.add3), false);
    Assert.assertEquals(this.add2.equals(this.add3), false);
    // test any non-null reference value x, x.equals(null) should return false
    Assert.assertEquals(this.add1.equals(null), false);
    Assert.assertEquals(this.add2.equals(null), false);
    // hashCode and equal
    Assert.assertTrue(
        this.add1.equals(this.add2) ? this.add1.hashCode() == this.add2.hashCode() : true);
    Assert.assertTrue(this.add1.hashCode() == this.add2.hashCode());
  }

}
