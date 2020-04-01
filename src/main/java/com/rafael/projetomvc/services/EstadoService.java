package com.rafael.projetomvc.services;

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
	
	public Estado find(Integer id) {
		Optional<Estado> obj = estadoRepository.findById(id);
		return obj.orElseThrow(()->new ObjectNotFoundException("Estado de ID não encontrado" + id + "Tipo" + Estado.class));
	}
	

}
