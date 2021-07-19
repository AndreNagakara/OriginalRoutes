package com.originalroutes.crm.model;

import lombok.Data;

@Data
public class Aresta<Tipo> {
	
	private Vertice<Tipo> inicio;
	private Vertice<Tipo> fim;
	private Long peso;
	
	public Aresta(Long peso,Vertice<Tipo> inicio,Vertice<Tipo> fim) {
		this.peso = peso;
		this.inicio = inicio;
		this.fim = fim;
	}
}
