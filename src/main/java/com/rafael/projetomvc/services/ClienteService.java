package com.rafael.projetomvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.DTO.ClienteDTO;
import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.repository.ClienteRepository;
import com.rafael.projetomvc.services.exception.DataIntegrityException;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente find(Integer cod) {
		Optional<Cliente> obj = clienteRepository.findById(cod);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Cliente Id não encontrado id:" + cod + Cliente.class));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj,obj);
		return clienteRepository.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}

	public void delete(Integer id) {
		 find(id);
		try {
			clienteRepository.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é Possível excluir um cliente que tenha pedidos");
		}
		
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page , Integer linesPage, String orderBy,String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(),clienteDTO.getNome(),clienteDTO.getEmail(),null,null);
	}


}
