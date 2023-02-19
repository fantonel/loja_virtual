package br.com.fantonel.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Address;
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
public class SendMailService {
	private String[] emailConfig;
	
	public SendMailService() {
	}
	
	@PostConstruct
	public void init() {
		if(emailConfig == null)
			emailConfig = getEmailConfig();
	}
	
	@Async
	public void sendHtmlMail(String assunto, String mensagem, String emailDestino) throws UnsupportedEncodingException, MessagingException{		
		Properties props = new Properties();
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls", "false");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(props, new Authenticator() {		      
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {				
				return new PasswordAuthentication(emailConfig[0], emailConfig[1]);
			}		
		});
		
		session.setDebug(true);
		
		Address[] toUser = InternetAddress.parse(emailDestino+","+"7e.engenharia@gmail.com");
		
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(emailConfig[0], "7e Engenharia", "UTF-8"));
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assunto.toString());
		message.setContent(mensagem, "text/html; charset=utf-8");
		
		Transport.send(message);
	}
	
	//Leio um arquivo texto com usuário/senha do email, apenas para não deixar fixa na aplicação,
	//uma vez que o projeto é público no GitHUb.
	private String[] getEmailConfig() {
		String configs[] = new String[] {"",""};
		String path  = "C:\\workspace\\dev\\_eclipse_workspace\\loja_virtual_email_config.txt";
		String linha = "";
		BufferedReader buffRead;
		
		int index = 0;
		try {
			buffRead = new BufferedReader(new FileReader(path));
			while (true) {
				if (linha != null) {
					if (index == 1)					
						configs[0] = linha;
					if (index == 2)					
						configs[1] = linha;
				} else
					break;
				linha = buffRead.readLine();
				index++;
			}
			buffRead.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return configs;
	}
}