package com.rafael.projetomvc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rafael.projetomvc.dominio.Categoria;
import com.rafael.projetomvc.dominio.Produto;
import com.rafael.projetomvc.repository.CategoriaRepository;
import com.rafael.projetomvc.repository.ProdutoRepository;

@SpringBootApplication
public class ProjetoMvcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
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
		
		
		
		
		
		
	}

}
