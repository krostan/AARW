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
	public static void sendEmail(String recipientEmail, String resetToken) {
		// STMP 服務器設置
		String host = "smtp.gmail.com";
        int port = 587;
        // 寄信的 google帳號 跟應用程式密碼
        String username = "";
        String password = "";
        // 發送者
        String fromEmail = "";
        // 接收者
        String toEmail = recipientEmail;
      
        String subject = "重置密碼";
        String body = "請點擊下方連結重置您的密碼：\n" + "http://localhost:8080/CatDemo/reset-pwd?token=" + resetToken;
        
        // 設定 SMTP 伺服器相關資訊
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        // 建立 Session 物件
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        try {
            // 建立郵件訊息物件
            MimeMessage message = new MimeMessage(session);
            // 設定發送者
            message.setFrom(new InternetAddress(fromEmail));
            // 設定接收者
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            // 主旨
            message.setSubject(subject);
            // 內容
            message.setText(body);
            
            // 寄送郵件
            Transport.send(message);
            
            System.out.println("郵件已成功寄出");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
