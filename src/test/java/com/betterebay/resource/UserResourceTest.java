package com.betterebay.resource;

import com.betterebay.api.Register;
import com.betterebay.auth.UserAuthenticator;
import com.betterebay.core.User;
import com.betterebay.db.UserDao;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.testing.junit.ResourceTestRule;

import static org.mockito.Mockito.reset;

public class UserResourceTest {

    private static final UserDao userDao = Mockito.mock(UserDao.class);
    private static final UserAuthenticator userAuthenticator = Mockito.mock(UserAuthenticator.class);
    @ClassRule
    public static final ResourceTestRule resources =
            ResourceTestRule.builder().setTestContainerFactory(new GrizzlyWebTestContainerFactory())
                    .addProvider(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                            .setAuthenticator(UserResourceTest.userAuthenticator).setRealm("Validate User")
                            .setPrefix("Basic").buildAuthFilter()))
                    .addProvider(RolesAllowedDynamicFeature.class)
                    .addProvider(new AuthValueFactoryProvider.Binder<>(User.class))
                    .addResource(new UserResource(UserResourceTest.userDao)).build();


    private User user1;
    private User user2;
    private Register register1;
    private Register register2;
    private User noUser;
    private User noPassword;

    @Before
    public void setUp() throws Exception {
        this.user1 = new User("John Snow", "winterfall", "johnsnow@gmail.com");
        this.user2 = new User("Lady Bella", "mountain", "ladybella@gmail.com");
        this.noUser = new User("AA", "Incorrect User", "Incorrect User");
        this.noPassword = new User("AAA", "Incorrect Password", "Incorrect Password");

        Mockito.when(UserResourceTest.userAuthenticator.authenticate(new BasicCredentials("AAA", "111")))
                .thenReturn(com.google.common.base.Optional.fromNullable(this.user2));
        Mockito.when(UserResourceTest.userAuthenticator.authenticate(new BasicCredentials("AA", "111")))
                .thenReturn(com.google.common.base.Optional.fromNullable(this.noUser));
        Mockito.when(UserResourceTest.userAuthenticator.authenticate(new BasicCredentials("AAA", "11")))
                .thenReturn(com.google.common.base.Optional.fromNullable(this.noPassword));

        Mockito.when(UserResourceTest.userDao.findAllUser())
                .thenReturn(Arrays.asList(this.user1, this.user2));
        Mockito.when(UserResourceTest.userDao.findUserByEmail("johnsnow@gmail.com"))
                .thenReturn(Optional.ofNullable(this.user1));
        Mockito.when(UserResourceTest.userDao.findUserByID(new Long(1)))
                .thenReturn(Optional.ofNullable(this.user1));
        Mockito.when(UserResourceTest.userDao.findUserByName("John Snow"))
                .thenReturn(Optional.empty());
        Mockito.when(UserResourceTest.userDao.findUserByName("Lady Bella"))
                .thenReturn(Optional.ofNullable(this.user2));
        Mockito.when(UserResourceTest.userDao.findUserByName("AA"))
                .thenReturn(Optional.empty());
        Mockito.when(UserResourceTest.userDao.findUserByPassword("mountain"))
                .thenReturn(Optional.ofNullable(this.user2));
        Mockito.when(UserResourceTest.userDao.createUser(this.user1)).thenReturn(this.user1);
        Mockito.when(UserResourceTest.userDao.createUser(this.user1)).thenReturn(this.user2);

        Mockito.when(UserResourceTest.userDao.UserNamePasswordMatch("Lady Bella", "mountain"))
                .thenReturn(true);
        this.register1 = new Register("John Snow", "johnsnow@gmail.com", "winterfall", "Success");
        this.register2 = new Register("", "", "", "Failure : user name already exists");

    }

    @After
    public void tearDown() throws Exception {
        reset(userDao);
    }

    @Test
    public void testLogin() {
        WebTarget webTarget = UserResourceTest.resources.getJerseyTest().target("/user/log_in");
        Assert.assertEquals(
                webTarget.request(MediaType.TEXT_PLAIN)
                        .header(HttpHeaders.AUTHORIZATION, "Basic QUE6MTEx")
                        .post(Entity.entity(this.noUser, MediaType.APPLICATION_JSON), Response.class).readEntity(String.class),
                "User: AA doesn't exist.");
        Assert.assertEquals(
                webTarget.request(MediaType.TEXT_PLAIN)
                        .header(HttpHeaders.AUTHORIZATION, "Basic QUFBOjEx")
                        .post(Entity.entity(this.noPassword, MediaType.APPLICATION_JSON), Response.class).readEntity(String.class),
                "Password doesn't match with User: AAA");
        Assert.assertEquals(
                webTarget.request(MediaType.TEXT_PLAIN)
                        .header(HttpHeaders.AUTHORIZATION, "Basic QUFBOjExMQ==")
                        .post(Entity.entity(this.user2, MediaType.APPLICATION_JSON), Response.class).readEntity(String.class),
                "Login Successfully! Welcome Lady Bella with id null");

    }

    @Test
    public void testRegister() {
        WebTarget webTarget = UserResourceTest.resources.getJerseyTest().target("/user/register");
        Assert.assertEquals(
                webTarget.request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(this.user1, MediaType.APPLICATION_JSON), Register.class),
                this.register1);
        Assert.assertEquals(
                webTarget.request(MediaType.APPLICATION_JSON)
                        .post(Entity.entity(this.user2, MediaType.APPLICATION_JSON), Register.class),
                this.register2);
    }
}


