package com.rafael.projetomvc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.repository.ClienteRepository;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer cod) {
		Optional<Cliente> obj = clienteRepository.findById(cod);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Cliente Id não encontrado id:" + cod + Cliente.class));
	}

}
