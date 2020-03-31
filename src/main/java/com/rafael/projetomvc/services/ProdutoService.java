package com.rafael.projetomvc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.dominio.Produto;
import com.rafael.projetomvc.repository.ProdutoRepository;

public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto  find(Integer cod) {
		Optional<Produto> obj = produtoRepository.findById(cod);
		return obj.orElse(null);
	}

}
