package cn.benjamin.shop.utils;

/**
 * Created by Benjamin on 2018/12/18.
 */

import com.sun.mail.util.MailSSLSocketFactory;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;


public class MailUitls {
    /**
     * 发送邮件的方法
     * @param to	:收件人
     * @param code	:激活码
     */
    public static void sendMail(String to,String code) {
        /**
         * 1.获得一个Session对象.
         * 2.创建一个代表邮件的对象Message.
         * 3.发送邮件Transport
         */


        // 1.获得连接对象
        Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");    // 发送服务器需要身份验证
        props.setProperty("mail.host", "smtp.qq.com");  // 设置邮件服务器主机名
        props.setProperty("mail.transport.protocol", "smtp");   // 发送邮件协议名称

        MailSSLSocketFactory sf = null;   // 开启SSL加密
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        // 填写认证信息
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("benjamin007@vip.qq.com", "qsktsetbvvyebhea");
            }
        });


        // 2.创建邮件对象:
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("benjamin007@vip.qq.com")); // 设置发件人:
            message.addRecipient(RecipientType.TO, new InternetAddress(to));    // 设置收件人:  发送 TO 抄送 CC   密送BCC
            // 设置标题
            message.setSubject("来自购物天堂官方激活邮件");
            // 设置邮件正文:
            message.setContent("<h1>购物天堂官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://127.0.0.1:8080/shop/user_active.action?code=" + code + "'>http://127.0.0.1:8080/shop/user_active.action?code=" + code + "</a></h3>", "text/html;charset=UTF-8");
            // 3.发送邮件:
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


}
