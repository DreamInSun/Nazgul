package cyan.nazgul.dropwizard.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.UnsupportedEncodingException;

/**
 * Created by DreamInSun on 2018/3/27.
 */
public class AuthConfig {

    /*========== JWT Secret ===========*/
    @JsonProperty
    private String jwtTokenSecret;

    /*========== Getter & Setter ===========*/
    public String getJwtTokenSecret() {
        return jwtTokenSecret;
    }

    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    /*========== Assistant Function ===========*/
    public byte[] convertJwtTokenSecret() {
        byte[] retByteBuffy = null;
        try {
            retByteBuffy =  jwtTokenSecret.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return retByteBuffy;
    }
}
