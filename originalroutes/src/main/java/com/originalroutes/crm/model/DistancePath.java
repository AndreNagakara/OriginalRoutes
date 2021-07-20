
package com.originalroutes.crm.model;

import java.util.ArrayList;
import lombok.Data;

@Data
public class DistancePath {
	
	private Long distance;
	private ArrayList<String> path;
	
	public DistancePath(Long distance, ArrayList<String> path ) {
		this.distance = distance;
		this.path = path;
	}
	
}
