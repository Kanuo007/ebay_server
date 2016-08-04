package resource;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

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
  UserResource userResource;
  static UserDao mockedUserDao = Mockito.mock(UserDao.class);
  Register register1;
  Register register2;
  Optional<User> list1 = Optional.fromNullable(null);
  Optional<User> list2 = Optional.fromNullable(this.user2);


  @ClassRule
  public static ResourceTestRule resources = ResourceTestRule.builder()
      .addResource(new UserResource(UserResourceTest.mockedUserDao)).build();


  @Before
  public void setUp() throws Exception {
    this.user1 = new User("John Snow", "winterfall", "johnsnow@gmail.com");
    this.user2 = new User("Lady Bella", "mountain", "ladybella@gmail.com");
    // Mockito.when(UserResourceTest.userDao.findAllUser())
    // .thenReturn(Arrays.asList(this.user1, this.user2));
    // Mockito.when(UserResourceTest.userDao.findUserByEmail("johnsnow@gmail.com"))
    // .thenReturn(Optional.fromNullable(this.user1));
    // Mockito.when(UserResourceTest.userDao.findUserByID(new Long(1)))
    // .thenReturn(Optional.fromNullable(this.user1));
    // Mockito.when(UserResourceTest.userDao.findUserByName("Lady Bella"))
    // .thenReturn(Optional.fromNullable(this.user2));
    // Mockito.when(UserResourceTest.userDao.findUserByPassword("mountain"))
    // .thenReturn(Optional.fromNullable(this.user2));
    // Mockito.when(UserResourceTest.userDao.createUser(this.user1)).thenReturn(this.user1);
    // Mockito.when(UserResourceTest.userDao.createUser(this.user1)).thenReturn(this.user2);

    Mockito.when(UserResourceTest.mockedUserDao.findUserByName(this.user1.getUser_name()))
        .thenReturn(this.list1);
    Mockito.when(UserResourceTest.mockedUserDao.findUserByName(this.user2.getUser_name()))
        .thenReturn(this.list2);
    this.userResource = new UserResource(UserResourceTest.mockedUserDao);
    this.register1 = new Register("John Snow", "johnsnow@gmail.com", "winterfall", "Success");
    this.register2 = new Register("", "", "", "Failure : user name alrady exists");

  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testLogin() {
    // Boolean bool = UserResourceTest.userDao.UserNamePasswordMatch("Lady Bella", "mountain");
  }

  // @Test
  // public void testLogin() {
  // Assertions.assertThat(UserResourceTest.resources.client().target("/user/login-in"))
  // }


  @Test
  public void testRegister() {
    Assert.assertEquals(this.userResource.register(this.user1), this.register1);
    Assert.assertEquals(this.userResource.register(this.user2), this.register2);
    Assert.assertEquals(
        UserResourceTest.resources.getJerseyTest().target("/user/register")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(this.user1, MediaType.APPLICATION_JSON), Register.class),
        this.register1);

    Mockito.verify(UserResourceTest.mockedUserDao, Mockito.times(2)).createUser(this.user1);


  }



}
