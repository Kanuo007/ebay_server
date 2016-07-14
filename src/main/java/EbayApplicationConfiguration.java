import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.DatabaseConfiguration;
import io.dropwizard.metrics.graphite.GraphiteReporterFactory;
import jersey.repackaged.com.google.common.collect.ImmutableMap;

/**
 * Created by baoheng ling on 6/9/2016.
 */
public class EbayApplicationConfiguration extends Configuration{

    private static final Logger LOGGER = LoggerFactory.getLogger(EbayApplicationConfiguration.class);

    @NotEmpty
    private String template;

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @NotEmpty
    private String defaultName = "Guest";

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    @Valid
    @NotNull
    @JsonProperty("database")
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        LOGGER.info("Dropwizard dummy DB URL (will be overridden)=" + database.getUrl());
        if(System.getenv("DATABASE_URL") != null) {
            DatabaseConfiguration databaseConfiguration = BetterEbayDatabaseConfiguration.create(System.getenv("DATABASE_URL"));
            database = (DataSourceFactory) databaseConfiguration.getDataSourceFactory(null);
            LOGGER.info("Heroku DB URL=" + database.getUrl());
        }
        return database;
    }

    @NotNull
    private Map<String, Map<String, String>> viewRendererConfiguration = Collections.emptyMap();

    @Valid
    private GraphiteReporterFactory graphiteReporterFactory = new GraphiteReporterFactory();

    @JsonProperty("viewRendererConfiguration")
    public Map<String, Map<String, String>> getViewRendererConfiguration() {
        return viewRendererConfiguration;
    }

    @JsonProperty("viewRendererConfiguration")
    public void setViewRendererConfiguration(Map<String, Map<String, String>> viewRendererConfiguration) {
        ImmutableMap.Builder<String, Map<String, String>> builder = ImmutableMap.builder();
        for (Map.Entry<String, Map<String, String>> entry : viewRendererConfiguration.entrySet()) {
            builder.put(entry.getKey(), ImmutableMap.copyOf(entry.getValue()));
        }
        this.viewRendererConfiguration = builder.build();
    }

    @JsonProperty("metrics")
    public GraphiteReporterFactory getGraphiteReporterFactory() {
        return graphiteReporterFactory;
    }

    @JsonProperty("metrics")
    public void setGraphiteReporterFactory(GraphiteReporterFactory graphiteReporterFactory) {
        this.graphiteReporterFactory = graphiteReporterFactory;
    }
}
