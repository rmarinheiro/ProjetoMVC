package com.rafael.projetomvc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Pedido;
import com.rafael.projetomvc.repository.PedidoRepository;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido find(Integer cod) {
		Optional<Pedido> obj = pedidoRepository.findById(cod);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Pedido Id não encontrado" + cod + "Tipo " + Pedido.class));
	}

}
