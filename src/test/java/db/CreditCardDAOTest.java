package db;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import core.CreditCard;

public class CreditCardDAOTest {
  CreditCardDao mockedCreditCardDao;
  CreditCard creditCard;

  @Before
  public void setUp() throws Exception {
    this.mockedCreditCardDao = Mockito.mock(CreditCardDao.class);
    this.creditCard = new CreditCard(new Long(256), "Master Card", new Long("5239598888888888"),
        "Lily Yang", 223, "2021", "07", "225 Terry Ave, Seattle, WA, 98109");

    Mockito.when(this.mockedCreditCardDao.findCreditCardByID(Matchers.anyLong()))
        .thenReturn(this.creditCard);
    Mockito.when(this.mockedCreditCardDao.createCreditCard(this.creditCard))
        .thenReturn(this.creditCard);
    Mockito.when(this.mockedCreditCardDao.findAllCreditCard())
        .thenReturn(Arrays.asList(this.creditCard));
    Mockito.when(this.mockedCreditCardDao.findByUserId(Matchers.anyLong()))
        .thenReturn(Arrays.asList(this.creditCard));
  }

  @After
  public void tearDown() throws Exception {}


  @Test
  public void testFindCreditCardByID() {
    Assert.assertEquals(this.mockedCreditCardDao.findCreditCardByID(24l), this.creditCard);
    Mockito.verify(this.mockedCreditCardDao).findCreditCardByID(Matchers.anyLong());
  }

  @Test
  public void testCreateCreditCard() {
    Assert.assertEquals(this.mockedCreditCardDao.createCreditCard(this.creditCard),
        this.creditCard);
    Mockito.verify(this.mockedCreditCardDao).createCreditCard(this.creditCard);

  }

  @Test
  public void testFindAllCreditCard() {
    Assert.assertEquals(this.mockedCreditCardDao.findAllCreditCard(),
        Arrays.asList(this.creditCard));
    Mockito.verify(this.mockedCreditCardDao).findAllCreditCard();
  }

  @Test
  public void testFindByUserId() {
    Assert.assertEquals(this.mockedCreditCardDao.findByUserId(256l),
        Arrays.asList(this.creditCard));
    Mockito.verify(this.mockedCreditCardDao).findByUserId(Matchers.anyLong());
  }

}
