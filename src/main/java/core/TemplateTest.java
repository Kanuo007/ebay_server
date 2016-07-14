package core;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

public class TemplateTest {
  private Template template1;
  private Template template2;
  private Template template3;
  private Template template4;

  @Before
  public void setUp() throws Exception {
    this.template1 = new Template("Hello", "OnePiece");
    this.template2 = new Template("Hello", "OnePiece");
    this.template3 = new Template("Hello", "OnePiece");
    this.template4 = new Template("Hello", "Guest");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testFormatContent() {
    Assert.assertEquals(this.template4.formatContent(Optional.<String>absent()), "Hello");
  }

  @Test
  public void testHashCode() {
    Assert.assertTrue(this.template1.hashCode() == this.template1.hashCode());
    Assert.assertTrue(this.template1.hashCode() == this.template2.hashCode());
  }

  @Test
  public void testEqualsObject() {
    // test Reflexive: for any non-null reference value x, x.equals(x) should return true
    Assert.assertEquals(this.template1.equals(this.template1), true);
    Assert.assertEquals(this.template2.equals(this.template2), true);
    Assert.assertEquals(this.template1.equals(new Integer(4)), false);
    Assert.assertEquals(this.template1.equals(null), false);
    // test Symmetric: for any non-null reference values x and y, x.equals(y) should return true if
    // and only if y.equals(x) returns true.
    Assert.assertEquals(this.template1.equals(this.template2), true);
    Assert.assertEquals(this.template2.equals(this.template1), true);
    // test Transitive: for any non-null reference values x, y, and z, if x.equals(y) returns true
    // and y.equals(z) returns true, then x.equals(z) should return true.
    Assert.assertEquals(this.template1.equals(this.template2), true);
    Assert.assertEquals(this.template1.equals(this.template3), true);
    Assert.assertEquals(this.template2.equals(this.template3), true);
    // test any non-null reference value x, x.equals(null) should return false
    Assert.assertEquals(this.template1.equals(null), false);
    Assert.assertEquals(this.template2.equals(null), false);
    // hashCode and equal
    Assert.assertTrue(this.template1.equals(this.template2)
        ? this.template1.hashCode() == this.template2.hashCode() : true);
    Assert.assertTrue(this.template1.hashCode() == this.template2.hashCode());
    Assert.assertTrue(this.template1.hashCode() == this.template2.hashCode());
  }

}
