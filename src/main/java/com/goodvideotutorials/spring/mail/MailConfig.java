package com.goodvideotutorials.spring.mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Value("${mail.sender.host}")
	private String host;
	@Value("${mail.authenticator.password}")
	private String password;
	@Value("${mail.authenticator.email}")
	private String username;

	@Bean
	@Profile("dev")
	public MailSender mockMailSender(){
		return new MockMailSender();
	}
	
	@Bean
	@Profile("!dev")
	public MailSender smtpMailSender(){
		SmtpMailSender mailsender =  new SmtpMailSender();
		mailsender.setJavaMailSender(javaMailSender());
		return mailsender;
	}

	/**
	 * once declared(annotated) as bean the same instance will be returned no matter
	 * how many time javaMailSender() is called
	 * @return
	 */
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		System.out.println("Creating instance of javaMailSender for host "+host);
		sender.setHost(host);
		sender.setSession(getMailSession());
		return sender;
	}

	private Session getMailSession() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.socketFactory.port", 465);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", false);
		return Session.getInstance(props, getAuthenticator());
	}

	private Authenticator getAuthenticator() {
		SmtpAuthenticator authenticator = new SmtpAuthenticator();
		System.out.println("Call authenticator username = "+username+" password = "+password+" host = "+host);
		authenticator.setUsername(username);
		authenticator.setPassword(password);
		return authenticator;
	}
}
