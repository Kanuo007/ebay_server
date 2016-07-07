import core.Item;
import core.User;
import db.ItemDao;
import db.UserDao;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.AuctionResource;
import resource.FeedbackResource;
import resource.HomepageResource;
import resource.LoginResource;
import resource.RegisterResource;

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
      new HibernateBundle<EbayApplicationConfiguration>(User.class, Item.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(EbayApplicationConfiguration configuration) {
          return configuration.getDataSourceFactory();
        }
      };

  @Override
  public void initialize(Bootstrap<EbayApplicationConfiguration> bootstrap) {
    bootstrap.addBundle(this.migrations);
    bootstrap.addBundle(this.hibernateBundle);
  }

  @Override
  public void run(EbayApplicationConfiguration configuration, Environment environment) {
    UserDao userDao = new UserDao(this.hibernateBundle.getSessionFactory());
    ItemDao itemDao = new ItemDao(this.hibernateBundle.getSessionFactory());
    environment.jersey().register(new HomepageResource());
    environment.jersey().register(new LoginResource());
    // environment.jersey().register(new SearchResource());
    environment.jersey().register(new RegisterResource(userDao));
    environment.jersey().register(new FeedbackResource());
    environment.jersey().register(new AuctionResource(itemDao));
  }
}
