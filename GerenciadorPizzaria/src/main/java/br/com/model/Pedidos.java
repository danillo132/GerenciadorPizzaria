package br.com.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Pedidos implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	@JsonIgnore
	@ManyToOne(optional = false)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedidos", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Pizza> pizzas = new ArrayList<Pizza>();
	
	@OneToMany(mappedBy = "pedidos", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Bebidas> bebidas = new ArrayList<Bebidas>();
	
	@OneToMany(mappedBy = "pedidos", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Calzone> calzones = new ArrayList<Calzone>();

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public List<Bebidas> getBebidas() {
		return bebidas;
	}

	public void setBebidas(List<Bebidas> bebidas) {
		this.bebidas = bebidas;
	}

	public List<Calzone> getCalzones() {
		return calzones;
	}

	public void setCalzones(List<Calzone> calzones) {
		this.calzones = calzones;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	
	
	

}
