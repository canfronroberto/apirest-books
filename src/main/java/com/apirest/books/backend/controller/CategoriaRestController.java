package com.apirest.books.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.books.backend.model.Categoria;
import com.apirest.books.backend.response.CategoriaResponseRest;
import com.apirest.books.backend.service.ICategoriaService;

@CrossOrigin(origins = {"http://localhost:4200"}) // acepta la url que le pasamos para la comunicacion entre cliente y servidor
@RestController
@RequestMapping("/v1")
public class CategoriaRestController {
	
	@Autowired
	private ICategoriaService service;
	
	@GetMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> consultaCategoria() {
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		return response;
	}
	
	@GetMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> consultaPorIdEntity(@PathVariable Long id){
		ResponseEntity<CategoriaResponseRest> response = service.buscarPorId(id);
		return response;
	}
	
	@PostMapping("/categorias")
	public ResponseEntity<CategoriaResponseRest> crearCategoria(@RequestBody Categoria request){
		ResponseEntity<CategoriaResponseRest> response = service.crearCategoria(request);
		return response;
	}
	
	@PutMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(@RequestBody Categoria request, @PathVariable Long id){
		ResponseEntity<CategoriaResponseRest> response = service.actualizarCategoria(request, id);
		return response;
	}
	
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(@PathVariable Long id){
		ResponseEntity<CategoriaResponseRest> response = service.eliminarCategoria(id);
		return response;
	}
}
