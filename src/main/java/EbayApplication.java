import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import auth.UserAuthenticator;
import auth.UserAuthorizer;
import core.Address;
import core.BidHistory;
import core.CreditCard;
import core.Feedback;
import core.Item;
import core.Transaction;
import core.User;
import db.BidHistoryDAO;
import db.FeedbackDao;
import db.ItemDao;
import db.UserDao;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.AuctionResource;
import resource.BidResource;
import resource.FeedbackResource;
import resource.HomepageResource;
import resource.LoginResource;
import resource.RegisterResource;
import resource.SearchResource;
import resource.ListResource;

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
                    CreditCard.class, Address.class, BidHistory.class, Transaction.class) {
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
        FeedbackDao feedbackDao = new FeedbackDao(this.hibernateBundle.getSessionFactory());
        BidHistoryDAO bidHistoryDao = new BidHistoryDAO(this.hibernateBundle.getSessionFactory());

        CachingAuthenticator<BasicCredentials, User> cachingAuthenticator = new CachingAuthenticator<>(
                environment.metrics(), new UserAuthenticator(userDao),
                configuration.getAuthenticationCachePolicy());
        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<User>()
                        .setAuthenticator(new UserAuthenticator(userDao))
                        .setAuthorizer(new UserAuthorizer())
                        .setRealm("Validate User")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        // use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));


        environment.jersey().register(new HomepageResource());
        environment.jersey().register(new LoginResource(userDao));
        environment.jersey().register(new SearchResource(itemDao));
        environment.jersey().register(new RegisterResource(userDao));
        environment.jersey().register(new FeedbackResource(feedbackDao));
        environment.jersey().register(new AuctionResource(itemDao));
        environment.jersey().register(new ListResource(itemDao));
        environment.jersey().register(new BidResource(bidHistoryDao, itemDao));
    }
}
