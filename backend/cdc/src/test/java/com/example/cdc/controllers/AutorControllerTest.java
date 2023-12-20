package com.example.cdc.controllers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.cdc.dto.AutorDto;
import com.example.cdc.service.AutorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(AutorController.class)
public class AutorControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AutorService autorService;

	@Test
	public void deve_criar_novo_autor_e_retornar_status_201() throws JsonProcessingException, Exception {
		
		// arrange
		AutorDto autorDto = AutorDto.builder()
				.email(null)
				.nome(null)
				.descricao(null)
				.build();
		
		// act and assert
		this.mockMvc
			.perform(MockMvcRequestBuilders.post("/autores")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(autorDto)))
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.content().json(
					new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(autorDto)))
			.andReturn();

		
		// verify
		verify(autorService, times(1)).cadastrarNovoAutor(autorDto);
		
	}
	
}
