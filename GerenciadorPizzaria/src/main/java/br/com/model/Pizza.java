package br.com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pizza implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	
	private String tamanho;
	
	private String tipo;
	
	private String sabor1;
	
	private String sabor2;
	
	private String sabor3;
	
	private String sabor4;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	private Pedidos pedidos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

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

	public String getSabor3() {
		return sabor3;
	}

	public void setSabor3(String sabor3) {
		this.sabor3 = sabor3;
	}

	public String getSabor4() {
		return sabor4;
	}

	public void setSabor4(String sabor4) {
		this.sabor4 = sabor4;
	}
	
	public void setPedidos(Pedidos pedidos) {
		this.pedidos = pedidos;
	}
	
	public Pedidos getPedidos() {
		return pedidos;
	}
	
	
}
