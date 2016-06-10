package betterebayapplication;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;
import io.dropwizard.Configuration;

/**
 * Created by baoheng ling on 6/9/2016.
 */
public class EbayApplicationConfiguration extends Configuration{

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
}
