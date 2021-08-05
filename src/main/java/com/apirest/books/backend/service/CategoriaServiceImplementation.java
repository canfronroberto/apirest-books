package com.apirest.books.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apirest.books.backend.model.Categoria;
import com.apirest.books.backend.model.dao.ICategoriaDao;
import com.apirest.books.backend.response.CategoriaResponseRest;

@Service
public class CategoriaServiceImplementation implements ICategoriaService{

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImplementation.class);
	
	@Autowired
	private ICategoriaDao categoriaDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
		
		log.info("inicio del metodo buscarCategorias()");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			
			List<Categoria> categoria = (List<Categoria>) categoriaDao.findAll();
			
			response.getCategoriaResponse().setCategoria(categoria);
			
			response.setMetadata("respuesta ok", "200", "respuesta exitosa");
			
		} catch (Exception e) {
			response.setMetadata("error", "500", "error al consultar categorias");
			log.error("error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // devuelve 500
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<CategoriaResponseRest> buscarPorId(Long id) {
		
		log.info("inicio metodo buscarPorId()");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Optional<Categoria> categoria = categoriaDao.findById(id);
			
			if(categoria.isPresent()) {
				list.add(categoria.get());
				response.getCategoriaResponse().setCategoria(list);
			} else {
				log.error("error al consultar categoria");
				response.setMetadata("Respuesta incorrecta", "400", "categoria no encontrada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("error al consultar categoria");
			response.setMetadata("Respuesta incorrecta", "500", "error al consultar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("respuesta ok", "200", "respuesta exitosa");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> crearCategoria(Categoria categoria) {
		log.info("inicio metodo crearCategoria()");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		List<Categoria> list = new ArrayList<>();
		
		try {
			
			Categoria categoriaGuardada = categoriaDao.save(categoria);
			
			if(categoriaGuardada!=null) {
				list.add(categoriaGuardada);
				response.getCategoriaResponse().setCategoria(list);
			} else {
				log.error("error al guardar la categoria");
				response.setMetadata("Respuesta incorrecta", "400", "categoria no guardada");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			log.error("error al guardar categoria");
			response.setMetadata("Respuesta incorrecta", "500", "error al guardar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("respuesta ok", "200", "categoria creada");
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> actualizarCategoria(Categoria categoria, Long id) {
		
		log.info("inicio metodo actualizarCategoria()");
		
		CategoriaResponseRest response = new CategoriaResponseRest();
		List<Categoria> list = new ArrayList<>();
		
		try {
			Optional<Categoria> categoriaBuscada = categoriaDao.findById(id);
			
			if(categoriaBuscada.isPresent()) {
				categoriaBuscada.get().setNombre(categoria.getNombre());
				categoriaBuscada.get().setDescripcion(categoria.getDescripcion());
				
				Categoria categoriaActualizar = categoriaDao.save(categoriaBuscada.get()); // actualizando categoria
				
				if(categoriaActualizar != null) {
					response.setMetadata("respuesta ok", "200", "categoria actualizada");
					list.add(categoriaActualizar);
					response.getCategoriaResponse().setCategoria(list);
				} else {
					log.error("error al actualizar categoria");
					response.setMetadata("Respuesta incorrecta", "400", "error al actualizar categoria");
					return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				log.error("error al actualizar categoria");
				response.setMetadata("Respuesta incorrecta", "400", "error al actualizar categoria");
				return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("error al actualizar categoria", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta incorrecta", "500", "error al actualizar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<CategoriaResponseRest> eliminarCategoria(Long id) {
		
		log.info("inicio metodo eliminarCategoria()");
		CategoriaResponseRest response = new CategoriaResponseRest();
		
		try {
			
			// eliminar un registro
			categoriaDao.deleteById(id);
			response.setMetadata("respuesta ok", "200", "categoria eliminada");
		} catch (Exception e) {
			log.error("error al eliminar categoria", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta incorrecta", "500", "error al eliminar categoria");
			return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<CategoriaResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

}
