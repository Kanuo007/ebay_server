package com.betterebay.db;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.betterebay.core.Address;

public class AddressDaoTest {
  private AddressDao addressDao1;
  private Address add1;
  private Address add2;
  private Address add3;

  @Before
  public void setUp() throws Exception {
    this.addressDao1 = Mockito.mock(AddressDao.class);
    this.add1 = new Address("110 Baker St", "Seattle", "USA", new Long(98111), new Long(23));
    this.add2 = new Address("111 Baker St", "Seattle", "USA", new Long(98111), new Long(24));
    this.add3 = new Address("7 Smith St", "San Franscisco", "USA", new Long(99001), new Long(90));
    Mockito.when(this.addressDao1.findAll())
        .thenReturn(Arrays.asList(this.add1, this.add2, this.add3));
    Mockito.when(this.addressDao1.findAddressByUserId(0))
        .thenReturn(Arrays.asList(this.add1, this.add2));
    Mockito.when(this.addressDao1.findAddressByCity("Seattle"))
        .thenReturn(Arrays.asList(this.add1, this.add2));
    Mockito.when(this.addressDao1.findAddressByCountry("USA"))
        .thenReturn(Arrays.asList(this.add1, this.add2, this.add3));
    Mockito.when(this.addressDao1.findAddressByZipcode(98111))
        .thenReturn(Arrays.asList(this.add1, this.add2));
  }

  @After
  public void tearDown() throws Exception {}


  @Test
  public void testFindAll() {
    List<Address> addresses = this.addressDao1.findAll();
    Assert.assertEquals(addresses.size(), 3);
    Assert.assertEquals(addresses.get(0), this.add1);
    Assert.assertEquals(addresses.get(1), this.add2);
    Assert.assertEquals(addresses.get(2), this.add3);
  }

  @Test
  public void testFindAddressByUserId() {
    List<Address> addresses = this.addressDao1.findAddressByUserId(0);
    Assert.assertEquals(addresses.get(0), this.add1);
    Assert.assertEquals(addresses.get(1), this.add2);
    // Assert.assertEquals(addresses.get(0).getUser_id(), 0);
  }

  @Test
  public void testFindAddressByCity() {
    List<Address> addresses = this.addressDao1.findAddressByCity("Seattle");
    Assert.assertEquals(addresses.size(), 2);
    Assert.assertEquals(addresses.get(0), this.add1);
    Assert.assertEquals(addresses.get(1), this.add2);

  }

  @Test
  public void testFindAddressByCountry() {
    List<Address> addresses = this.addressDao1.findAddressByCountry("USA");
    Assert.assertEquals(addresses.size(), 3);
    Assert.assertEquals(addresses.get(0), this.add1);
    Assert.assertEquals(addresses.get(1), this.add2);
    Assert.assertEquals(addresses.get(2), this.add3);
  }

  @Test
  public void testFindAddressByZipcode() {
    List<Address> addresses = this.addressDao1.findAddressByZipcode(98111);
    Assert.assertEquals(addresses.size(), 2);
    Assert.assertEquals(addresses.get(0), this.add1);
    Assert.assertEquals(addresses.get(1), this.add2);
    Assert.assertFalse(addresses.get(1) == this.add3);
  }

}
