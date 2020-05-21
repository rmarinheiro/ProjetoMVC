package com.rafael.projetomvc.services;

import java.util.List;
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
	
	public List<Cidade> findByEstados(Integer id) {
		List<Cidade> obj = cidadeRepository.findCidades(id);
		return obj;
	}

}
