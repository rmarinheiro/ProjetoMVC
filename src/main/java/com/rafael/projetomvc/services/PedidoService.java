package com.rafael.projetomvc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.dominio.ItemPedido;
import com.rafael.projetomvc.dominio.PagamentoComBoleto;
import com.rafael.projetomvc.dominio.Pedido;
import com.rafael.projetomvc.dominio.enums.EstadoPagamento;
import com.rafael.projetomvc.repository.ItemPedidoRepository;
import com.rafael.projetomvc.repository.PagamentoRepository;
import com.rafael.projetomvc.repository.PedidoRepository;
import com.rafael.projetomvc.security.UserSS;
import com.rafael.projetomvc.services.exception.AuthorizationException;
import com.rafael.projetomvc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido find(Integer cod) {
		Optional<Pedido> obj = pedidoRepository.findById(cod);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Pedido Id não encontrado" + cod + "Tipo " + Pedido.class));
	}

	@Transactional
	public Pedido insert( Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preenchePagamentoComBoleto(pagto,obj.getInstante());
		}
		
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		//System.out.println(obj);
		//emailService.SenderConfirmationEmail(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
		
	}
	


	public Page<Pedido> findPage(Integer page , Integer linesPage, String orderBy,String direction){
		UserSS userSS = UserService.authenticated();
		if(userSS == null) {
			throw new AuthorizationException("Acesso Negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		Cliente cl = clienteService.find(userSS.getId());
		return pedidoRepository.findyByCliente(cl.getId(), pageRequest);
	}

}
