package com.originalroutes.crm.controller;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.originalroutes.crm.model.ArrayDistance;
import com.originalroutes.crm.model.Dados;
import com.originalroutes.crm.model.Rota;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ReqControllerGraphTest {
	
	@Autowired
	private ReqControllerGraph reqControllerGraph;
	
	public Dados dados = new Dados();
	
	
// Grafo Entrada: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
	public void populadados(ArrayList<String> paths) {
		
		List<Rota> rotas = new ArrayList<Rota>(null);
	//	rotas.add(new Rota("A","B",(long) 5));
	//	rotas.add(new Rota("B","C",(long) 4));
	//	rotas.add(new Rota("C","D",(long) 8));
	//	rotas.add(new Rota("D","C",(long) 8));
	//	rotas.add(new Rota("D","E",(long) 6));
	//	rotas.add(new Rota("A","D",(long) 5));
	//	rotas.add(new Rota("C","E",(long) 2));
	//	rotas.add(new Rota("E","B",(long) 3));
	//	rotas.add(new Rota("A","E",(long) 7));
		dados.setData(paths,rotas);
	}
		
	@Test
	public void teste1() {
		//1.	Distância da Rota ABC: 9
		ArrayList<String> paths = new ArrayList<String>(null);
		paths.add("A");
		paths.add("B");
		paths.add("C");
		populadados(paths);
		ArrayDistance arrayDistance	= new ArrayDistance((long) 9);
		assertThat(reqControllerGraph.calculadistancia(dados
				), is ( arrayDistance ) );
				
	}

}
