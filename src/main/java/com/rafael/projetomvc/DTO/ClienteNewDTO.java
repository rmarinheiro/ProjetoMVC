package com.rafael.projetomvc.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rafael.projetomvc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "Prenchimento Obrigatório")
	@Length(min = 5 , max = 120 , message = "O Tamanho tem que ser de 5 a 120 caracteres")
	private String nome;
	
	@NotEmpty
	@Email(message = "Email Inválido")
	private String email;
	
	@NotEmpty(message = "Preenchimento é Obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipoCliente;
	
	@NotEmpty(message = "Prenchimento é obrigatório")
	private String senha;
	
	@NotEmpty(message = "Preenchimento é Obrigatório")
	private String logradouro;
	
	private String numero;
	@NotEmpty(message = "Preenchimento é Obrigatório")
	private String complemento;
	
	@NotEmpty(message = "Preenchimento é Obrigatório")
	private String bairro;
	
	@NotEmpty(message = "Preenchimento é Obrigatório")
	private String cep;
	@NotEmpty(message = "Preenchimento é Obrigatório")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	private Integer cidadeID;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Integer getCidadeID() {
		return cidadeID;
	}

	public void setCidadeID(Integer cidadeID) {
		this.cidadeID = cidadeID;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

	
}
