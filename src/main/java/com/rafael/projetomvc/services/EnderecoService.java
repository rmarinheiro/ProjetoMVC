package com.rafael.projetomvc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rafael.projetomvc.dominio.Endereco;
import com.rafael.projetomvc.repository.EnderecoRepository;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;

public class EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco find(Integer cod) {
		Optional<Endereco> obj = enderecoRepository.findById(cod);
		return (Endereco) obj.orElseThrow(()->new ObjectNotFoundException("Id do Endereço não encontrado:"+ cod + Endereco.class));
	}

}
