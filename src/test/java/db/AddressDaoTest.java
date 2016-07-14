package db;

import java.util.ArrayList;

import org.hibernate.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.Address;

public class AddressDaoTest extends DAOTests {
  @Before
  public void initialize() {

    AddressDao addressDao1 = new AddressDao(this.sessionFactory);
    getSession().beginTransaction();

    Query query1 = getSession().createQuery("Delete from address");
    query1.executeUpdate();
  }


  @Test
  public void filtersTodos() throws Exception {
    getSession().beginTransaction();

    Address address1 = new Address("222 Baker Street", "Seattle", "USA", 98100, 1);
    address1.setId(1);
    AddressDao addressDao1 = new AddressDao(this.sessionFactory);
    addressDao1.findAddressByCity("Seattle");

    ArrayList<Address> loa = new ArrayList<>();
    loa.add(address1);
    Assert.assertEquals(addressDao1.findAddressByCity("Seattle"), loa);
    getSession().getTransaction().commit();
  }

}
