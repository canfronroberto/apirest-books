package com.apirest.books.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.apirest.books.backend.model.Libro;

public interface ILibroDao extends CrudRepository<Libro, Long>{

}
