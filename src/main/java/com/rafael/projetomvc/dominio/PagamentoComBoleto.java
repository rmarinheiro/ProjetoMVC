package com.rafael.projetomvc.dominio;

import java.util.Date;

import javax.persistence.Entity;

import com.rafael.projetomvc.dominio.enums.EstadoPagamento;
@Entity
public class PagamentoComBoleto extends Pagamento {
	
	
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimneto;
	private Date dataPagamento;

	
	public PagamentoComBoleto() {
		// TODO Auto-generated constructor stub
	}


	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido,Date dataPagamento, Date dataVencimneto) {
		super(id, estadoPagamento, pedido);
		this.dataVencimneto = dataVencimneto;
		this.dataPagamento = dataPagamento;
		// TODO Auto-generated constructor stub
	}


	public Date getDataVencimneto() {
		return dataVencimneto;
	}


	public void setDataVencimneto(Date dataVencimneto) {
		this.dataVencimneto = dataVencimneto;
	}


	public Date getDataPagamento() {
		return dataPagamento;
	}


	public void setDataPagaemnto(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	
	

	
	
	
}
