package com.rafael.projetomvc.dominio;

import java.util.Date;

import javax.persistence.Entity;

import com.rafael.projetomvc.dominio.enums.EstadoPagamento;
@Entity
public class PagamentoComBoleto extends Pagamento {
	
	
	private static final long serialVersionUID = 1L;
	
	private Date dataVencimneto;
	private Date dataPagaemnto;

	
	public PagamentoComBoleto() {
		// TODO Auto-generated constructor stub
	}


	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido,Date dataVencimento,Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		this.dataVencimneto = dataVencimneto;
		this.dataPagaemnto = dataPagaemnto;
		// TODO Auto-generated constructor stub
	}


	public Date getDataVencimneto() {
		return dataVencimneto;
	}


	public void setDataVencimneto(Date dataVencimneto) {
		this.dataVencimneto = dataVencimneto;
	}


	public Date getDataPagaemnto() {
		return dataPagaemnto;
	}


	public void setDataPagaemnto(Date dataPagaemnto) {
		this.dataPagaemnto = dataPagaemnto;
	}

	
	

	
	
	
}
