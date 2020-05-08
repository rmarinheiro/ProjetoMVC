package com.rafael.projetomvc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafael.projetomvc.DTO.CategoriaDTO;
import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.services.CategoriaService;

@RestController
@RequestMapping(value ="/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria>listar(@PathVariable Integer id) {
//	  Categoria c1 = new Categoria(1,"Informatica");
//	  
//	  Categoria c2 = new Categoria(2, "Escritorio");
//	  
//	  List<Categoria> lista = new ArrayList<Categoria>();
//	  lista.add(c1);
//	  lista.add(c2);
	Categoria obj = categoriaService.buscar(id);
	return ResponseEntity.ok().body(obj);
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDTO){
		Categoria obj = categoriaService.fromDTO(objDTO);
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id){
		Categoria obj = categoriaService.fromDTO(objDTO);
		obj.setId(id);
		obj = categoriaService.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void>delete(@PathVariable Integer id){
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity <List<CategoriaDTO>>listarTodos() {

	List<Categoria> list= categoriaService.findAll();
	List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDTO);
		
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity <Page<CategoriaDTO>>findPage(@RequestParam(value = "page",defaultValue = "0")Integer page , @RequestParam(value = "linesPage",defaultValue = "24")Integer linesPage, 
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,@RequestParam(value = "direction",defaultValue = "ASC")String direction) {

	Page<Categoria> list= categoriaService.findPage(page, linesPage, orderBy, direction);
	Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
	return ResponseEntity.ok().body(listDTO);
		
	}
}
