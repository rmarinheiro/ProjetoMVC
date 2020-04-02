package com.rafael.projetomvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafael.projetomvc.dominio.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
