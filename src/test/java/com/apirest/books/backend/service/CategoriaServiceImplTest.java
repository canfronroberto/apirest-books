package com.apirest.books.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.apirest.books.backend.model.Categoria;
import com.apirest.books.backend.model.dao.ICategoriaDao;
import com.apirest.books.backend.response.CategoriaResponseRest;

public class CategoriaServiceImplTest {

	@InjectMocks // intenta crear una instancia de la instancia del objecto de prueba e inyectas sus campos anotados con la anotacion @Mock
	CategoriaServiceImplementation service;
	
	@Mock // se utiliza para mockear la clase. Imita el comportamiento de la clase pero de forma controlada
	ICategoriaDao categoriaDao;
	
	List<Categoria> list = new ArrayList<Categoria>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this); // sirve para pasarle las anotaciones de mockito y poder usarlas
		this.cargarCategorias();
	}
	
	@Test
	public void buscarCategoriasTest() {
		when(categoriaDao.findAll()).thenReturn(list); // evaluamos si categoriaDao va llamar al metodo findAll y retorna un objeto fijo
		
		ResponseEntity<CategoriaResponseRest> response = service.buscarCategorias();
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).findAll();
	}
	
	public void cargarCategorias() {
		Categoria catUno = new Categoria(Long.valueOf(1), "abarrotes", "distintos abarrotes");
		Categoria catDos = new Categoria(Long.valueOf(1), "lacteos", "variedad de abarrotes");
		Categoria catTres = new Categoria(Long.valueOf(1), "bebidas", "bebidas sin azucar");
		Categoria catCuatro = new Categoria(Long.valueOf(1), "carnes blancas", "distintas carnes");
		
		list.add(catUno);
		list.add(catDos);
		list.add(catTres);
		list.add(catCuatro);
		
	}
}
