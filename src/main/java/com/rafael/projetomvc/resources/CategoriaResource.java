package com.rafael.projetomvc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	

}
