package com.example.cdc.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;

import com.example.cdc.dto.AutorDto;
import com.example.cdc.models.Autor;
import com.example.cdc.repositories.AutorRepository;
import com.example.cdc.service.AutorService;

@ExtendWith(MockitoExtension.class)
public class AutorServiceTest {

	private AutorService autorService;
	
	@Mock
	private AutorRepository autorRepository;

	@BeforeEach
	public void setUp() {
		autorService = new AutorService(autorRepository);
	}
	
	@Test
	public void deve_cadastrar_um_novo_autor() {
		
		// arrange
		AutorDto autorDto = AutorDto.builder()
				.email(null)
				.nome(null)
				.descricao(null)
				.build();

		// act and assert
		this.autorService.cadastrarNovoAutor(autorDto);

		// verify
		verify(autorRepository, times(1)).save(any(Autor.class));
		
	}
	
}
