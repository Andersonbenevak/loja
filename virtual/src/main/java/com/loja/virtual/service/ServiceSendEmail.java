package com.loja.virtual.service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ServiceSendEmail {
	
	private String UserName = "andersonebenevak10@gmail.com";
	private String senha = "exgd fbql tkdr zuys";
	
	
	@Async
	public void EnviarEmailHtml(String assunto , String mensaguem, String emailDestino )throws UnsupportedEncodingException, MessagingException {
		
		Properties properties = new Properties();

		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls", "false");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		   
		   Session session = Session.getInstance(properties, new Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {
	                   return new PasswordAuthentication(UserName, senha);
	            }
	     });
		   
		   session.setDebug(true);
		   
		   InternetAddress[] toUser = InternetAddress.parse(emailDestino);
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(UserName, "Anderson Loja Virtual", "UTF-8"));
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(assunto);
			message.setContent(mensaguem, "text/html; charset=utf-8");
			
			Transport.send(message);
			
	}

}
