package com.example.cdc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cdc.dto.AutorDto;
import com.example.cdc.models.Autor;
import com.example.cdc.repositories.AutorRepository;

@Service
public class AutorService {

	@Autowired
	private AutorRepository autorRepository;

	public AutorService(AutorRepository autorRepository) {
		this.autorRepository = autorRepository;
	}

	public void cadastrarNovoAutor(AutorDto autorDto) {

		Autor autor = Autor.builder()
				.instante(null)
				.email(null)
				.nome(null)
				.descricao(null)
				.build();
		
		this.autorRepository.save(autor);
		
	}

}
