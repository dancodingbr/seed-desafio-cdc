package com.example.cdc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cdc.dto.AutorDto;
import com.example.cdc.service.AutorService;

@RestController
public class AutorController {

	@Autowired
	private AutorService autorService;

	@PostMapping("/autores")
	public ResponseEntity<AutorDto> cadastrarNovoAutor(@RequestBody AutorDto autor) {

		this.autorService.cadastrarNovoAutor(autor);
		
		final HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<AutorDto>(autor, httpHeaders, HttpStatus.CREATED);
	}
	
}
