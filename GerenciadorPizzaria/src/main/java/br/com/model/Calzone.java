package br.com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Calzone implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	
	private String sabor1;
	
	private String sabor2;
	
	private String tipo;
	
	private String tamanho;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	private Pedidos pedidos;

	public String getSabor1() {
		return sabor1;
	}

	public void setSabor1(String sabor1) {
		this.sabor1 = sabor1;
	}

	public String getSabor2() {
		return sabor2;
	}

	public void setSabor2(String sabor2) {
		this.sabor2 = sabor2;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setPedidos(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
	public Pedidos getPedidos() {
		return pedidos;
	}
	
	

}
