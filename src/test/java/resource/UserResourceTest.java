package resource;

import java.util.Arrays;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import core.User;
import db.UserDao;
import io.dropwizard.testing.junit.ResourceTestRule;

public class UserResourceTest {

  private User user1;
  private User user2;

  private static final UserDao userDao = Mockito.mock(UserDao.class);
  @ClassRule
  public static final ResourceTestRule Userresources =
      ResourceTestRule.builder().addResource(new UserResource(UserResourceTest.userDao)).build();

  @Before
  public void setUp() throws Exception {
    this.user1 = new User("John Snow", "winterfall", "johnsnow@gmail.com");
    this.user2 = new User("Lady Bella", "mountain", "ladybella@gmail.com");
    Mockito.when(UserResourceTest.userDao.findAllUser())
        .thenReturn(Arrays.asList(this.user1, this.user2));
    Mockito.when(UserResourceTest.userDao.findUserByEmail("johnsnow@gmail.com"))
        .thenReturn(Optional.ofNullable(this.user1));
    Mockito.when(UserResourceTest.userDao.findUserByID(new Long(1)))
        .thenReturn(Optional.ofNullable(this.user1));
    Mockito.when(UserResourceTest.userDao.findUserByName("Lady Bella"))
        .thenReturn(Optional.ofNullable(this.user2));
    Mockito.when(UserResourceTest.userDao.findUserByPassword("mountain"))
        .thenReturn(Optional.ofNullable(this.user2));
    Mockito.when(UserResourceTest.userDao.createUser(this.user1)).thenReturn(this.user1);
    Mockito.when(UserResourceTest.userDao.createUser(this.user1)).thenReturn(this.user2);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testLogin() {
    Boolean bool = UserResourceTest.userDao.UserNamePasswordMatch("Lady Bella", "mountain");
  }



  @Test
  public void testRegister() {}

}
