package com.originalroutes.crm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Rota {
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	@Column(nullable = true)
	private String source;
	
	@Column(nullable = true)
	private String target;
	
	@Column(nullable = true)
	private Long distance;
	
	

}

