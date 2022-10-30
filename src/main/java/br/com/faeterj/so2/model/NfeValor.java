package br.com.faeterj.so2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="prod")
public class NfeValor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	String cnpj;

	String NomeEmissor;
	String NomeCliente;
	
	String produto;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNomeEmissor() {
		return NomeEmissor;
	}
	public void setNomeEmissor(String nomeEmissor) {
		NomeEmissor = nomeEmissor;
	}
	public String getNomeCliente() {
		return NomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	

}
