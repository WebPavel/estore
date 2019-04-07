package utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {
    /**
     * 发送邮件
     * @param email
     * @param emailMessage
     */
    public static void sendMail(String email, String emailMessage) throws MessagingException {
        // 创建一个程序与邮件服务器的会话对象Session
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "SMTP");
        properties.setProperty("mail.host", "smtp.163.com");
        properties.setProperty("mail.smtp.auth", "true");
        // 创建身份验证器
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("pau11uis_dev", "Ypp091725");
            }
        };
        Session session = Session.getInstance(properties, authenticator);
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("pau11uis_dev@163.com")); // 设置发送者
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        message.setSubject("用户激活");
        message.setContent(emailMessage, "text/html;charset=UTF-8");
        Transport.send(message);
    }
}
