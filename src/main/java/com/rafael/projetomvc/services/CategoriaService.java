package com.rafael.projetomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.repository.CategoriaRepository;
import com.rafael.projetomvc.services.exception.DataIntegrityException;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaService;
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return categoriaService.save(obj);
	}
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = categoriaService.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado ID" + id + "Tipo" + Categoria.class));
	}

	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return categoriaService.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			categoriaService.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é Possível excluir uma categoria que tenha produdutos");
		}
		
	}

	public List<Categoria> findAll() {
		return categoriaService.findAll();
	}

}
