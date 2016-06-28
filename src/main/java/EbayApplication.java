import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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

    @Override
    public void initialize(Bootstrap<EbayApplicationConfiguration> bootstrap) {
        bootstrap.addBundle(migrations);
    }

    @Override
    public void run(EbayApplicationConfiguration configuration, Environment environment) {

    }
}
