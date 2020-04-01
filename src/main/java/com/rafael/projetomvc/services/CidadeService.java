package com.rafael.projetomvc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Cidade;
import com.rafael.projetomvc.repository.CidadeRepository;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade find(Integer id) {
		Optional<Cidade> obj = cidadeRepository.findById(id);
		return obj.orElseThrow( ()-> new ObjectNotFoundException("Cidade de ID não encontrada " + id + "Tipo "+ Cidade.class));
	}

}
