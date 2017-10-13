package cyan.svc.nazgul.example.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Entity Definition
 * Created by DreamInSun on 2016/7/21.
 */
public class User {

    @NotNull
    @JsonProperty
    public int id;

    @JsonProperty
    public String name;
}
