package test;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by caibaolong on 2017/2/4.
 * 发送邮件的测试程序
 */
public class MailTest {
    public static void main(String[] args) throws MessagingException {
        Properties props = new Properties();
        // 开启debug调试
        props.setProperty("mail.debug", "true");
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
        msg.setSubject("JavaMail测试");
        // 设置邮件内容
        msg.setText("这是一封由JavaMail发送的邮件！");
        // 设置发件人
        msg.setFrom(new InternetAddress("caidifa@163.com"));

        Transport transport = session.getTransport();
        // 连接邮件服务器
        transport.connect("caidifa@163.com", "caixjm0");
        // 发送邮件
        transport.sendMessage(msg, new Address[] {new InternetAddress("caidifa@126.com")});
        // 关闭连接
        transport.close();
    }
}
