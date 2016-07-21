package core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TransactionTest {

  private Transaction tran1;
  private Transaction tran2;
  private Transaction tran3;
  private Transaction tran4;

  @Before
  public void setUp() throws Exception {
    this.tran1 = new Transaction();
    this.tran2 = new Transaction();
    this.tran3 = new Transaction();
    this.tran4 = new Transaction();

  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testHashCode() {
    Assert.assertTrue(this.tran1.hashCode() == this.tran1.hashCode());
    Assert.assertTrue(this.tran1.hashCode() == this.tran2.hashCode());
    Assert.assertTrue(this.tran2.hashCode() == this.tran3.hashCode());
  }

  @Test
  public void testEqualsObject() {
    // test Reflexive: for any non-null reference value x, x.equals(x) should return true
    Assert.assertEquals(this.tran1.equals(this.tran1), true);
    Assert.assertEquals(this.tran1.equals(this.tran4), false);
    Assert.assertEquals(this.tran2.equals(new Integer(4)), false);
    // test Symmetric: for any non-null reference values x and y, x.equals(y) should return true if
    // and only if y.equals(x) returns true.
    Assert.assertEquals(this.tran1.equals(this.tran2), true);
    Assert.assertEquals(this.tran2.equals(this.tran1), true);
    // test Transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true
    // and y.equals(z) returns true, then x.equals(z) should return true.
    Assert.assertEquals(this.tran1.equals(this.tran2), true);
    Assert.assertEquals(this.tran1.equals(this.tran3), true);
    Assert.assertEquals(this.tran2.equals(this.tran3), true);
    // test any non-null reference value x, x.equals(null) should return false
    Assert.assertEquals(this.tran1.equals(null), false);
    Assert.assertEquals(this.tran2.equals(null), false);
    // hashCode and equal
    Assert.assertTrue(
        this.tran1.equals(this.tran2) ? this.tran1.hashCode() == this.tran2.hashCode() : true);
    Assert.assertTrue(this.tran1.hashCode() == this.tran2.hashCode());
    Assert.assertTrue(this.tran1.hashCode() == this.tran2.hashCode());



  }

}
