package cyan.util.email;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DreamInSun on 2017/9/14.
 */
public class EmailMessage {

    /*========== Properties ==========*/
    private String subject; // 标题
    private String content; // 内容

    private String contentType = "text/html;charset=utf-8";

    private String fromMail; // 显示从此邮箱发出邮件
    private List<String> toMails; // 收件人
    private List<String> ccMails; // 抄送人
    private List<String> bccMails; // 秘密抄送人
    private List<File> attachments; // 附件

    /*========== Getter & Setter ==========*/

    /*===== FromMails =====*/
    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail;
    }

    /*===== ToMails =====*/
    public List<String> getToMails() {
        return toMails;
    }

    public void setToMails(List<String> toMails) {
        this.toMails = toMails;
    }

    public void setToMail(String toMail) {
        if (null != toMails) {
            this.toMails.clear();
        } else {
            toMails = new ArrayList<>();
        }
        this.toMails.add(toMail);
    }

    public void addToMail(String toMail) {
        if (null == toMails) {
            toMails = new ArrayList<>();
        }
        this.toMails.add(toMail);
    }

    /*===== CcMails =====*/
    public List<String> getCcMails() {
        return ccMails;
    }

    public void setCcMails(List<String> ccMails) {
        this.ccMails = ccMails;
    }

    public void setCcMail(String ccMail) {
        if (null != ccMails) {
            this.ccMails.clear();
        } else {
            ccMails = new ArrayList<>();
        }
        this.ccMails.add(ccMail);
    }

    public void addCcMail(String ccMail) {
        if (null == ccMails) {
            ccMails = new ArrayList<>();
        }
        this.ccMails.add(ccMail);
    }

    /*===== BccMails =====*/
    public List<String> getBccMails() {
        return bccMails;
    }

    public void setBccMails(List<String> bccMails) {
        this.bccMails = bccMails;
    }

    public void setBccMail(String bccMail) {
        if (null != bccMails) {
            this.bccMails.clear();
        } else {
            bccMails = new ArrayList<>();
        }
        this.bccMails.add(bccMail);
    }

    public void addBccMail(String bccMail) {
        if (null == bccMails) {
            bccMails = new ArrayList<>();
        }
        this.bccMails.add(bccMail);
    }

    /*===== Attachments =====*/
    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }


    /*===== Subject =====*/
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    /*===== Content =====*/
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /*===== ContentType =====*/
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
