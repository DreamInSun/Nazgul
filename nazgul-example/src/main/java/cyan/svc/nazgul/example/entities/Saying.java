package cyan.svc.nazgul.example.entities;

/**
 * An Example Entity
 * Created by DreamInSun on 2016/6/30.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.Length;

@ApiModel(value = "A Saying is a representation of greeting")
public class Saying {

    /*========== Properties ==========*/
    private long id;

    @Length(max = 3)
    private String content;

    /*========== Getter & Setter ==========*/
    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }

    /*========== Constructor ==========*/
    public Saying() {

    }

    public Saying(long id, String content) {
        this.id = id;
        this.content = content;
    }


}