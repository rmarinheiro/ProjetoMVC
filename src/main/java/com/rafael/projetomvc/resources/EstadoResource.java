package com.rafael.projetomvc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.projetomvc.DTO.CategoriaDTO;
import com.rafael.projetomvc.DTO.CidadeDTO;
import com.rafael.projetomvc.DTO.EstadoDTO;
import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.dominio.Cidade;
import com.rafael.projetomvc.dominio.Estado;
import com.rafael.projetomvc.services.CidadeService;
import com.rafael.projetomvc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	
	
	@RequestMapping( method =  RequestMethod.GET)
	public ResponseEntity <List<EstadoDTO>>listarTodosEstados() throws Exception {

		List<Estado> list= estadoService.find();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
			
		}
	
	@RequestMapping(value = "/{estadoId}/cidades", method =  RequestMethod.GET)
	public ResponseEntity <List<CidadeDTO>>listarTodasCidades(@PathVariable Integer estadoId) throws Exception {

		List<Cidade> list= cidadeService.findByEstados(estadoId);
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
			
		}

}
