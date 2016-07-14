package db;

import org.hibernate.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import core.User;



public class UserDaoGTest extends DAOTests {
  @Before
  public void initialize() {

    UserDao userDao1 = new UserDao(this.sessionFactory);
    getSession().beginTransaction();

    Query query1 = getSession().createQuery("Delete from user");
    query1.executeUpdate();
  }


  @Test
  public void filtersTodos() throws Exception {
    getSession().beginTransaction();

    User user1 = new User("John Snow", "Winterfall", "johnsnow@gmail.com");
    user1.setId(1);
    UserDao userDao1 = new UserDao(this.sessionFactory);
    userDao1.createUser(user1);

    Assert.assertEquals(userDao1.findUserByID((long) 1), user1);
    getSession().getTransaction().commit();
  }

}
