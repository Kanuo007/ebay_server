package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Welcome {

    @JsonProperty
    private String content;

    public Welcome(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

}

