package com.originalroutes.crm.model;

import java.util.ArrayList;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Vertice<Tipo> {
	
	@Id
	private Tipo dado;
	private ArrayList<Aresta<Tipo>> arestasEntrada;
	private ArrayList<Aresta<Tipo>> arestasSaida;
	
	public Vertice(Tipo valor) {
		this.dado = valor;
		this.arestasEntrada = new ArrayList<Aresta<Tipo>>();
		this.arestasSaida = new ArrayList<Aresta<Tipo>>();
	}
	
	public void adicionarArestaEntrada(Aresta<Tipo> aresta) {
		this.arestasEntrada.add(aresta);
	}
	
	public void adicionarArestaSaida(Aresta<Tipo> aresta) {
		this.arestasSaida.add(aresta);
	}
}
