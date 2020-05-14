package com.rafael.projetomvc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.dominio.Cidade;
import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.dominio.Endereco;
import com.rafael.projetomvc.dominio.Estado;
import com.rafael.projetomvc.dominio.ItemPedido;
import com.rafael.projetomvc.dominio.Pagamento;
import com.rafael.projetomvc.dominio.PagamentoComBoleto;
import com.rafael.projetomvc.dominio.PagamentoComCartao;
import com.rafael.projetomvc.dominio.Pedido;
import com.rafael.projetomvc.dominio.Produto;
import com.rafael.projetomvc.dominio.enums.EstadoPagamento;
import com.rafael.projetomvc.dominio.enums.Perfil;
import com.rafael.projetomvc.dominio.enums.TipoCliente;
import com.rafael.projetomvc.repository.CategoriaRepository;
import com.rafael.projetomvc.repository.CidadeRepository;
import com.rafael.projetomvc.repository.ClienteRepository;
import com.rafael.projetomvc.repository.EnderecoRepository;
import com.rafael.projetomvc.repository.EstadoRepository;
import com.rafael.projetomvc.repository.ItemPedidoRepository;
import com.rafael.projetomvc.repository.PagamentoRepository;
import com.rafael.projetomvc.repository.PedidoRepository;
import com.rafael.projetomvc.repository.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	public void instantiateTestDatabase() throws ParseException{
		
		
		
		Categoria cat1 = new Categoria(null,"Informatica");
		
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Categoria cat3 = new Categoria(null,"Cama Mesa e Banho");
		
		Categoria cat4 = new Categoria(null, "Laticinios");
		
		Categoria cat5 = new Categoria(null,"Jardinagem");
		
		Categoria cat6 = new Categoria(null, "Decoração");
		
		Categoria cat7 = new Categoria(null,"Impressora");
		
		
		
		
		
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		
		Produto p2 = new Produto(null, "Impressora", 800.00);
		
		Produto p3 = new Produto(null, "Monitor", 400.00);
		
		Produto p4 = new Produto(null, "Toalha", 50.00);
		
		Produto p5 = new Produto(null, "Colcha", 200.00);
		
		Produto p6 = new Produto(null, "TV", 1200.00);
		
		Produto p7 = new Produto(null, "Roçadeira", 800.00);
		
		Produto p8 = new Produto(null, "Abajur", 400.00);
		
		Produto p9 = new Produto(null, "Pente", 40.00);
		
		Produto p10 = new Produto(null, "Shampoo", 400.00);
		
		Produto p11 = new Produto(null, "Mousse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		
		cat5.getProdutos().addAll(Arrays.asList(p8));
		
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		
		
		p1.getCategorias().addAll(Arrays.asList(cat1,cat4));
		
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2,cat4));
		
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		
		p4.getCategorias().addAll(Arrays.asList(cat2));
		
		p5.getCategorias().addAll(Arrays.asList(cat3));
		
		p6.getCategorias().addAll(Arrays.asList(cat3));
		
		p7.getCategorias().addAll(Arrays.asList(cat4));
		
		p8.getCategorias().addAll(Arrays.asList(cat5));
		
		p9.getCategorias().addAll(Arrays.asList(cat6));
		
		p10.getCategorias().addAll(Arrays.asList(cat6));
		
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Osasco", est2);
		Cidade c3 = new Cidade(null, "São Paulo", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cl1 = new Cliente(null, "Maria", "rsmarinheiro007@gmail.com", "11274293707",TipoCliente.PessoaFisica,pe.encode("123"));
		Cliente cl2 = new Cliente(null, "Ana Costa", "ellencrislopes@gmail.com", "31093361700",TipoCliente.PessoaFisica,pe.encode("123"));
		cl1.getTelefones().addAll(Arrays.asList("22321321","2345678"));
		cl2.getTelefones().addAll(Arrays.asList("22547643","2345679"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "567", "apt 300","Higienpolis", "2027001", cl1, c1);
		Endereco end2 = new Endereco(null, "Rua Mattos", "567", "apt 300","Ibirapuera", "2027001", cl1, c2);
		Endereco end3 = new Endereco(null, "Rua Mariz e barros", "553", "apt 305","Ibirapuera", "2027003", cl2, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(end1,end2));
		cl2.getEnderecos().addAll(Arrays.asList(end3));
		cl2.addPerfil(Perfil.ADMIN);
		
		clienteRepository.saveAll(Arrays.asList(cl1,cl2));
		enderecoRepository.saveAll(Arrays.asList(end1,end2, end3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 19:32"), cl1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("12/10/2017 20:09"), cl1, end2);
		Pedido ped3 = new Pedido(null, sdf.parse("12/10/2017 20:09"), cl2, end3);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 4);
		ped1.setPagamento(pag1);
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,null, sdf.parse("20/10/2017 00:00"));
		ped2.setPagamento(pag2);
		Pagamento pag3 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped3,null, sdf.parse("20/10/2017 00:00"));
		ped3.setPagamento(pag3);
		
		cl1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		cl2.getPedidos().addAll(Arrays.asList(ped3));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2,ped3));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2,pag3));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 2000.00, 1, 200.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 80.00, 2, 40.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 800.00, 1, 100.00);
		ItemPedido ip4 = new ItemPedido(ped3, p2, 800.00, 1, 100.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		ped3.getItens().addAll(Arrays.asList(ip4));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3,ip4));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3,ip4));

	}

}
