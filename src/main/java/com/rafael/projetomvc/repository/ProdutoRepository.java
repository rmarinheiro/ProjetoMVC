package com.rafael.projetomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.projetomvc.dominio.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
