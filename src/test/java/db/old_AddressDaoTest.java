// package db;
//
// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;
//
// import core.Address;
//
// public class old_AddressDaoTest extends DAOTests {
// AddressDao addressDao1;
// Address add1;
// Address add2;
//
// @Before
// public void setUp() throws Exception {
// this.addressDao1 = mock(AddressDao.class);
// this.add1 = new Address("110 Baker St", "Seattle", "USA", 98111, 23);
// this.add2 = new Address("110 Baker St", "Seattle", "USA", 98111, 23);
// }
//
// @After
// public void tearDown() throws Exception {
//
// }
//
// @Test
// public void testEqual()
// {
//
// }
// }
//// public void initialize() {
////
//// AddressDao addressDao1 = new AddressDao(this.sessionFactory);
//// getSession().beginTransaction();
////
//// Query query1 = getSession().createQuery("Delete from address");
//// query1.executeUpdate();
//// getSession().getTransaction().commit();
//// }
////
////
//// @Test
//// public void filtersTodos() throws Exception {
//// getSession().beginTransaction();
////
//// Address address1 = new Address("222 Baker Street", "Seattle", "USA", 98100, 1);
//// address1.setId(1);
//// this.addressDao1.findAddressByCity("Seattle");
////
//// ArrayList<Address> loa = new ArrayList<>();
//// loa.add(address1);
//// // Assert.assertEquals(addressDao1.findAddressByCity("Seattle"), loa);
//// getSession().getTransaction().commit();
//// }
//
// }
