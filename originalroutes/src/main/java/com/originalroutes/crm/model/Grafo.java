package com.originalroutes.crm.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.Data;

@Data
public class Grafo<Tipo> {
	private ArrayList<Vertice<Tipo>> vertices;
	private ArrayList<Aresta<Tipo>> arestas;
	
	public Grafo() {
		this.vertices = new ArrayList<Vertice<Tipo>>();
		this.arestas = new ArrayList<Aresta<Tipo>>();
	}
	
	public void adicionarVertice(Tipo dado) {
		Vertice<Tipo> novoVertice = this.getVertice(dado);
		if (novoVertice == null) {
		novoVertice = new Vertice<Tipo>(dado);
		this.vertices.add(novoVertice);
		}
	}
	
	public void adicionarAresta(Long peso, Tipo dadoInicio, Tipo dadoFim) {
		Vertice<Tipo> inicio = this.getVertice(dadoInicio);
		Vertice<Tipo> fim = this.getVertice(dadoFim);
		Aresta<Tipo> aresta = new Aresta<Tipo>(peso,inicio,fim);
		inicio.adicionarArestaSaida(aresta);
		fim.adicionarArestaEntrada(aresta);
		this.arestas.add(aresta);
	}
	
	public Vertice<Tipo> getVertice(Tipo dado){
		Vertice<Tipo> vertice = null;
		for(int i=0; i < this.vertices.size(); i++) {
			if (this.vertices.get(i).getDado().equals(dado)){
				vertice = this.vertices.get(i);
				break;
			}
		}
		return vertice;
	}
	
	public Aresta<Tipo> getAresta(Tipo dado){
		Aresta<Tipo> aresta = null;
		for(int i=0; i < this.arestas.size(); i++) {
			if (this.arestas.get(i).getInicio().equals(dado)){
				aresta = this.arestas.get(i);
				break;
			}
		}
		return aresta;
	}
	
	public void buscaEmLargura() {
		ArrayList<Vertice<Tipo>> marcados = new ArrayList<Vertice<Tipo>>();
		ArrayList<Vertice<Tipo>> fila = new ArrayList<Vertice<Tipo>>();
		Vertice<Tipo> atual = this.vertices.get(0);
		marcados.add(atual);
		System.out.println(atual.getDado());
		fila.add(atual);
		while(fila.size() > 0) {
			Vertice<Tipo> visitado = fila.get(0);
			for(int i=0; i < visitado.getArestasSaida().size(); i++) {
				Vertice<Tipo> proximo = visitado.getArestasSaida().get(i).getFim();
				if (!marcados.contains(proximo)) {
					marcados.add(proximo);
					System.out.println(proximo.getDado());
					fila.add(proximo);
				}
			}
			fila.remove(0);
		}
	}
	
	public GrafoResposta buscaRotas(Tipo dadoInicio, Tipo dadoFim, Long maxStops) {
		GrafoResposta grafos = new GrafoResposta(null);
		ArrayList<Routes> rotas = new ArrayList<Routes>();
		
		Aresta<Tipo> atual = null;
		for(Aresta<Tipo> itens : this.arestas) {	
		String caminho = itens.getInicio().getDado().toString();
		Long distancia = itens.getPeso();
		Long stops = 0L;
		atual = itens;		
		if (itens.getInicio().getDado().equals(dadoInicio)) {
			while (!caminho.contains(dadoFim.toString())) {
				for(Aresta<Tipo> subitens : this.arestas) {
					if (subitens.getInicio().getDado().equals(atual.getFim().getDado())) {
						atual = subitens;
						if(!caminho.contains(subitens.getInicio().getDado().toString()) && !caminho.contains(subitens.getFim().getDado().toString()) && !caminho.contains(dadoFim.toString())) {
							caminho = caminho+subitens.getInicio().getDado();
							distancia = distancia+subitens.getPeso();
							stops = stops+1;
							if(caminho.contains(dadoFim.toString())) {
								if(stops<=maxStops) {
									Routes caminhos = new Routes(caminho, stops);
									rotas.add(caminhos); 
									grafos.setRoutes(rotas);								
								}
							}
						}
					}
				}
			}
		}
		}
		return grafos;
	}
	

	

}