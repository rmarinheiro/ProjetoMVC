package com.rafael.projetomvc.repository;





import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafael.projetomvc.dominio.Cliente;
import com.rafael.projetomvc.dominio.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>  {
	
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Pedido obj WHERE obj.cliente.id = id")
	Page<Pedido> findyByCliente(@Param("cliente")Integer id, PageRequest pageRequest);

}
