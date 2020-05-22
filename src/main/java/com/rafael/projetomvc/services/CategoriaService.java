package com.rafael.projetomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.DTO.CategoriaDTO;
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
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado ID " + id + "Tipo" + Categoria.class));
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = buscar(obj.getId());
		updateDate(newObj,obj);
		return categoriaService.save(newObj);
	}

	private void updateDate(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
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
	
	public Page<Categoria> findPage(Integer page , Integer linesPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return categoriaService.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}

}
