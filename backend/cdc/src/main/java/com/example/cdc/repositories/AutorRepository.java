package com.example.cdc.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.cdc.models.Autor;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {

}
