package com.apirest.books.backend.response;

import java.util.List;

import com.apirest.books.backend.model.Libro;

public class LibroResponse {
	
	private List<Libro> libro;

	public List<Libro> getLibro() {
		return libro;
	}

	public void setLibro(List<Libro> libro) {
		this.libro = libro;
	}
	
	
}
