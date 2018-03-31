package com.jedrek.graduation.utils;

import java.security.GeneralSecurityException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class MailUtil {
    public static void main(String[] args) throws Exception {
        String to = "1850715048@qq.com";
        String userMessage = "Jedrek_1850715048@qq.com_12345";
        sendMail(to, userMessage);
    }


    /**
     * 向指定的邮箱发送一封激活邮件
     * @param to 发送地址
     */
    public static void sendMail(String to, String userMessage) {
        // 发件人电子邮箱
        String from = "1309898641@qq.com";
        // 指定发送邮件的主机为 smtp.qq.com
        String host = "smtp.qq.com";  //QQ 邮件服务器
        // 获取系统属性
        Properties properties = System.getProperties();
        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("xxxxx@qq.com", "xxxxxx"); //发件人邮件用户名、密码
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("Welcome to DocumentHub!");

            // 设置消息体
            // todo 两个问题，一个应发送一个html页面，激活链接应修改
            String hash = makeHashCode(userMessage);
            String html = makeMailContent(to, hash);
            message.setContent(html, "text/html;charset=utf-8");
            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....from runoob.com");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


    /**
     * 创建邮件内容
     * @param to  邮箱地址
     * @param hash l链接中的动态内容
     *
     */
    public static String makeMailContent(String to, String hash) {
        String url = String.format("localhost:8088/email=%s&hash=%s", to, hash);
        String html = "<h2>欢迎你注册DoucmentHub，请点击以下链接来激活全部服务</h2>";
        String link = String.format("<a href=\"%s\">Join us</a>", url);
        String end = "<h3>如果此操作非本人，请忽略此封邮件</h3>";
        return html+link+end;
    }

    public static String makeHashCode(String message) {
        String cryptKey = "magiccode";
        String data = message;
        try {
            return DESUtil.encrypt(data, cryptKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("生成注册hash值失败");
        }
    }
}
