package com.originalroutes.crm.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
	
	public Aresta<Tipo> getAresta(String dado){
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
	
	
	public ArrayList<Aresta<Tipo>> buscaAresta(Tipo dadoInicio) {
		ArrayList<Aresta<Tipo>> novalista = new  ArrayList<Aresta<Tipo>>();
		for(Aresta<Tipo> itens : this.arestas) {
			if (itens.getInicio().getDado().equals(dadoInicio)) {
				novalista.add(itens);
			}
		}
		return novalista;
	}
	
		public GrafoResposta buscaRotas(Tipo dadoInicio, Tipo dadoFim, Long maxStops) {
			GrafoResposta grafos = new GrafoResposta(null);
			ArrayList<Routes> rotas = new ArrayList<Routes>();
			ArrayList<String> paths = new ArrayList<String>();
			ArrayList<String> finalpaths = new ArrayList<String>();
			String end = "0";
			
			if (maxStops == null) {
				maxStops = 999999L;
			}
				
			if (dadoInicio.equals(dadoFim)) {
				Routes caminhos = new Routes(dadoInicio.toString(), 0L);
				rotas.add(caminhos); 
				grafos.setRoutes(rotas);
				return grafos;
			}
			
			
				
				for(Aresta<Tipo> itens : this.buscaAresta(dadoInicio)) {
					paths.add(itens.getInicio().getDado().toString()+itens.getFim().getDado().toString());
					
				}
				
		   while (end.contains("0")) {	
		   end = "1";
		   ArrayList<String> newpaths = new ArrayList<String>();
			for(String itens2 : paths) {
				for(Aresta<Tipo> itens : this.buscaAresta( (Tipo) itens2.substring(itens2.length() - 1))) {

					
					if(!itens2.contains(itens.getFim().getDado().toString())){
					if (!itens2.substring(itens2.length()- 1).toString().contains(dadoFim.toString())) {
						newpaths.add(itens2+itens.getFim().getDado().toString());
					}else {
						newpaths.add(itens2);
					}	
				}
			}
			
		}
		
		 for(String itens2 : newpaths) {
		 if (!itens2.substring(itens2.length()- 1).toString().contains(dadoFim.toString())) {
			 end = "0";
				 }
				 paths = new ArrayList<String>();
				 paths = newpaths;
				 }	 
		   }
		   ArrayList<String> newpaths = new ArrayList<String>();
		   for (String itens2 : paths) {
			   if (itens2.substring(itens2.length()- 1).toString().contains(dadoFim.toString())) {
				   if (itens2.length()-1 <= maxStops ){
				   newpaths.add(itens2);
		   		   }
			   }
		   }
		 
		   paths = newpaths;
		   
	    
		   Set<String> set = new HashSet<>(paths);
		   paths.clear();
		   paths.addAll(set);  
		   
		   Collections.sort(paths);
		   Collections.sort(paths, (a, b)->Integer.compare(a.length(), b.length()));   
	
		   
		for(String itens2 : paths) {
			Routes caminhos = new Routes(itens2, (long) (itens2.length()-1));
			rotas.add(caminhos); 
			grafos.setRoutes(rotas);
		}
		
		
		return grafos;
	}
	
	
	
	
	public ArrayDistance calculaDistancia(ArrayList<String> array) {
		ArrayDistance arrayDistance = new ArrayDistance(null);
		Long distancia = 0L;
			

		for(int i=0; i < array.size()-1; i++)
		   {
			
			if (i < array.size()) {
				Long loop = 0L;
				for(Aresta<Tipo> itens : this.arestas) {
					if (itens.getInicio().getDado().equals(array.get(i)) && itens.getFim().getDado().equals(array.get(i+1))) {
						distancia = distancia+itens.getPeso();
					}else {
						loop = loop+1;	
					}
					if (loop == this.arestas.size()) {
						loop = 0L;
						arrayDistance.setDistance(loop-1);
						return arrayDistance;
					}	
				}
									
			}
						
		   }
		System.out.println(distancia);
		arrayDistance.setDistance(distancia);
		return arrayDistance;
	}
	
	
	public DistancePath buscaMelhorRota(Tipo dadoInicio, Tipo dadoFim) {
		DistancePath grafos = new DistancePath(null,null);
		ArrayList<String> patch = new ArrayList<String>();
		Aresta<Tipo> atual = null;
		
		if (dadoInicio.equals(dadoFim)) {
			patch.add(dadoInicio.toString());
			grafos = new DistancePath(0L,patch);	
			return grafos;
		}
		
		Long menordistancia = -1L;
		
		String menorcaminho = "";
		
		for(Aresta<Tipo> itens : this.arestas) {	
		if (itens.getInicio().getDado().equals(dadoInicio)) {
			String caminho = itens.getInicio().getDado().toString();
			Long distancia = itens.getPeso();
			caminho = caminho+itens.getFim().getDado().toString();
			atual = itens;
			if (itens.getInicio().getDado().equals(dadoInicio) && itens.getFim().getDado().equals(dadoFim)) {
				patch.add(itens.getInicio().getDado().toString());
				patch.add(itens.getFim().getDado().toString());
				grafos = new DistancePath(itens.getPeso(),patch);	
				return grafos;
			}
			
	
			
				for(Aresta<Tipo> subitens : this.arestas) {
					distancia = itens.getPeso();
			    if (subitens.getInicio().getDado().equals(atual.getFim().getDado()) && !caminho.contains(subitens.getFim().getDado().toString())) {
						if(!caminho.contains(subitens.getFim().getDado().toString()) 
								&& !caminho.contains(dadoFim.toString())) {
							caminho = caminho+subitens.getFim().getDado();
							atual = subitens;
							distancia = distancia+subitens.getPeso();
							if(caminho.contains(dadoFim.toString())) {
									if (distancia < menordistancia || menordistancia == -1L ) {
										menordistancia = distancia;
										menorcaminho = caminho;	
								}
										
							}
						}
					}
		
			}
		}
		}
		
		
		for (char ch: menorcaminho.toCharArray()) {
			patch.add(String.valueOf(ch));
		}
		
		grafos = new DistancePath(menordistancia,patch);	
		return grafos;
	}

	
	
	
}