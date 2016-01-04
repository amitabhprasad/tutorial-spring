package com.goodvideotutorials.spring.mail;

import javax.mail.MessagingException;

public interface MailSender {

	/**
	 * 
	 * @param to
	 * @param subject
	 * @param body
	 */
	void send(String to, String subject, String body)throws MessagingException;

}