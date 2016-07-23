package com.orange.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.nazgul.dropwizard.DbConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by DreamInSun on 2016/6/30.
 */
public class NazgulConfiguration extends DbConfiguration<NazgulConfiguration> {

    /*========== Properties ==========*/
    @NotEmpty
    @JsonProperty("template")
    private String template;

    @NotEmpty
    @JsonProperty("defaultName")
    private String defaultName = "Friend";

    @JsonProperty("template")
    public String getTemplate() {
        return template;
    }

    @JsonProperty("template")
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty("defaultName")
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty("defaultName")
    public void setDefaultName(String name) {
        this.defaultName = name;
    }


}