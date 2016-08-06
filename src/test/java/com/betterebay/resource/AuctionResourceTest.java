
package com.betterebay.resource;

import com.betterebay.auth.UserAuthenticator;
import com.betterebay.core.BidHistory;
import com.betterebay.core.Item;
import com.betterebay.core.User;
import com.betterebay.db.BidHistoryDao;
import com.betterebay.db.FeedbackDao;
import com.betterebay.db.ItemDao;
import com.betterebay.db.NotificationDao;
import com.betterebay.db.TransactionDao;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.SimpleDateFormat;
import java.util.Optional;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.testing.junit.ResourceTestRule;

public class AuctionResourceTest {

  private static final ItemDao mockedItemDao = Mockito.mock(ItemDao.class);
  private static final BidHistoryDao mockedBidHistoryDao = Mockito.mock(BidHistoryDao.class);
  private static final FeedbackDao mockedFeedbackDao = Mockito.mock(FeedbackDao.class);
  private static final TransactionDao mockedTransactionDao = Mockito.mock(TransactionDao.class);
  private static final NotificationDao mockedNotificationDao = Mockito.mock(NotificationDao.class);

  private static final UserAuthenticator userAuthenticator = Mockito.mock(UserAuthenticator.class);
  @ClassRule
  public static final ResourceTestRule resources =
      ResourceTestRule.builder().setTestContainerFactory(new GrizzlyWebTestContainerFactory())
          .addProvider(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
              .setAuthenticator(AuctionResourceTest.userAuthenticator).setRealm("Validate User")
              .setPrefix("Basic").buildAuthFilter()))
          .addProvider(RolesAllowedDynamicFeature.class)
          .addProvider(new AuthValueFactoryProvider.Binder<>(User.class))
          .addResource(new AuctionResource(AuctionResourceTest.mockedNotificationDao,
              AuctionResourceTest.mockedBidHistoryDao, AuctionResourceTest.mockedTransactionDao,
              AuctionResourceTest.mockedItemDao, AuctionResourceTest.mockedFeedbackDao))
          .build();
  SimpleDateFormat ft;
  BidHistory bidHistory1;
  BidHistory bidHistory1Response;
  BidHistory bidHistory2;
  BidHistory bidHistory2Response;

  BidHistory bidHistory3;
  BidHistory bidHistory3Response;

  BidHistory bidHistory4;
  BidHistory bidHistory4Response;

  User user;
  Item item1;
  Item item2;
  Optional<Item> empty = Optional.empty();
  AuctionResource auctionResource;

  // @ClassRule
  // public static ResourceTestRule resources = ResourceTestRule.builder()
  // .addResource(new AuctionResource(AuctionResourceTest.mockedBidHistoryDao,
  // AuctionResourceTest.mockedTransactionDao,
  // AuctionResourceTest.mockedItemDao, AuctionResourceTest.mockedFeedbackDao))
  // .build();

  @Before
  public void setUp() throws Exception {
    this.user = new User("AAA", "111", "123");

    this.ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    this.auctionResource = Mockito.mock(AuctionResource.class);

    this.item1 = new Item(1570l, "Computer", 1000.0, true, this.ft.parse("2016-07-22 00:00:00"),
        this.ft.parse("2016-07-23 12:00:00"));// item1 has been bid for two times

    this.item2 = new Item(777l, "Desk", 50.0, false, this.ft.parse("2016-07-20 10:40:42"),
        this.ft.parse("2016-07-21 12:00:00"));// item2 hasn't been ready to bid

    // success
    this.bidHistory1 =
        new BidHistory(new Long(1), new Long(544), this.ft.parse("2016-07-22 10:40:42"), 1050.0);
    this.bidHistory1Response =
        new BidHistory(new Long(1), new Long(544), this.ft.parse("2016-07-22 10:40:42"), 1050.0);
    this.bidHistory1Response.setStatus("Succeed.");

    // The bidding price is lower than current
    this.bidHistory2 =
        new BidHistory(new Long(1), new Long(222), this.ft.parse("2016-07-22 11:00:0"), 1049.0);
    this.bidHistory2Response =
        new BidHistory(new Long(1), new Long(222), this.ft.parse("2016-07-22 11:00:0"), 1049.0);
    this.bidHistory2Response.setStatus("Failure: item not able to bid.");

    // item id doesn't exist
    this.bidHistory3 =
        new BidHistory(new Long(5), new Long(256), this.ft.parse("2016-07-22 11:00:0"), 50.0);
    this.bidHistory3Response =
        new BidHistory(new Long(5), new Long(256), this.ft.parse("2016-07-22 11:00:0"), 50.0);
    this.bidHistory3Response.setStatus("Failure: item does not exist.");

    // item is not able to bid
    this.bidHistory4 =
        new BidHistory(new Long(2), new Long(777), this.ft.parse("2016-07-22 11:00:0"), 50.0);
    this.bidHistory4Response =
        new BidHistory(new Long(2), new Long(777), this.ft.parse("2016-07-22 11:00:0"), 50.0);
    this.bidHistory4Response.setStatus("Failure: item not able to bid.");

    Mockito
        .when(
            AuctionResourceTest.userAuthenticator.authenticate(new BasicCredentials("AAA", "111")))
        .thenReturn(com.google.common.base.Optional.fromNullable(this.user));
    Mockito.when(AuctionResourceTest.mockedBidHistoryDao.findByHighestPriceByItemId(1l))
        .thenReturn(Optional.ofNullable(this.bidHistory2));

    Mockito.when(AuctionResourceTest.mockedItemDao.findItemByID(1l))
        .thenReturn(Optional.of(this.item1));
    Mockito.when(AuctionResourceTest.mockedItemDao.findItemByID(2l))
        .thenReturn(Optional.of(this.item2));
    Mockito.when(AuctionResourceTest.mockedItemDao.findItemByID(5l)).thenReturn(this.empty);

    Mockito.when(this.auctionResource.bid(this.user, this.bidHistory1))
        .thenReturn(this.bidHistory1Response);
    Mockito.when(this.auctionResource.bid(this.user, this.bidHistory2))
        .thenReturn(this.bidHistory2Response);
    Mockito.when(this.auctionResource.bid(this.user, this.bidHistory3))
        .thenReturn(this.bidHistory3Response);
    Mockito.when(this.auctionResource.bid(this.user, this.bidHistory4))
        .thenReturn(this.bidHistory4Response);
    Mockito.when(AuctionResourceTest.mockedBidHistoryDao.createBidHistory(this.bidHistory1))
        .thenReturn(this.bidHistory1);
  }

  @After
  public void tearDown() throws Exception {}



  @Test
  public void testBid() {
    Assert.assertEquals(this.auctionResource.bid(this.user, this.bidHistory1),
        this.bidHistory1Response);

    Assert.assertEquals(this.auctionResource.bid(this.user, this.bidHistory2),
        this.bidHistory2Response);

    Assert.assertEquals(this.auctionResource.bid(this.user, this.bidHistory3),
        this.bidHistory3Response);

    Assert.assertEquals(this.auctionResource.bid(this.user, this.bidHistory4),
        this.bidHistory4Response);
    Mockito.verify(this.auctionResource).bid(this.user, this.bidHistory1);
    Mockito.verify(this.auctionResource).bid(this.user, this.bidHistory2);
    Mockito.verify(this.auctionResource).bid(this.user, this.bidHistory3);
    Mockito.verify(this.auctionResource).bid(this.user, this.bidHistory4);

    Assert.assertEquals(AuctionResourceTest.resources.getJerseyTest().target("/auction/bid")
        .request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Basic QUFBOjExMQ==")
        .post(Entity.entity(this.bidHistory1, MediaType.APPLICATION_JSON), BidHistory.class),
        this.bidHistory1Response);


    // Assertions
    // .assertThat(AuctionResourceTest.resources.client().target("/auction/bid")
    // .request(MediaType.APPLICATION_JSON).post(
    // Entity.entity(this.bidHistory1, MediaType.APPLICATION_JSON), BidHistory.class))
    // .isEqualTo(this.bidHistory1Response);
    // Mockito.verify(AuctionResourceTest.mockedBidHistoryDao).createBidHistory(this.bidHistory1);
  }
}
