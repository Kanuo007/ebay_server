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
    Assert.fail("Not yet implemented");
  }

  @Test
  public void testEqualsObject() {
    Assert.fail("Not yet implemented");
  }

}
