package com.rafael.projetomvc.dominio;

import javax.persistence.Entity;

import com.rafael.projetomvc.dominio.enums.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer numerodeParcelas;
	
	public PagamentoComCartao() {
	}

	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numerodeParcelas) {
		super(id, estadoPagamento, pedido);
		this.numerodeParcelas = numerodeParcelas;
	}

	public Integer getNumerodeParcelas() {
		return numerodeParcelas;
	}

	public void setNumerodeParcelas(Integer numerodeParcelas) {
		this.numerodeParcelas = numerodeParcelas;
	}

	
	
	
	

}
