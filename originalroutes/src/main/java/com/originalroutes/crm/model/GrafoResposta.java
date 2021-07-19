package com.originalroutes.crm.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class GrafoResposta {
	
	private ArrayList<Routes> routes;
	
	public GrafoResposta(ArrayList<Routes> routes) {
		this.routes = routes;
	}
	
	public void adicionarrota(Routes route) {
		this.routes.add(route);
	}
		
}
