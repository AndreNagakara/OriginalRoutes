package com.originalroutes.crm.controller;
 
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.originalroutes.crm.model.ArrayDistance;
import com.originalroutes.crm.model.Dados;
import com.originalroutes.crm.model.DistancePath;
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
		Grafo<String> grafo = new Grafo<String>();
		for(int i=0; i < dados.getData().size(); i++)
	    {
			
			Rota a = dados.getData().get(i);
			grafo.adicionarVertice(a.getSource());
			grafo.adicionarVertice(a.getTarget());
			grafo.adicionarAresta(a.getDistance(), a.getSource(), a.getTarget());
	    }
		GrafoResposta grafoResposta = grafo.buscaRotas(t1,t2,maxStops);
			
		return ResponseEntity.ok(grafoResposta);
    }
	
	@PostMapping("/routes/{id}/from/{t1}/to/{t2}")
	public ResponseEntity<GrafoResposta> consultaRotagravada(@PathVariable Long id, @PathVariable String t1, @PathVariable String t2, @RequestParam(required = false) Long maxStops ) {
		Grafo<String> grafo = new Grafo<String>();
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
			
		return ResponseEntity.ok(grafoResposta);
    }
	
	
	@PostMapping("/distance")
	public ResponseEntity<ArrayDistance> calculadistancia(@RequestBody Dados dados) {
		Grafo<String> grafo = new Grafo<String>();
		
		for(int i=0; i < dados.getData().size(); i++)
	    {
			
			Rota a = dados.getData().get(i);
		  	grafo.adicionarVertice(a.getSource());
		  	grafo.adicionarVertice(a.getTarget());
		 	grafo.adicionarAresta(a.getDistance(), a.getSource(), a.getTarget());
	     
	    }
		
		ArrayDistance distancia = grafo.calculaDistancia(dados.getPath());
		
		return ResponseEntity.ok(distancia);
    }
	
	@PostMapping("/distance/{id}")
	public ResponseEntity<ArrayDistance> calculadistanciagravada(@PathVariable Long id, @RequestBody Dados dados) {
		Grafo<String> grafo = new Grafo<String>();
		Dados dadosgravados = dadosRepository.findOne(id);
		
		if (dadosgravados == null ) {
			return ResponseEntity.notFound().build();
		}
		
		for(int i=0; i < dadosgravados.getData().size(); i++)
	    {
			
			Rota a = dadosgravados.getData().get(i);
		  	grafo.adicionarVertice(a.getSource());
		  	grafo.adicionarVertice(a.getTarget());
		 	grafo.adicionarAresta(a.getDistance(), a.getSource(), a.getTarget());
	     
	    }
		
		ArrayDistance distancia = grafo.calculaDistancia(dados.getPath());
		
		
		
		
		return ResponseEntity.ok(distancia);
    }
	
	@PostMapping("/distance/from/{t1}/to/{t2}")
	public ResponseEntity<DistancePath> distanciacompath(@PathVariable String t1,@PathVariable String t2, @RequestBody Dados dados) {
		Grafo<String> grafo = new Grafo<String>();
			
		for(int i=0; i < dados.getData().size(); i++)
	    {
			
			Rota a = dados.getData().get(i);
		  	grafo.adicionarVertice(a.getSource());
		  	grafo.adicionarVertice(a.getTarget());
		 	grafo.adicionarAresta(a.getDistance(), a.getSource(), a.getTarget());
	     
	    }

		DistancePath distancia = grafo.buscaMelhorRota(t1,t2);
		
		return ResponseEntity.ok(distancia);
    }
	
	@PostMapping("/distance/{id}/from/{t1}/to/{t2}")
	public ResponseEntity<DistancePath> distanciacompathgravada(@PathVariable Long id,@PathVariable String t1,@PathVariable String t2) {
		Grafo<String> grafo = new Grafo<String>();
		Dados dadosgravados = dadosRepository.findOne(id);
	
		if (dadosgravados == null ) {
			return ResponseEntity.notFound().build();
		}
		
		for(int i=0; i < dadosgravados.getData().size(); i++)
	    {
			
			Rota a = dadosgravados.getData().get(i);
		  	grafo.adicionarVertice(a.getSource());
		  	grafo.adicionarVertice(a.getTarget());
		 	grafo.adicionarAresta(a.getDistance(), a.getSource(), a.getTarget());
	     
	    }

		DistancePath distancia = grafo.buscaMelhorRota(t1,t2);
		
		return ResponseEntity.ok(distancia);
    }
	
}


