package com.originalroutes.crm.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.originalroutes.crm.model.Dados;
import com.originalroutes.crm.model.Grafo;
import com.originalroutes.crm.model.GrafoResposta;
import com.originalroutes.crm.model.Rota;
import com.originalroutes.crm.repository.DadosRepository;

@RestController
@RequestMapping
public class ReqControllerGraph {
	
    @Autowired
	private DadosRepository dadosRepository;
	
	@PostMapping("/graph")
	public ResponseEntity<Dados> addGrafo(@RequestBody Dados dados) {
		dadosRepository.save(dados);
		return ResponseEntity.created(URI.create("/graph")).body(dados);
    }
	
	@GetMapping("/graph/{id}")
	public ResponseEntity<Dados> getGrafo(@PathVariable Long id) {
		Dados dados = dadosRepository.findOne(id);
		
		if (dados == null ) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(dados);
	}
	
	@PostMapping("/routes/from/{t1}/to/{t2}")
	public ResponseEntity<GrafoResposta> consultaRota(@PathVariable String t1, @PathVariable String t2, @RequestBody Dados dados, @RequestParam(required = false) Long maxStops ) {
		Grafo grafo = new Grafo();
		for(int i=0; i < dados.getData().size(); i++)
	    {
			
			Rota a = dados.getData().get(i);
			grafo.adicionarVertice(a.getSource());
			grafo.adicionarVertice(a.getTarget());
			grafo.adicionarAresta(a.getDistance(), a.getSource(), a.getTarget());
	    }
		GrafoResposta grafoResposta = grafo.buscaRotas(t1,t2,maxStops);
			
		return ResponseEntity.created(URI.create("/graph")).body(grafoResposta);
    }
	
	@PostMapping("/routes/{id}/from/{t1}/to/{t2}")
	public ResponseEntity<GrafoResposta> consultaRota(@PathVariable Long id, @PathVariable String t1, @PathVariable String t2, @RequestParam(required = false) Long maxStops ) {
		Grafo grafo = new Grafo();
		Dados dados = dadosRepository.findOne(id);
		
		if (dados == null ) {
			return ResponseEntity.notFound().build();
		}
		
		for(int i=0; i < dados.getData().size(); i++)
	    {
			
			Rota a = dados.getData().get(i);
			grafo.adicionarVertice(a.getSource());
			grafo.adicionarVertice(a.getTarget());
			grafo.adicionarAresta(a.getDistance(), a.getSource(), a.getTarget());
	    }
		GrafoResposta grafoResposta = grafo.buscaRotas(t1,t2,maxStops);
			
		return ResponseEntity.created(URI.create("/graph")).body(grafoResposta);
    }
	
	
}


