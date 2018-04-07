package cyan.svc.nazgulexample.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import cyan.nazgul.dropwizard.config.BaseSvcConfig;

/**
 * Created by DreamInSun on 2017/11/24.
 */
public class SvcConfig extends BaseSvcConfig {

    /*========== Properties ==========*/
    /**
     * 用户中心的域
     */
    @JsonProperty("ucenter")
    protected String ucenter;
    /**
     * 微信接口的域
     */
    @JsonProperty("weixin")
    protected String weixin;
    /**
     * 消息中心的域
     */
    @JsonProperty("mcenter")
    protected String mcenter;

    /*========== Getter & Setter ==========*/

    public String getMcenter() {
        return mcenter;
    }

    public void setMcenter(String mcenter) {
        this.mcenter = mcenter;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getUcenter() {
        return ucenter;
    }

    public void setUcenter(String ucenter) {
        this.ucenter = ucenter;
    }
}
