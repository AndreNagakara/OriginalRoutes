package com.originalroutes.crm.model;

import lombok.Data;

@Data
public class ArrayDistance {
	
	private Long distance;

	public ArrayDistance(Long distance) {
		this.distance = distance;
	}
}
