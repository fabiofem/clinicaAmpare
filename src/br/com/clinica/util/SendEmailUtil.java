package br.com.clinica.util;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailUtil {
	
	
	
	public static void sendEmail(File file, String para){
		final String username = "clinicampare@gmail.com";
		final String password = "clinicampare123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("clinicampare@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			
			
			message.setSubject("Relat�rios de Pacientes Clinica Ampare");
			message.setText("Relat�rios de Pacientes Clinica Ampare,"
				+ "\n\n Segue para conhecimento o Relat�rio do dia!");

			
			 //4) create new MimeBodyPart object and set DataHandler object to this object        
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();      
            //String filename = "SendAttachment.java";//change accordingly     
            DataSource source = new FileDataSource(file);    
            messageBodyPart2.setDataHandler(new DataHandler(source));    
            messageBodyPart2.setFileName(file.getName()); 
			
            
            //5) create Multipart object and add MimeBodyPart objects to this object        
            Multipart multipart = new MimeMultipart();    
               
            multipart.addBodyPart(messageBodyPart2); 
			
			message.setContent(multipart);
			
			Transport.send(message);
			LogUtil.Log(SendEmailUtil.class.getName()+"  O EMAIL FOI ENCAMINHADO CORRECTAMENTE : "+file.getName() ,Level.INFO);
			

		} catch (MessagingException e) {
			 LogUtil.Log(SendEmailUtil.class.getName()+"  PROBLEMAS AO MANDAR O EMAIL: "+e.getMessage(),Level.SEVERE); 
			throw new RuntimeException(e);
		}

	}
	public static void sendEmailArquivo(File file, String para){
		final String username = "clinicampare@gmail.com";
		final String password = "clinicampare123";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("clinicampare@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			
			
			message.setSubject("Impress�es Digitais para eventual recuperação");
			message.setText("Impress�es Digitais para eventual recuperação,"
				+ "\n\n Impress�es Digitais para eventual recuperação");

			
			 //4) create new MimeBodyPart object and set DataHandler object to this object        
            MimeBodyPart messageBodyPart2 = new MimeBodyPart();      
            //String filename = "SendAttachment.java";//change accordingly     
            DataSource source = new FileDataSource(file);    
            messageBodyPart2.setDataHandler(new DataHandler(source));    
            messageBodyPart2.setFileName(file.getName()); 
			
            
            //5) create Multipart object and add MimeBodyPart objects to this object        
            Multipart multipart = new MimeMultipart();    
               
            multipart.addBodyPart(messageBodyPart2); 
			
			message.setContent(multipart);
			
			Transport.send(message);
			LogUtil.Log(SendEmailUtil.class.getName()+"  O EMAIL FOI ENCAMINHADO CORRECTAMENTE : "+file.getName() ,Level.INFO);
			

		} catch (MessagingException e) {
			 LogUtil.Log(SendEmailUtil.class.getName()+"  PROBLEMAS AO MANDAR O EMAIL: "+e.getMessage(),Level.SEVERE); 
			throw new RuntimeException(e);
		}

	}

}
