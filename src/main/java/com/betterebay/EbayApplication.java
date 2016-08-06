package com.betterebay;

import com.betterebay.auth.UserAuthenticator;
import com.betterebay.auth.UserAuthorizer;
import com.betterebay.core.Address;
import com.betterebay.core.BidHistory;
import com.betterebay.core.CreditCard;
import com.betterebay.core.Feedback;
import com.betterebay.core.Item;
import com.betterebay.core.Notification;
import com.betterebay.core.Transaction;
import com.betterebay.core.Update;
import com.betterebay.core.User;
import com.betterebay.db.BidHistoryDao;
import com.betterebay.db.FeedbackDao;
import com.betterebay.db.ItemDao;
import com.betterebay.db.NotificationDao;
import com.betterebay.db.TransactionDao;
import com.betterebay.db.UserDao;
import com.betterebay.resource.AuctionResource;
import com.betterebay.resource.ItemResource;
import com.betterebay.resource.UserResource;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 * Created by baoheng ling on 6/9/2016.
 */
public class EbayApplication extends Application<EbayApplicationConfiguration> {

  public static void main(String[] args) throws Exception {
    new EbayApplication().run(args);
  }


  private final MigrationsBundle<EbayApplicationConfiguration> migrations =
      new MigrationsBundle<EbayApplicationConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(EbayApplicationConfiguration configuration) {
          return configuration.getDataSourceFactory();
        }
      };
  private final HibernateBundle<EbayApplicationConfiguration> hibernateBundle =
      new HibernateBundle<EbayApplicationConfiguration>(User.class, Item.class, Feedback.class,
          CreditCard.class, Address.class, BidHistory.class, Transaction.class,
          Notification.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(EbayApplicationConfiguration configuration) {
          return configuration.getDataSourceFactory();
        }
      };

  @Override
  public void initialize(Bootstrap<EbayApplicationConfiguration> bootstrap) {
    bootstrap.addBundle(this.migrations);
    bootstrap.addBundle(this.hibernateBundle);
    bootstrap.addBundle(new SwaggerBundle<EbayApplicationConfiguration>() {
      @Override
      protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
          EbayApplicationConfiguration configuration) {
        return configuration.swaggerBundleConfiguration;
      }
    });

  }

  @Override
  public void run(EbayApplicationConfiguration configuration, Environment environment) {
    UserDao userDao = new UserDao(this.hibernateBundle.getSessionFactory());
    ItemDao itemDao = new ItemDao(this.hibernateBundle.getSessionFactory());
    FeedbackDao feedbackDao = new FeedbackDao(this.hibernateBundle.getSessionFactory());
    BidHistoryDao bidHistoryDao = new BidHistoryDao(this.hibernateBundle.getSessionFactory());
    NotificationDao notificationDao = new NotificationDao(this.hibernateBundle.getSessionFactory());
    TransactionDao transactionDao = new TransactionDao(this.hibernateBundle.getSessionFactory());

    Update update = new Update(notificationDao, transactionDao, bidHistoryDao, itemDao,
        this.hibernateBundle.getSessionFactory());
    update.updateEverything();

    environment.jersey()
        .register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
            .setAuthenticator(
                new UserAuthenticator(userDao, this.hibernateBundle.getSessionFactory()))
            .setAuthorizer(new UserAuthorizer()).setRealm("Validate User").buildAuthFilter()));
    environment.jersey().register(RolesAllowedDynamicFeature.class);
    // use @Auth to inject a custom Principal type into your com.betterebay.resource
    environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

    environment.jersey().register(new UserResource(userDao));
    environment.jersey().register(new ItemResource(itemDao));
    environment.jersey().register(
        new AuctionResource(notificationDao, bidHistoryDao, transactionDao, itemDao, feedbackDao));
  }

}
