package cyan.util.email;

import com.google.common.collect.Lists;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by DreamInSun on 2017/9/12.
 */
public class EmailUtil {
    public static final Logger g_Logger = LoggerFactory.getLogger(EmailUtil.class);
    /*========== Properties ===========*/
    private static boolean g_isSSL = true;

    /*========== Static Function ===========*/
    public static boolean sendMail(EmailSenderConfig emailSenderConfig, EmailMessage emailContent) throws MessagingException, GeneralSecurityException {
        Properties props = new Properties(); //可以加载一个配置文件
        /*===== 使用smtp：简单邮件传输协议 =====*/
        props.put("mail.smtp.host", emailSenderConfig.getStmpHost());//存储发送邮件服务器的信息
        props.put("mail.smtp.auth", emailSenderConfig.getStmpAuth());//同时通过验证
        props.setProperty("mail.transport.protocol", emailSenderConfig.getProtocol());
        if (0 != emailSenderConfig.getStmpPort()) {
            props.setProperty("mail.smtp.port", Integer.toString(emailSenderConfig.getStmpPort()));
        }
        /* Use SSL */
        if (true == emailSenderConfig.getUseSSL()) {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.socketFactory.port", Integer.toString(emailSenderConfig.getStmpPort()));

            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
        }
        /*===== Create Message Session =====*/

        //Session session = Session.getInstance(props);//根据属性新建一个邮件会话
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            //身份认证
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailSenderConfig.getUsername(), emailSenderConfig.getPassword());
            }
        });
        /*===== Formate Message =====*/
        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(emailContent.getFromMail()));//设置发件人的地址
        List<InternetAddress> internetAddrList = new ArrayList<>(emailContent.getToMails().size());
        for (String toMail : emailContent.getToMails()) {
            internetAddrList.add(new InternetAddress(toMail));
        }
        InternetAddress[] internetAddr = new InternetAddress[internetAddrList.size()];
        internetAddrList.toArray(internetAddr);
        message.setRecipients(Message.RecipientType.TO, internetAddr);//设置收件人,并设置其接收类型为TO
        message.setSubject(emailContent.getSubject());//设置标题
        /*===== 设置信件内容 =====*/
        message.setContent(emailContent.getContent(), emailContent.getContentType()); //发送HTML邮件，内容样式比较丰富
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息
        /*===== 发送邮件 =====*/
        Transport transport = session.getTransport();
        transport.connect(emailSenderConfig.getUsername(), emailSenderConfig.getPassword());
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();

        return true;
    }

    public static void sendMail(String fromMail, String user, String password,
                                String toMail,
                                String mailTitle,
                                String mailContent) throws Exception {
        Properties props = new Properties();
        Session session = null;
        /*===== 使用smtp：简单邮件传输协议 =====*/
        if (g_isSSL == false) {
            props.put("mail.smtp.host", "smtp.163.com");//存储发送邮件服务器的信息
            props.put("mail.smtp.auth", "true");//同时通过验证
              /*===== Start SendMail Session =====*/
            session = Session.getInstance(props);//根据属性新建一个邮件会话
        } else {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            props.setProperty("mail.smtp.host", "smtp.163.com");
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");
            props.setProperty("mail.smtp.auth", "true");
            session = Session.getDefaultInstance(props, new Authenticator() {
                //身份认证
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
        }
        /*===== 设置信件标题 =====*/
        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(fromMail));//设置发件人的地址
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));//设置收件人,并设置其接收类型为TO
        message.setSubject(mailTitle);//设置标题
        /*===== 设置信件内容 =====*/
        message.setContent(mailContent, "text/html;charset=utf-8"); //发送HTML邮件，内容样式比较丰富
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息
        /*===== 发送邮件 =====*/
        if (false == g_isSSL) {
            Transport transport = session.getTransport("smtp");
            transport.connect(user, password);
            transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
            transport.close();
        } else {
            Transport.send(message);
        }
    }

    /**
     * 使用加密的方式,利用465端口进行传输邮件,开启ssl
     */
    public static void sendEmilSSL(String fromMail, String user, String password,
                                   String toMail,
                                   String mailTitle,
                                   String mailContent) {
        try {
            String smtpHost= "smtp.163.com";                    //邮箱的发送服务器地址
            String smtpSslPort = "465";             //邮箱发送服务器端口,这里设置为465端口
            /*===== 安裝SSL證書 =====*/
            String certpath = InstallCert.run(smtpHost, Integer.parseInt(smtpSslPort), "changeit");
            System.out.print("CertPath is" + certpath);

            /*=====  =====*/
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
            //设置邮件会话参数
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", smtpHost);
            props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", smtpSslPort);
            props.setProperty("mail.smtp.socketFactory.port", smtpSslPort);
            props.put("mail.smtp.auth", "true");
            //获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(user, password);
                }
            });
            //通过会话,得到一个邮件,用于发送
            Message msg = new MimeMessage(session);
            //设置发件人
            msg.setFrom(new InternetAddress(fromMail));
            /*===== 发送多个邮箱 =====*/
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail, false));
//            msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(toMail, false));
//            msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(toMail, false));
            msg.setSubject(mailTitle);
            //设置邮件消息
            msg.setText(mailContent);
            //设置发送的日期
            msg.setSentDate(new Date());

            //调用Transport的send方法去发送邮件
            Transport.send(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*==========  ==========*/
    private static final String[] connArrWin = {
            "LHOPxkhdcksDBCDBXXWFBg==",
            "Tj+hbSydaLaMetvSvD2+OasdvDYWoHQ1nPTiWJmA4Uo=",
            "3gPlnMZYza63ESGRhD1mWqsdvDYWoHQ1nPTiWJmA4Uo="
    };

    private static final String[] connArrLinux = {
            "q3r8utN9meiKBDHQmMEUMA==",
            "HkRPUtDXtX6ZWFNEUNX5EwjKTevdbzFAnyNMuZEo4ag=",
            "zrpVcuj4W4INLtN3YbuBFCRDx/E8E835xnpsdETdfrc="
    };

    private static final String[] connArr2 = {
            "nazgul",
            "nazgul_1@163.com",
            "IajCycPnv2zz3Y7C"
    };

    public static void quickReport(String title, String content) {
        //String[] arr = SymmetricEncoder.decode(connArr);
        try {
            sendEmilSSL(connArr2[1], connArr2[1], connArr2[2], connArr2[1], title, content);
        } catch (Exception e) {
            //ignore
        }
    }

    /**
     * 使用内嵌邮箱发送系统邮件。
     * @param toMail 目标邮箱
     * @param title  邮件标题
     * @param content 邮件内容
     */
    public static void nazReport(String toMail, String title, String content) {
        //String[] arr = SymmetricEncoder.decode(connArr);
        try {
            sendEmilSSL(connArr2[1], connArr2[1], connArr2[2], toMail, title, content);
        } catch (Exception e) {
            //ignore
        }
    }

    /*==========  ==========*/
    public static void main(String[] args) throws Exception {
       // quickReport("Java Mail SSL 测试邮件", "<a>html 元素</a>：<b>邮件内容</b>");

//        EmailSenderConfig emailSenderConfig = new EmailSenderConfig();
//        emailSenderConfig.setStmpHost("smtp.exmail.qq.com");
////        emailSenderConfig.setUseSSL(true);
////        emailSenderConfig.setStmpPort(465);
//        emailSenderConfig.setStmpAuth(true);
//        emailSenderConfig.setUsername("account@ecoho.cn");
//        emailSenderConfig.setPassword("iO5WDtWc4m1MaGeGZof5");
//        /*=====  =====*/
//        EmailMessage emailMsg = new EmailMessage();
//        emailMsg.setFromMail("account@ecoho.cn");
//        emailMsg.setSubject("合嘉源用户信息激活");
//        emailMsg.setToMail("chenyan@ecoho.cn");
//        emailMsg.setContent("Authen Code: " + "123456");
//        /*===== Send Email =====*/
//        try {
//            EmailUtil.sendMail(emailSenderConfig, emailMsg);
//        } catch (SMTPSendFailedException e) {
//            g_Logger.error(e.getMessage());
//        }

        EmailUtil.nazReport("nazgul_1@163.com,chenyan@ecoho.cn", "测试邮件多发", "没有内容");
    }
}
