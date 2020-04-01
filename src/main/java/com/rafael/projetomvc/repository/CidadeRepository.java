package com.rafael.projetomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.projetomvc.dominio.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
