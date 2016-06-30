package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Welcome {


    private String content;

    public Welcome(String content){
        this.content = content;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    @JsonProperty
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Welcome{" +
                "content='" + content + '\'' +
                '}';
    }
}

