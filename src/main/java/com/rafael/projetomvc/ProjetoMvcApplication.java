package com.rafael.projetomvc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.dominio.Cidade;
import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.dominio.Endereco;
import com.rafael.projetomvc.dominio.Estado;
import com.rafael.projetomvc.dominio.Produto;
import com.rafael.projetomvc.dominio.enums.TipoCliente;
import com.rafael.projetomvc.repository.CategoriaRepository;
import com.rafael.projetomvc.repository.CidadeRepository;
import com.rafael.projetomvc.repository.ClienteRepository;
import com.rafael.projetomvc.repository.EnderecoRepository;
import com.rafael.projetomvc.repository.EstadoRepository;
import com.rafael.projetomvc.repository.ProdutoRepository;

@SpringBootApplication
public class ProjetoMvcApplication implements CommandLineRunner {
	
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
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null,"Informatica");
		
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		
		Produto p2 = new Produto(null, "Impressora", 800.00);
		
		Produto p3 = new Produto(null, "Monitor", 400.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2));
		
		cat2.getProdutos().addAll(Arrays.asList(p3));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Osasco", est2);
		Cidade c3 = new Cidade(null, "São Paulo", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cl1 = new Cliente(null, "Maria", "maria@gmail.com", "11276789081",TipoCliente.PessoaFisica);
		cl1.getTelefones().addAll(Arrays.asList("22321321","2345678"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "567", "apt 300","Higienpolis", "2027001", cl1, c1);
		Endereco end2 = new Endereco(null, "Rua Mattos", "567", "apt 300","Ibirapuera", "2027001", cl1, c2);
		
		cl1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		clienteRepository.saveAll(Arrays.asList(cl1));
		enderecoRepository.saveAll(Arrays.asList(end1,end2));
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
