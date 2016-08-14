package com.betterebay.db;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.betterebay.core.Item;



public class ItemDaoTest {
  private ItemDao itemDao;
  private Item a1;
  private Item a2;

  @Before
  public void setUp() throws Exception {
    this.itemDao = Mockito.mock(ItemDao.class);
    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.a1 = new Item(new Long(2), "shoes", 100.0, false, ft.parse("2016-07-21 1:1:1"),
        ft.parse("2016-07-21 1:1:1"), "A-catagory", 7, "green", 10, "AAA");
    this.a2 = new Item(new Long(1), "book", 10.05, true, ft.parse("2016-07-21 10:10:10"),
        ft.parse("2016-07-22 10:10:10"), "A-catagory", 7, "blue", 12, "AAA");

    Mockito.when(this.itemDao.findAllItem()).thenReturn(Arrays.asList(this.a1, this.a2));
    Mockito.when(this.itemDao.findAllItem()).thenReturn(Arrays.asList(this.a1, this.a2));
    Mockito.when(this.itemDao.findItemByID(new Long(1))).thenReturn(Optional.ofNullable(this.a1));
    Mockito.when(this.itemDao.findItemByID(new Long(2))).thenReturn(Optional.ofNullable(this.a2));
    Mockito.when(this.itemDao.findItemByName("shoes")).thenReturn(Arrays.asList(this.a1));
    Mockito.when(this.itemDao.findItemByName("book")).thenReturn(Arrays.asList(this.a2));
    Mockito.when(this.itemDao.findItemByNameColorSize("shoes", "green", 7))
        .thenReturn(Arrays.asList(this.a1));
    Mockito.when(this.itemDao.findItemByAvailability()).thenReturn(Arrays.asList(this.a2));
    Mockito.when(this.itemDao.createItem(this.a1)).thenReturn(this.a1);
    Mockito.when(this.itemDao.createItem(this.a2)).thenReturn(this.a2);
    Mockito.doNothing().when(this.itemDao).updateStatus(true, new Long(2));
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testFindItemByID() {
    Assert.assertEquals(this.itemDao.findItemByID(new Long(1)), Optional.ofNullable(this.a1));
    Assert.assertEquals(this.itemDao.findItemByID(new Long(2)), Optional.ofNullable(this.a2));
  }

  @Test
  public void testCreateItem() {
    Assert.assertEquals(this.itemDao.createItem(this.a1), this.a1);
    Assert.assertEquals(this.itemDao.createItem(this.a2), this.a2);
  }

  @Test
  public void testFindItemByName() {
    Assert.assertEquals(this.itemDao.findItemByName("shoes"), Arrays.asList(this.a1));
    Assert.assertEquals(this.itemDao.findItemByName("book"), Arrays.asList(this.a2));
  }

  @Test
  public void testFindItemByNameColorSize() {
    Assert.assertEquals(this.itemDao.findItemByNameColorSize("shoes", "green", 7),
        Arrays.asList(this.a1));
  }

  @Test
  public void testFindAllItem() {
    Assert.assertEquals(this.itemDao.findAllItem(), Arrays.asList(this.a1, this.a2));
  }

  @Test
  public void testFindItemByAvailability() {
    Assert.assertEquals(this.itemDao.findItemByAvailability(), Arrays.asList(this.a2));
  }

  @Test
  public void testUpdateStatus() {
    this.itemDao.updateStatus(true, new Long(2));
    Mockito.verify(this.itemDao).updateStatus(true, new Long(2));
  }
}
