package com.betterebay.resource;

import com.betterebay.auth.UserAuthenticator;
import com.betterebay.core.Item;
import com.betterebay.core.User;
import com.betterebay.db.ItemDao;

import org.assertj.core.api.Assertions;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.testing.junit.ResourceTestRule;

public class ItemResourceTest {

  private static final ItemDao itemDao = Mockito.mock(ItemDao.class);
  private static final UserAuthenticator userAuthenticator = Mockito.mock(UserAuthenticator.class);
  @ClassRule
  public static final ResourceTestRule resources =
      ResourceTestRule.builder().setTestContainerFactory(new GrizzlyWebTestContainerFactory())
          .addProvider(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
              .setAuthenticator(ItemResourceTest.userAuthenticator).setRealm("Validate User")
              .setPrefix("Basic").buildAuthFilter()))
          .addProvider(RolesAllowedDynamicFeature.class)
          .addProvider(new AuthValueFactoryProvider.Binder<>(User.class))
          .addResource(new ItemResource(ItemResourceTest.itemDao)).build();

  private Item item1;
  private Item item2;
  private User user;

  @Before
  public void setUp() throws Exception {
    this.user = new User("AAA", "111", "123");

    SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.item1 = new Item(new Long(1), "first item", new Double(10.0), true,
        ft.parse("2016-11-21 23:00:00"), ft.parse("2016-11-30 23:00:00"), "shoe", new Integer(1),
        "green", new Integer(4), "This is the first shoe");
    this.item1.setId(new Long(1));
    this.item2 = new Item(new Long(2), "second item", new Double(10.0), false,
        ft.parse("2016-12-21 23:00:00"), ft.parse("2016-12-30 23:00:00"), "book", new Integer(1),
        "", new Integer(1), "This is the first book");
    this.item2.setId(new Long(2));
    Mockito
        .when(ItemResourceTest.userAuthenticator.authenticate(new BasicCredentials("AAA", "111")))
        .thenReturn(com.google.common.base.Optional.fromNullable(this.user));

    Mockito.when(ItemResourceTest.itemDao.findAllItem())
        .thenReturn(Arrays.asList(this.item1, this.item2));
    Mockito.when(ItemResourceTest.itemDao.findItemByID(new Long(1)))
        .thenReturn(Optional.ofNullable(this.item1));
    Mockito.when(ItemResourceTest.itemDao.findItemByID(new Long(2)))
        .thenReturn(Optional.ofNullable(this.item2));
    Mockito.when(ItemResourceTest.itemDao.findItemByName("first item"))
        .thenReturn(Arrays.asList(this.item1));
    Mockito.when(ItemResourceTest.itemDao.findItemByName("second item"))
        .thenReturn(Arrays.asList(this.item2));
    Mockito.when(ItemResourceTest.itemDao.findItemByAvailability())
        .thenReturn(Arrays.asList(this.item1));
    Mockito
        .when(
            ItemResourceTest.itemDao.findItemByNameColorSize("first item", "green", new Integer(1)))
        .thenReturn(Arrays.asList(this.item1));
    Mockito
        .when(ItemResourceTest.itemDao.findItemByNameColorSize("second item", "", new Integer(1)))
        .thenReturn(Arrays.asList(this.item2));
    Mockito.when(ItemResourceTest.itemDao.createItem(this.item1)).thenReturn(this.item1);
    Mockito.when(ItemResourceTest.itemDao.createItem(this.item2)).thenReturn(this.item2);
  }

  @After
  public void tearDown() throws Exception {
    Mockito.reset(ItemResourceTest.itemDao);
  }

  @Test
  public void searchAll() throws Exception {
    Assertions
        .assertThat(ItemResourceTest.resources.getJerseyTest().target("/item/search_all")
            .request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Item>>() {}))
        .isEqualTo(Arrays.asList(this.item1, this.item2));
    Mockito.verify(ItemResourceTest.itemDao).findAllItem();
  }

  @Test
  public void search() throws Exception {
    Assertions.assertThat(
        ItemResourceTest.resources.getJerseyTest().target("/item/search_item_name/first item")
            .request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Item>>() {}))
        .isEqualTo(Arrays.asList(this.item1));
    Mockito.verify(ItemResourceTest.itemDao).findItemByName("first item");
  }

  @Test
  public void findAllAvailableItems() throws Exception {
    Assertions
        .assertThat(ItemResourceTest.resources.getJerseyTest().target("/item/search_item_onBid")
            .request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Item>>() {}))
        .isEqualTo(Arrays.asList(this.item1));
    Mockito.verify(ItemResourceTest.itemDao).findItemByAvailability();
  }

  @Test
  public void findIDItems() throws Exception {
    Assertions
        .assertThat(ItemResourceTest.resources.getJerseyTest().target("/item/search_item_id/2")
            .request(MediaType.APPLICATION_JSON).get().readEntity(Item.class))
        .isEqualTo(this.item2);
    Mockito.verify(ItemResourceTest.itemDao).findItemByID(new Long(2));
  }

  @Test
  public void sell() throws Exception {
    Assertions
        .assertThat(ItemResourceTest.resources.getJerseyTest().target("/item/create_item")
            .request(MediaType.APPLICATION_JSON)
            .header(HttpHeaders.AUTHORIZATION, "Basic QUFBOjExMQ==")
            .post(Entity.entity(this.item1, MediaType.APPLICATION_JSON), Item.class))
        .isEqualTo(this.item1);
    Mockito.verify(ItemResourceTest.itemDao).createItem(this.item1);
  }

}
