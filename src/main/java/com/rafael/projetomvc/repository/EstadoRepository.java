package com.rafael.projetomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.projetomvc.dominio.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}