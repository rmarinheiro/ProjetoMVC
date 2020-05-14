package com.rafael.projetomvc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.repository.ClienteRepository;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;


@Service
public class AuthServices {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired	
	private EmailService emailService;
	
	@Autowired
	private BCryptPasswordEncoder be;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) throws ObjectNotFoundException {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não Encontrado");
		}
		
		String newPass = newPassword();
		cliente.setSenha(be.encode(newPass));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char [] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i]= randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char)(rand.nextInt(10) + 48);
		}else if(opt == 1) {//gera um digito Maiusculo
			return (char)(rand.nextInt(26) + 65);
		}else {//gera um digito minusculo
			return (char)(rand.nextInt(10) + 97);
		}
	}

}
