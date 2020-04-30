package com.rafael.projetomvc.services;

import org.springframework.mail.SimpleMailMessage;

import com.rafael.projetomvc.dominio.Pedido;

public interface EmailService {
	
	void SenderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
