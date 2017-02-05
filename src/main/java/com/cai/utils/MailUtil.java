package com.cai.utils;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by caibaolong on 2017/1/17.
 * 发送邮件的相关工具
 */
public class MailUtil {

    /**
     * 发送一份邮件 统一用caidifa@163.com作为发件人
     *
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param address 目标地址
     * @throws MessagingException 邮件发送时异常
     */
    public static void mailForPassword(String subject,String content,String address) throws MessagingException {
        Properties props = new Properties();
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.163.com");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");
        // 设置环境信息
        Session session = Session.getInstance(props);
        // 创建邮件对象
        Message msg = new MimeMessage(session);
        // 设置邮件主题
        msg.setSubject(subject);
        // 设置邮件内容
        msg.setText(content);
        // 设置发件人
        msg.setFrom(new InternetAddress("caidifa@163.com"));
        Transport transport = session.getTransport();
        // 连接邮件服务器
        transport.connect("caidifa@163.com", "caixjm0");
        // 发送邮件
        transport.sendMessage(msg, new Address[]{new InternetAddress(address)});
        // 关闭连接
        transport.close();
    }

}
