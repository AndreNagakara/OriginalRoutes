package com.originalroutes.crm.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.originalroutes.crm.model.Grafo;
import com.originalroutes.crm.repository.GrafoRepository;

@RestController
@RequestMapping("/graph")
public class ReqControllerGraph {
	
    @Autowired
	private GrafoRepository grafoRepository;
	
	@PostMapping
	public ResponseEntity<Grafo> addGrafo(@RequestBody Grafo grafo) {
		grafoRepository.save(grafo);
		return ResponseEntity.created(URI.create("/graph")).body(grafo);
   }
	
	@GetMapping("/{id}")
	public ResponseEntity<Grafo> getGrafo(@PathVariable Long id) {
		Grafo grafo = grafoRepository.findOne(id);
		
		if (grafo == null ) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(grafo);
	}
	
	
	
}


