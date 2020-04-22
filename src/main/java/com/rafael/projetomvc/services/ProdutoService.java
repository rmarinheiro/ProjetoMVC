package com.rafael.projetomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.dominio.Produto;
import com.rafael.projetomvc.repository.CategoriaRepository;
import com.rafael.projetomvc.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto  find(Integer cod) {
		Optional<Produto> obj = produtoRepository.findById(cod);
		return obj.orElse(null);
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page , Integer linesPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return produtoRepository.seach(nome,categorias,pageRequest);
		
	}

}
