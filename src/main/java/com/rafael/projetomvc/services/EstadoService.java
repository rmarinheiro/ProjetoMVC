package com.rafael.projetomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Estado;
import com.rafael.projetomvc.repository.EstadoRepository;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Estado> find() throws Exception {
		try {
			return estadoRepository.findAllByOrderByNome();
		} catch (Exception e) {
			throw new Exception("Lista de Estados não encontrada");
		}
		
	}
	

}
