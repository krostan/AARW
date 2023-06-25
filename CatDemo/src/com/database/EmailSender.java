package com.database;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public static void sendEmail(String recipientEmail, String subject, String jsonContent) {
		
		String host = "smtp.gmail.com";
        int port = 587;
        String username = "";
        String password = "";
        
        // 設定 SMTP 伺服器相關資訊
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        
        // 建立 Session 物件
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            // 建立郵件訊息物件
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject(subject);
            message.setText(jsonContent);
            
            // 寄送郵件
            Transport.send(message);
            
            System.out.println("郵件已成功寄出");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
