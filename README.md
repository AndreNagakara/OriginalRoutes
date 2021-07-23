# OriginalRoutes
API para gerar Rotas

###############################################################################################################
Salvar Grafo
Esse endpoint deverá receber as arestas de um grafo e salvá-las em um banco de dados para consultas posteriores.
•	Endpoint: /graph
•	HTTP Method: POST
•	HTTP Success Response Code: CREATED (201)
•	Contract:
o	Request payload
{
  "data":[
    { 
      "source": "A", "target": "B", "distance":6
    },
    { 
      "source": "A", "target": "E", "distance":4
    },
    { 
      "source": "B", "target": "A", "distance":6
    },
    { 
      "source": "B", "target": "C", "distance":2
    },
    { 
      "source": "B", "target": "D", "distance":4
    },
    { 
      "source": "C", "target": "B", "distance":3
    },
    { 
      "source": "C", "target": "D", "distance":1
    },
    { 
      "source": "C", "target": "E", "distance":7
    },
    { 
      "source": "E",  "target": "B", "distance":5
    },
    { 
      "source": "E", "target": "D", "distance":7
    }
  ]
}
•	Response payload
{
  "id" : 1,
  "data":[
    { 
      "source": "A", "target": "B", "distance":6
    },
    { 
      "source": "A", "target": "E", "distance":4
    },
    { 
      "source": "B", "target": "A", "distance":6
    },
    { 
      "source": "B", "target": "C", "distance":2
    },
    { 
      "source": "B", "target": "D", "distance":4
    },
    { 
      "source": "C", "target": "B", "distance":3
    },
    { 
      "source": "C", "target": "D", "distance":1
    },
    { 
      "source": "C", "target": "E", "distance":7
    },
    { 
      "source": "E",  "target": "B", "distance":5
    },
    { 
      "source": "E", "target": "D", "distance":7
    }
  ]
}


###############################################################################################################
Recuperar Grafo
Esse endpoint deverá retornar um grafo previamente salvo no banco de dados. Se o grafo não existe, deverá retornar HTTP NOT FOUND.
•	Endpoint: /graph/<graphId>
•	HTTP Method: GET
•	HTTP Success Response Code: OK (200)
•	HTTP Error Response Code: NOT FOUND (404)
•	Contract:
o	Request payload: none
o	Response payload
{
  "id" : 1,
  "data":[
    { 
      "source": "A", "target": "B", "distance":6
    },
    { 
      "source": "A", "target": "E", "distance":4
    },
    { 
      "source": "B", "target": "A", "distance":6
    },
    { 
      "source": "B", "target": "C", "distance":2
    },
    { 
      "source": "B", "target": "D", "distance":4
    },
    { 
      "source": "C", "target": "B", "distance":3
    },
    { 
      "source": "C", "target": "D", "distance":1
    },
    { 
      "source": "C", "target": "E", "distance":7
    },
    { 
      "source": "E",  "target": "B", "distance":5
    },
    { 
      "source": "E", "target": "D", "distance":7
    }
  ]
}





###############################################################################################################
Encontrar todas rotas disponíveis dado um bairro de origem e outro de destino
Esse endpoint deverá calcular todas as rotas disponíveis de um bairro de origem para outro de destino, dado um número máximo de paradas. Se não existirem rotas possíveis, o resultado deverá ser uma lista vazia. Se o parâmetro "maxStops" não for definido, você deverá listar todas as rotas possíveis.
Exemplo: No grafo (AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7), as possíveis rotas de A para C com máximo de 3 paradas seriam: ["ABC", "ADC", "AEBC"]
•	Endpoint: /routes/from/<town1>/to/<town2>?maxStops=<maxStops>
•	HTTP Method: POST
•	HTTP Response Code: OK (200)
•	Contract:
o	Request payload
{
  "data":[
    { 
      "source": "A", "target": "B", "distance":5
    },
    { 
      "source": "B", "target": "C", "distance":4
    },
    { 
      "source": "C", "target": "D", "distance":8
    },
    { 
      "source": "D", "target": "C", "distance":8
    },
    { 
      "source": "D", "target": "E", "distance":6
    },
    { 
      "source": "A", "target": "D", "distance":5
    },
    { 
      "source": "C", "target": "E", "distance":2
    },
    { 
      "source": "E", "target": "B", "distance":3
    },
    { 
      "source": "A", "target": "E", "distance":7
    }
  ]
}
•	Response payload
{
  "routes": [
    {
      "route": "ABC",
      "stops": 2
    },
    {
      "route": "ADC",
      "stops": 2
    },
    {
      "route": "AEBC",
      "stops": 3
    }
  ]
}



###############################################################################################################
Encontrar todas as rotas disponíveis, dado um bairro de origem e outro de destino em um grafo salvo anteriormente
Esse endpoint deverá fazer exatamente o mesmo que o anterior, porém utilizando um grafo salvo anteriormente. Se o grafo não existir, deverá retornar HTTP NOT FOUND.
•	Endpoint: /routes/<graphId>/from/<town1>/to/<town2>?maxStops=<maxStops>
•	HTTP Method: POST
•	HTTP Success Response Code: OK (200)
•	HTTP Error Response Code: NOT FOUND (404)
•	Contract:
o	Request payload: none
o	Response payload
{
  "routes": [
    {
      "route": "ABC",
      "stops": 2
    },
    {
      "route": "ADC",
      "stops": 2
    },
    {
      "route": "AEBC",
      "stops": 3
    }
  ]
}



###############################################################################################################
Determinar distância de um caminho específico
Esse endpoint deverá retornar a distância total de um caminho entre uma lista direcionada e específica de bairros. Caso a lista de bairros esteja vazia ou seja unitária, o resultado deverá ser zero. Se o dado caminho não existir, então o resultado deverá ser -1.
•	Endpoint: /distance
•	HTTP Method: POST
•	HTTP Response Code: OK (200)
•	Contract:
o	Request payload
{
  "path":["A", "B", "C", "D"],
  "data":[
    { 
      "source": "A", "target": "B", "distance":6
    },
    { 
      "source": "A", "target": "E", "distance":4
    },
    { 
      "source": "B", "target": "A", "distance":6
    },
    { 
      "source": "B", "target": "C", "distance":2
    },
    { 
      "source": "B", "target": "D", "distance":4
    },
    { 
      "source": "C", "target": "B", "distance":3
    },
    { 
      "source": "C", "target": "D", "distance":1
    },
    { 
      "source": "C", "target": "E", "distance":7
    },
    { 
      "source": "E",  "target": "B", "distance":5
    },
    { 
      "source": "E", "target": "D", "distance":7
    }
  ]
}
•	Response payload
{
  "distance" : 9
}




###############################################################################################################
Determinar a distância de um caminho específico em um grafo salvo
Esse endpoint deverá fazer exatamente o mesmo que o anterior, porém utilizando um grafo salvo anteriormente. Se o grafo não existir, deverá retornar HTTP NOT FOUND.
•	Endpoint: /distance/<graphId>
•	HTTP Method: POST
•	HTTP Success Response Code: OK (200)
•	HTTP Error Response Code: NOT FOUND (404)
•	Contract:
o	Request payload
{
  "path":["A", "B", "C", "D"]
}
•	Response payload
{
  "distance" : 9
}



###############################################################################################################
Determinar a distância mínima entre dois bairros
Esse endpoint deverá determinar a rota cuja distância seja a mínima possível entre dois bairros. Se os bairros de origem e destino forem iguais, o resultado deverá ser zero. Se não existir rota possível entre os dois bairros, então o resultado deverá ser -1.
•	Endpoint: /distance/from/<town1>/to/<town2>
•	HTTP Method: POST
•	HTTP Response Code: OK (200)
•	Contract:
o	Request payload
{
  "data":[
    { 
      "source": "A", "target": "B", "distance":6
    },
    { 
      "source": "A", "target": "E", "distance":4
    },
    { 
      "source": "B", "target": "A", "distance":6
    },
    { 
      "source": "B", "target": "C", "distance":2
    },
    { 
      "source": "B", "target": "D", "distance":4
    },
    { 
      "source": "C", "target": "B", "distance":3
    },
    { 
      "source": "C", "target": "D", "distance":1
    },
    { 
      "source": "C", "target": "E", "distance":7
    },
    { 
      "source": "E",  "target": "B", "distance":5
    },
    { 
      "source": "E", "target": "D", "distance":7
    }
  ]
}
•	Response payload
{
  "distance" : 8,
  "path" : ["A", "B", "C"]
}



###############################################################################################################
Determinar a distância mínima entre dois bairros em um grafo salvo
Esse endpoint deverá fazer exatamente o mesmo que o anterior, porém utilizando um grafo salvo anteriormente. Se o grafo não existir, deverá retornar HTTP NOT FOUND.
•	Endpoint: /distance/<graphId>/from/<town1>/to/<town2>
•	HTTP Method: POST
•	HTTP Success Response Code: OK (200)
•	HTTP Error Response Code: NOT FOUND (404)
•	Contract:
o	Request payload: none
o	Response payload
{
  "distance" : 8,
  "path" : ["A", "B", "C"]
}
Dados para Teste
Grafo Entrada: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7


###############################################################################################################
Em /originalroutes/src/test/java/com/originalroutes/crm/controller/ReqControllerGraphTest.java

Casos de Teste:
1.	Distância da Rota ABC: 9
2.	Distância da Rota AD: 5
3.	Distância da Rota ADC: 13
4.	Distância da Rota AEBCD: 22
5.	Distância da Rota AED: -1 (Inexistente)
6.	Rotas de origem C e destino C com um máximo de 3 paradas:
o	C (0 paradas)
7.	Rotas de origem A e destino C com um máximo de 4 paradas:
o	ABC (2 paradas)
o	ADC (2 paradas)
o	AEBC (3 paradas)
o	ADEBC (4 paradas)
8.	Distância mínima de A para C: ABC (distância = 9)
9.	Distância mínima de B para B: B (distância = 0)
