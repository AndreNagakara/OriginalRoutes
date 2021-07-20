package com.originalroutes.crm.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Routes {
	
	private String route;
	private Long stops;
	
	public Routes(String route, Long stops ) {
		this.route = route;
		this.stops = stops;
	}
}
