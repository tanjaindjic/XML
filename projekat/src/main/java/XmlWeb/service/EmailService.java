package XmlWeb.service;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

	private final String from="piginco@gmail.com";
	private final String host = "localhost";
	
@SuppressWarnings("restriction")
@Async
  public void sendEmail(String to) {

		System.out.println("Sending email...");
		try{
	        String user = "piginco@gmail.com";
	        String pass = "pigincoNTM10";	        
	        String subject = "Activate your account";
	        String messageText = "Please go to following link to activate your account: https://localhost:8096/confirm?token=" + to;
	        boolean sessionDebug = false;
	
	        Properties props = System.getProperties();
	
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.required", "true");
	
	        java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	        Session mailSession = Session.getDefaultInstance(props, null);
	        mailSession.setDebug(sessionDebug);
	        Message msg = new MimeMessage(mailSession);
	        msg.setFrom(new InternetAddress(from));
	        InternetAddress[] address = {new InternetAddress(to)};
	        msg.setRecipients(Message.RecipientType.TO, address);
	        msg.setSubject(subject); msg.setSentDate(new Date());
	        msg.setText(messageText);
	
	       Transport transport=mailSession.getTransport("smtp");
	       transport.connect(host, user, pass);
	       transport.sendMessage(msg, msg.getAllRecipients());
	       transport.close();
	       System.out.println("message send successfully");
	    }catch(Exception ex)
	    {
	        System.out.println(ex);
	    }

    
  }
}