package cyan.util.email;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;

/**
 * Created by DreamInSun on 2017/9/14.
 */
public class EmailSenderConfig {
    /*========== Properties ===========*/
    @JsonProperty
    private String protocol = "smtp";

    /**
     * SMTP服务地址，例如：smtp.163.com
     */
    @JsonProperty
    private String stmpHost;

    /**
     * STMP服务端口，默认465
     */
    @JsonProperty
    private int stmpPort = 0;

    /**
     * 发送邮件是否需要验证， true：是；false：否；
     */
    @JsonProperty
    private Boolean stmpAuth = true;

    /**
     * 是否试用SSL验证，未完成
     * 需要使用命令行安装证书：
     *  java InstallCert smtp.xyz.com:465
     *  得到jssecacerts文件后复制到jdk1.6.0_14\jre\lib\security目录
     */
    @JsonProperty
    private Boolean useSSL = true;//TODO 需要自动运行命令导入证书

    /**
     * 发送邮件账号
     */
    @JsonProperty
    private String username;

    /**
     * 发送邮件账号对应的密码，可能是验证码
     */
    @JsonProperty
    private String password;

    /*========== Getter & Setter ===========*/
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getStmpHost() {
        return stmpHost;
    }

    public void setStmpHost(String stmpHost) {
        this.stmpHost = stmpHost;
    }

    public Boolean getStmpAuth() {
        return stmpAuth;
    }

    public void setStmpAuth(Boolean stmpAuth) {
        this.stmpAuth = stmpAuth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStmpPort() {
        return stmpPort;
    }

    public void setStmpPort(int stmpPort) {
        this.stmpPort = stmpPort;
    }

    public Boolean getUseSSL() {
        return useSSL;
    }

    public void setUseSSL(Boolean useSSL) {
        this.useSSL = useSSL;
    }
}
