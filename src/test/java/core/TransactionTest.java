package core;

import java.text.SimpleDateFormat;

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
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.tran1 = new Transaction(new Long(1), new Long(12), new Long(123), new Long(1234),
        new String("Great Product"), ft.parse("2016-10-18 13:30:00"));
    this.tran2 = new Transaction(new Long(1), new Long(12), new Long(123), new Long(1234),
        new String("Great Product"), ft.parse("2016-10-18 13:30:00"));
    this.tran3 = new Transaction(new Long(1), new Long(12), new Long(123), new Long(1234),
        new String("Great Product"), ft.parse("2016-10-18 13:30:00"));
    this.tran4 = new Transaction(new Long(2), new Long(22), new Long(223), new Long(2234),
        new String("Not bad"), ft.parse("2016-10-18 03:30:00"));

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
