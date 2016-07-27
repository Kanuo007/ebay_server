package resource;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.base.Optional;

import api.Register;
import core.User;
import db.UserDao;
import io.dropwizard.testing.junit.ResourceTestRule;

public class UserResourceTest {
  User user1 = new User("li", "aa", "li@gmail.com");
  User user2 = new User("bao", "bb", "bao@gmail.com");
  UserResource mockedUserResource;
  static UserDao mockedUserDao;
  Register register1;
  Register register2;
  Optional<User> empty = Optional.fromNullable(null);
  Optional<User> list1 = Optional.of(this.user1);
  @ClassRule
  public static ResourceTestRule resources = ResourceTestRule.builder()
      .addResource(new UserResource(UserResourceTest.mockedUserDao)).build();

  @Before
  public void setUp() throws Exception {
    this.mockedUserResource = Mockito.mock(UserResource.class);
    UserResourceTest.mockedUserDao = Mockito.mock(UserDao.class);
    this.register1 = new Register("li", "aa", "li@gmail.com", "Success");
    this.register2 = new Register("", "", "", "Failure : user name alrady exists");
    Mockito.when(UserResourceTest.mockedUserDao.findUserByName(this.user1.getUser_name()))
        .thenReturn(this.list1);
    Mockito.when(UserResourceTest.mockedUserDao.findUserByName(this.user2.getUser_name()))
        .thenReturn(this.empty);
  }

  @After
  public void tearDown() throws Exception {}


  @Test
  public void testRegister() {
    // Assert.assertEquals(this.mockedUserResource.register(this.user1), this.register1);
    Assert.assertEquals(this.mockedUserResource.register(this.user2), this.register2);
  }

}
