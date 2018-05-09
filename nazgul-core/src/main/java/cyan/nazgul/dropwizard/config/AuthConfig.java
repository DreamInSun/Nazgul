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

    @JsonProperty
    private String jwtRealm;

    @JsonProperty
    private String jwtPreFix = "Bearer";

    /*========== Getter & Setter ===========*/
    public String getJwtTokenSecret() {
        return jwtTokenSecret;
    }

    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    public String getJwtRealm() {
        return jwtRealm;
    }

    public void setJwtRealm(String jwtRealm) {
        this.jwtRealm = jwtRealm;
    }

    public String getJwtPreFix() {
        return jwtPreFix;
    }

    public void setJwtPreFix(String jwtPreFix) {
        this.jwtPreFix = jwtPreFix;
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
