package com.rafael.projetomvc.resources;

import java.net.URI;
import java.util.List;
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

import com.rafael.projetomvc.DTO.ClienteDTO;
import com.rafael.projetomvc.DTO.ClienteNewDTO;
import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.services.ClienteService;

@RestController
@RequestMapping(value ="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping(value = "/{id}", method =  RequestMethod.GET )
	public ResponseEntity<Cliente> buscarCliente(@PathVariable Integer id){
		
		Cliente obj = clienteService.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		Cliente obj = clienteService.fromDTO(objDTO);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}" , method = RequestMethod.DELETE)
	public ResponseEntity<Void>delete(@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping( method = RequestMethod.GET)
	public ResponseEntity <List<ClienteDTO>>listarTodos() {

	List<Cliente> list= clienteService.findAll();
	List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDTO);
		
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity <Page<ClienteDTO>>findPage(@RequestParam(value = "page",defaultValue = "0")Integer page , @RequestParam(value = "linesPage",defaultValue = "24")Integer linesPage, 
			@RequestParam(value = "orderBy",defaultValue = "nome")String orderBy,@RequestParam(value = "direction",defaultValue = "ASC")String direction) {

	Page<Cliente> list= clienteService.findPage(page, linesPage, orderBy, direction);
	Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
	return ResponseEntity.ok().body(listDTO);
		
	}
	
	@RequestMapping(method = RequestMethod.POST )
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO){
		Cliente obj = clienteService.fromDTO(objDTO);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	

}
