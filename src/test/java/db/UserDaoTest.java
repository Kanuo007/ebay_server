package db;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import core.User;

public class UserDaoTest {
  private UserDao userDao1;
  private User user1;
  private User user2;


  @Before
  public void setUp() throws Exception {
    this.userDao1 = Mockito.mock(UserDao.class);
    this.user1 = new User("John Snow", "winterfall", "johnsnow@gmail.com");
    this.user2 = new User("Lady Bella", "mountain", "ladybella@gmail.com");
    Mockito.when(this.userDao1.findAllUser()).thenReturn(Arrays.asList(this.user1, this.user2));
    Mockito.when(this.userDao1.findUserByEmail("johnsnow@gmail.com"))
        .thenReturn(Optional.ofNullable(this.user1));
    Mockito.when(this.userDao1.findUserByID(new Long(1)))
        .thenReturn(Optional.ofNullable(this.user1));
    Mockito.when(this.userDao1.findUserByName("Lady Bella"))
        .thenReturn(Optional.ofNullable(this.user2));
    Mockito.when(this.userDao1.findUserByPassword("mountain"))
        .thenReturn(Optional.ofNullable(this.user2));
    Mockito.when(this.userDao1.createUser(this.user1)).thenReturn(this.user1);
    Mockito.when(this.userDao1.createUser(this.user2)).thenReturn(this.user2);
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testFindUserByID() {
    Optional<User> user = this.userDao1.findUserByID(new Long(1));
    Assert.assertEquals(user, Optional.ofNullable(this.user1));
    Assert.assertEquals(user.get().getName(), "John Snow");
  }

  @Test
  public void testFindUserByName() {
    Optional<User> user = this.userDao1.findUserByName("Lady Bella");
    Assert.assertEquals(user, Optional.ofNullable(this.user2));
  }

  @Test
  public void testFindUserByEmail() {
    Optional<User> user = this.userDao1.findUserByEmail("johnsnow@gmail.com");
    Assert.assertEquals(user, Optional.ofNullable(this.user1));
  }

  @Test
  public void testFindUserByPassword() {
    Optional<User> user = this.userDao1.findUserByPassword("mountain");
    Assert.assertEquals(user, Optional.ofNullable(this.user2));
  }

  @Test
  public void testCreateUser() {
    // Assert.assertEquals(this.userDao1.createUser(this.user1), this.user1);
    Assert.assertEquals(this.userDao1.createUser(this.user2), this.user2);
  }

  @Test
  public void testFindAllUser() {
    List<User> users = this.userDao1.findAllUser();
    Assert.assertEquals(users.size(), 2);
  }

  @Test
  public void testUserNamePasswordMatch() {
    Optional<User> user = this.userDao1.findUserByName("Lady Bella");
    // Assert.assertEquals(this.userDao1.UserNamePasswordMatch("Lady Bella", "mountain"),
    // user.get().getUser_password().equals("mountain"));
  }

}
