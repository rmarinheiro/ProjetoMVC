package com.rafael.projetomvc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SmtpService extends AbstractEmailService {
	
	@Autowired
	private MailSender mailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpService.class);


	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando o email");
		mailSender.send(msg);
		LOG.info("Email enviado");
	}

}
