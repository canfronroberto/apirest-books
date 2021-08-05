package com.apirest.books.backend.service;

import org.springframework.http.ResponseEntity;

import com.apirest.books.backend.model.Categoria;
import com.apirest.books.backend.response.CategoriaResponseRest;

public interface ICategoriaService {
	
	public ResponseEntity<CategoriaResponseRest> buscarCategorias();
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id);
	public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria);
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Categoria categoria, Long id);
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id);
	
}
