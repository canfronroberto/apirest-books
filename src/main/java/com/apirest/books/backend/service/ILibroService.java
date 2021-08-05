package com.apirest.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.apirest.books.backend.model.Libro;
import com.apirest.books.backend.response.LibroResponseRest;

public interface ILibroService {
	
	public ResponseEntity<LibroResponseRest> buscarLibros();
	public ResponseEntity<LibroResponseRest> buscarPorId(Long id);
	public ResponseEntity<LibroResponseRest> crearLibro(Libro libro);
	public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Long id);
	public ResponseEntity<LibroResponseRest> eliminarLibro(Long id);
}
