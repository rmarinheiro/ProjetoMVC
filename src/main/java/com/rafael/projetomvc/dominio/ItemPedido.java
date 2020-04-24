package com.rafael.projetomvc.dominio;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

	
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK itemPedidoPk = new ItemPedidoPK();	
	
	private Double preco;
	
	private Integer quantidade;
	
	private Double desconto;
	
	
	public ItemPedido() {
	}


	public ItemPedido(Pedido pedido, Produto produto, Double preco, Integer quantidade, Double desconto) {
		super();
		itemPedidoPk.setPedido(pedido);
		itemPedidoPk.setProduto(produto);
		this.preco = preco;
		this.quantidade = quantidade;
		this.desconto = desconto;
	}


	public ItemPedidoPK getItemPedidoPk() {
		return itemPedidoPk;
	}


	public void setItemPedidoPk(ItemPedidoPK itemPedidoPk) {
		this.itemPedidoPk = itemPedidoPk;
	}


	public Double getPreco() {
		return preco;
	}


	public void setPreco(Double preco) {
		this.preco = preco;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getDesconto() {
		return desconto;
	}


	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return itemPedidoPk.getPedido();
	}
	
	public void setPedido(Pedido pedido) {
		itemPedidoPk.setPedido(pedido);
	}
	
	
	
	public Produto getProduto() {
		return itemPedidoPk.getProduto();
	}
	
	public void setProduto(Produto produto) {
		itemPedidoPk.setProduto(produto);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemPedidoPk == null) ? 0 : itemPedidoPk.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (itemPedidoPk == null) {
			if (other.itemPedidoPk != null)
				return false;
		} else if (!itemPedidoPk.equals(other.itemPedidoPk))
			return false;
		return true;
	}
	
	public double getSubTotal() {
		return (preco-desconto)*quantidade;
	}
	
	
	
	
}
