package resource;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import core.User;
import db.UserDao;
import io.dropwizard.testing.junit.ResourceTestRule;

public class UserResourceTest {


  // User user1 = new User("li", "aa", "li@gmail.com");
  // User user2 = new User("bao", "bb", "bao@gmail.com");
  // UserResource mockedUserResource;
  // static UserDao mockedUserDao;
  // Register register1;
  // Register register2;
  // Optional<User> empty = Optional.fromNullable(null);
  // Optional<User> list1 = Optional.of(this.user2);

  private static final UserDao userDao = Mockito.mock(UserDao.class);


  @ClassRule
  public static final ResourceTestRule RULE =
      ResourceTestRule.builder().addResource(new UserResource(UserResourceTest.userDao)).build();

  private User user1;
  private User user2;
  private UserResource userResource;

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

    Mockito.when(UserResourceTest.userDao.UserNamePasswordMatch("Lady Bella", "mountain"))
        .thenReturn(true);
    // Mockito.when(UserResourceTest.userDao.findUserByName(this.user2.getUser_name()))
    // .thenReturn(this.list2);
    // this.userResource = new UserResource(UserResourceTest.mockedUserDao);
    // this.register1 = new Register("John Snow", "johnsnow@gmail.com", "winterfall", "Success");
    // this.register2 = new Register("", "", "", "Failure : user name alrady exists");

  }

  @After
  public void tearDown() throws Exception {}

  @Test

  public void testLogin() {
    Boolean bool = UserResourceTest.userDao.UserNamePasswordMatch("Lady Bella", "mountain");

  }

  @Test(expected = Exception.class)
  public void testGetUser() {
    Mockito.when(UserResourceTest.userDao.findUserByID(new Long(1)))
        .thenReturn(Optional.ofNullable(this.user1));
    User found_user =
        UserResourceTest.RULE.getJerseyTest().target("/user/1").request().get(User.class);

    Assertions.assertThat(found_user.getId()).isEqualTo(this.user1.getId());
    Mockito.verify(UserResourceTest.userDao).findUserByID(new Long(1));
  }

  // @Test
  // public void testRegister() {
  // Assert.assertEquals(this.userResource.register(this.user1), this.register1);
  // Assert.assertEquals(this.userResource.register(this.user2), this.register2);
  // System.out.println(UserResourceTest.resources.client().target("/user/register")
  // .request(MediaType.APPLICATION_JSON)
  // .post(Entity.entity(this.user2, MediaType.APPLICATION_JSON), User.class).toString());
  //
  // System.out.println(this.user2.toString());

  @Test
  public void testRegister() {
    // Assert.assertEquals(this.userResource.register(this.user1), this.register1);
    // Assert.assertEquals(this.userResource.register(this.user2), this.register2);
    // Assert.assertEquals(
    // UserResourceTest.resources.getJerseyTest().target("/user/register")
    // .request(MediaType.APPLICATION_JSON)
    // .post(Entity.entity(this.user1, MediaType.APPLICATION_JSON), Register.class),
    // this.register1);
    //
    //
    // Mockito.verify(UserResourceTest.mockedUserDao, Mockito.times(2)).createUser(this.user1);

    // Assert.assertEquals(UserResourceTest.resources.client().target("/user/register")
    // .request(MediaType.APPLICATION_JSON)
    // .post(Entity.entity(this.user1, MediaType.APPLICATION_JSON), User.class), this.user1);
    //
    // Mockito.verify(UserResourceTest.mockedUserDao).createUser(this.user1);

  }
}


