package com.example.com.starwarsretrofit.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Result",
    "Icon",
    "FirstURL",
    "Text"
})
public class RelatedTopic extends SugarRecord {

    @JsonProperty("Result")
    private String result;
    @JsonProperty("Icon")
    @Ignore
    private Icon icon;
    @JsonProperty("FirstURL")
    private String firstURL;
    @JsonProperty("Text")
    private String text;
    @JsonIgnore
    @Ignore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public RelatedTopic() {

    }

    public RelatedTopic(String result, String firstURL, String text) {
        this.result = result;
        this.firstURL = firstURL;
        this.text = text;
    }

    /**
     * 
     * @return
     *     The result
     */
    @JsonProperty("Result")
    public String getResult() {
        return result;
    }

    /**
     * 
     * @param result
     *     The Result
     */
    @JsonProperty("Result")
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 
     * @return
     *     The icon
     */
    @JsonProperty("Icon")
    public Icon getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The Icon
     */
    @JsonProperty("Icon")
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The firstURL
     */
    @JsonProperty("FirstURL")
    public String getFirstURL() {
        return firstURL;
    }

    /**
     * 
     * @param firstURL
     *     The FirstURL
     */
    @JsonProperty("FirstURL")
    public void setFirstURL(String firstURL) {
        this.firstURL = firstURL;
    }

    /**
     * 
     * @return
     *     The text
     */
    @JsonProperty("Text")
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The Text
     */
    @JsonProperty("Text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "RelatedTopic{" +
                "result='" + result + '\'' +
                ", icon=" + icon +
                ", firstURL='" + firstURL + '\'' +
                ", text='" + text + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
