package com.apirest.books.backend.response;

public class LibroResponseRest extends ResponseREST{
	
	private LibroResponse libroResponse = new LibroResponse();

	public LibroResponse getLibroResponse() {
		return libroResponse;
	}

	public void setLibroResponse(LibroResponse libroResponse) {
		this.libroResponse = libroResponse;
	}
	
	
}
