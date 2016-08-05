import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import auth.UserAuthenticator;
import auth.UserAuthorizer;
import core.Address;
import core.BidHistory;
import core.CreditCard;
import core.Feedback;
import core.Item;
import core.Notification;
import core.Transaction;
import core.Update;
import core.User;
import db.BidHistoryDao;
import db.FeedbackDao;
import db.ItemDao;
import db.NotificationDao;
import db.TransactionDao;
import db.UserDao;
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
import resource.AuctionResource;
import resource.HomepageResource;
import resource.ItemResource;
import resource.UserResource;

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
    // use @Auth to inject a custom Principal type into your resource
    environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

    environment.jersey().register(new HomepageResource());
    environment.jersey().register(new UserResource(userDao));
    environment.jersey().register(new ItemResource(itemDao));
    environment.jersey().register(
        new AuctionResource(notificationDao, bidHistoryDao, transactionDao, itemDao, feedbackDao));
  }

}
