package com.rafael.projetomvc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaService;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = categoriaService.findById(id);
		return obj.orElse(null);
	}

}
