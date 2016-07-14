package api;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author LiYang
 *
 */
public class RegisterTest {
  private Register r1;
  private Register r2;
  private Register r3;
  private Register r4;

  @Before
  public void setUp() throws Exception {
    this.r1 = new Register("Lily", "li@gmail.com", "aa", "success");
    this.r2 = new Register("Lily", "li@gmail.com", "aa", "success");
    this.r3 = new Register("Lily", "li@gmail.com", "aa", "success");
    this.r4 = new Register("lily", "li@gmail.com", "aa", "success");
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGetName() {
    Assert.assertEquals(this.r1.getName(), "Lily");
  }

  @Test
  public void testGetEmail() {
    Assert.assertEquals(this.r1.getEmail(), "li@gmail.com");
  }

  @Test
  public void testGetPassword() {
    Assert.assertEquals(this.r1.getPassword(), "aa");
  }

  @Test
  public void testGetStatus() {
    Assert.assertEquals(this.r1.getStatus(), "success");
  }


  @Test
  public void testSetName() {
    Assert.assertEquals(this.r1.getName(), "Lily");
    this.r1.setName("bao");
    Assert.assertNotEquals(this.r1.getName(), "Lily");
    Assert.assertEquals(this.r1.getName(), "bao");
  }

  @Test
  public void testSetEmail() {
    Assert.assertEquals(this.r1.getEmail(), "li@gmail.com");
    this.r1.setEmail("bao@yahoo.com");
    Assert.assertNotEquals(this.r1.getEmail(), "li@gmail.com");
    Assert.assertEquals(this.r1.getEmail(), "bao@yahoo.com");
  }

  @Test
  public void testSetPassword() {
    Assert.assertEquals(this.r1.getPassword(), "aa");
    this.r1.setPassword("ba");
    Assert.assertNotEquals(this.r1.getPassword(), "aa");
    Assert.assertEquals(this.r1.getPassword(), "ba");
  }


  @Test
  public void testSetStatus() {
    Assert.assertEquals(this.r1.getStatus(), "success");
    this.r1.setStatus("failure");
    Assert.assertNotEquals(this.r1.getStatus(), "success");
    Assert.assertEquals(this.r1.getStatus(), "failure");
  }


  @Test
  public void testHashCode() {

    Assert.assertEquals(this.r1.hashCode(), this.r2.hashCode());
    Assert.assertFalse(this.r1.hashCode() == new Integer(2).hashCode());
  }

  @Test
  public void testEqualsObject() {
    Assert.assertFalse(this.r1.equals(null));
    Assert.assertFalse(this.r1.equals(new Integer(1314)));
    Assert.assertFalse(this.r1.equals(this.r4));

    // reflexive
    Assert.assertTrue(this.r1.equals(this.r1));

    // symmetric
    Assert.assertTrue(this.r1.equals(this.r2) && this.r2.equals(this.r1));

    // transitive
    Assert.assertTrue(this.r1.equals(this.r2) && this.r2.equals(this.r1));
    Assert.assertTrue(this.r2.equals(this.r3) && this.r3.equals(this.r2));
    Assert.assertTrue(this.r1.equals(this.r3));

    // consistent
    Assert.assertTrue(this.r1.equals(this.r2));
  }
}
