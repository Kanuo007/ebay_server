import core.User;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resource.FeedbackResource;
import resource.HomepageResource;
import resource.LoginResource;
import resource.RegisterResource;
import resource.SearchResource;

/**
 * Created by baoheng ling on 6/9/2016.
 */
public class EbayApplication extends  Application<EbayApplicationConfiguration>{

    public static void main(String[] args) throws Exception{
        new EbayApplication().run(args);
    }

    private final MigrationsBundle<EbayApplicationConfiguration> migrations = new MigrationsBundle<EbayApplicationConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(EbayApplicationConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };
    private final HibernateBundle<EbayApplicationConfiguration> hibernateBundle = new HibernateBundle<EbayApplicationConfiguration>(User.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(EbayApplicationConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<EbayApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(migrations);
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(EbayApplicationConfiguration configuration, Environment environment) {
        environment.jersey().register(new HomepageResource());
        environment.jersey().register(new LoginResource());
        environment.jersey().register(new SearchResource());
        environment.jersey().register(new RegisterResource());
        environment.jersey().register(new FeedbackResource());
    }
}
