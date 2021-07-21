package com.originalroutes.crm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.List;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.jsonpath.JsonPath;

@WebAppConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
class ReqControllerGraphTest {
	
	@Autowired
	private ReqControllerGraph reqControllerGraph;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}
	

	@Test
	public void Teste1() throws Exception {
		
		String mockRequestBodyAsString = "{\r\n"
				+ "  \"path\":[\"A\", \"B\", \"C\"],\r\n"
				+ "  \"data\":[\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"B\", \"distance\":5     \r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"B\", \"target\": \"C\", \"distance\":4\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"D\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"C\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"E\", \"distance\":6\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"D\", \"distance\":5\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"E\", \"distance\":2\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"E\", \"target\": \"B\", \"distance\":3\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\",  \"target\": \"E\", \"distance\":7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		
		String expectedData = "9";
		mockMvc.perform(post("/distance")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mockRequestBodyAsString.getBytes()))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.distance").value(expectedData));
		
	}
	
	@Test
	public void Teste2() throws Exception {
		
		String mockRequestBodyAsString = "{\r\n"
				+ "  \"path\":[\"A\", \"D\"],\r\n"
				+ "  \"data\":[\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"B\", \"distance\":5     \r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"B\", \"target\": \"C\", \"distance\":4\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"D\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"C\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"E\", \"distance\":6\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"D\", \"distance\":5\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"E\", \"distance\":2\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"E\", \"target\": \"B\", \"distance\":3\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\",  \"target\": \"E\", \"distance\":7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		
		String expectedData = "5";
		mockMvc.perform(post("/distance")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mockRequestBodyAsString.getBytes()))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.distance").value(expectedData));
		
	}
	
	@Test
	public void Teste3() throws Exception {
		
		String mockRequestBodyAsString = "{\r\n"
				+ "  \"path\":[\"A\", \"D\", \"C\"],\r\n"
				+ "  \"data\":[\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"B\", \"distance\":5     \r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"B\", \"target\": \"C\", \"distance\":4\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"D\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"C\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"E\", \"distance\":6\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"D\", \"distance\":5\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"E\", \"distance\":2\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"E\", \"target\": \"B\", \"distance\":3\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\",  \"target\": \"E\", \"distance\":7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		
		String expectedData = "13";
		mockMvc.perform(post("/distance")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mockRequestBodyAsString.getBytes()))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.distance").value(expectedData));
		
	}
	
	@Test
	public void Teste4() throws Exception {
		
		String mockRequestBodyAsString = "{\r\n"
				+ "  \"path\":[\"A\", \"E\", \"B\", \"C\", \"D\"],\r\n"
				+ "  \"data\":[\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"B\", \"distance\":5     \r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"B\", \"target\": \"C\", \"distance\":4\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"D\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"C\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"E\", \"distance\":6\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"D\", \"distance\":5\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"E\", \"distance\":2\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"E\", \"target\": \"B\", \"distance\":3\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\",  \"target\": \"E\", \"distance\":7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		
		String expectedData = "22";
		mockMvc.perform(post("/distance")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mockRequestBodyAsString.getBytes()))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.distance").value(expectedData));
		
	}
	
	@Test
	public void Teste5() throws Exception {
		
		String mockRequestBodyAsString = "{\r\n"
				+ "  \"path\":[\"A\", \"E\", \"D\"],\r\n"
				+ "  \"data\":[\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"B\", \"distance\":5     \r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"B\", \"target\": \"C\", \"distance\":4\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"D\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"C\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"E\", \"distance\":6\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"D\", \"distance\":5\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"E\", \"distance\":2\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"E\", \"target\": \"B\", \"distance\":3\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\",  \"target\": \"E\", \"distance\":7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		
		String expectedData = "-1";
		mockMvc.perform(post("/distance")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mockRequestBodyAsString.getBytes()))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.distance").value(expectedData));
		
	}
	
	@Test
	public void Teste6() throws Exception {
		
		String mockRequestBodyAsString = "{\r\n"
				+ "  \"data\":[\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"B\", \"distance\":5     \r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"B\", \"target\": \"C\", \"distance\":4\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"D\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"C\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"E\", \"distance\":6\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"D\", \"distance\":5\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"E\", \"distance\":2\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"E\", \"target\": \"B\", \"distance\":3\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\",  \"target\": \"E\", \"distance\":7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		mockMvc.perform(post("/routes/from/C/to/C?maxStops=9")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mockRequestBodyAsString.getBytes()))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.routes[:1].route").value("C"))
		                .andExpect(jsonPath("$.routes[:1].stops").value(0));
		
	}
	
	@Test
	public void Teste7() throws Exception {
		
		String mockRequestBodyAsString = "{\r\n"
				+ "  \"data\":[\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"B\", \"distance\":5     \r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"B\", \"target\": \"C\", \"distance\":4\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"D\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"C\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"E\", \"distance\":6\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"D\", \"distance\":5\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"E\", \"distance\":2\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"E\", \"target\": \"B\", \"distance\":3\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\",  \"target\": \"E\", \"distance\":7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		mockMvc.perform(post("/routes/from/A/to/C?maxStops=4")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mockRequestBodyAsString.getBytes()))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.routes[0].route").value("ABC"))
		                .andExpect(jsonPath("$.routes[0].stops").value(2))
						.andExpect(jsonPath("$.routes[1].route").value("ADC"))
				        .andExpect(jsonPath("$.routes[1].stops").value(2))
						.andExpect(jsonPath("$.routes[2].route").value("AEBC"))
				        .andExpect(jsonPath("$.routes[2].stops").value(3))
						.andExpect(jsonPath("$.routes[3].route").value("ADEBC"))
				        .andExpect(jsonPath("$.routes[3].stops").value(4));
		
		
	}
	
	@Test
	public void Teste8() throws Exception {
		
		String mockRequestBodyAsString = "{\r\n"
				+ "  \"data\":[\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"B\", \"distance\":5     \r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"B\", \"target\": \"C\", \"distance\":4\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"D\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"C\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"E\", \"distance\":6\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"D\", \"distance\":5\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"E\", \"distance\":2\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"E\", \"target\": \"B\", \"distance\":3\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\",  \"target\": \"E\", \"distance\":7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		mockMvc.perform(post("/distance/from/A/to/C")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mockRequestBodyAsString.getBytes()))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.distance").value("9"))
		                .andExpect(jsonPath("$.path[0]").value("A"))
		                .andExpect(jsonPath("$.path[1]").value("B"))
		                .andExpect(jsonPath("$.path[2]").value("C"));
		
	}
	
	@Test
	public void Teste9() throws Exception {
		
		String mockRequestBodyAsString = "{\r\n"
				+ "  \"data\":[\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"B\", \"distance\":5     \r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"B\", \"target\": \"C\", \"distance\":4\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"D\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"C\", \"distance\":8\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"D\", \"target\": \"E\", \"distance\":6\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\", \"target\": \"D\", \"distance\":5\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"C\", \"target\": \"E\", \"distance\":2\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"E\", \"target\": \"B\", \"distance\":3\r\n"
				+ "    },\r\n"
				+ "    { \r\n"
				+ "      \"source\": \"A\",  \"target\": \"E\", \"distance\":7\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		

		mockMvc.perform(post("/distance/from/B/to/B")
		                .contentType(MediaType.APPLICATION_JSON)
		                .content(mockRequestBodyAsString.getBytes()))
		                .andExpect(status().isOk())
		                .andExpect(jsonPath("$.distance").value("0"))
		                .andExpect(jsonPath("$.path[0]").value("B"));
		
	}
	
}
