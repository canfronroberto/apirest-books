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

import com.apirest.books.backend.model.Libro;
import com.apirest.books.backend.model.dao.ILibroDao;
import com.apirest.books.backend.response.LibroResponseRest;

@Service
public class LibroServiceImplementation implements ILibroService{

	private static final Logger log = LoggerFactory.getLogger(LibroServiceImplementation.class);
	
	@Autowired
	private ILibroDao libroDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> buscarLibros() {
		
		log.info("inicio del metodo buscarLibros()");
		
		LibroResponseRest response = new LibroResponseRest();
		
		try {
			
			List<Libro> libro = (List<Libro>) libroDao.findAll();
			
			response.getLibroResponse().setLibro(libro);
			
			response.setMetadata("respuesta ok", "200", "respuesta exitosa");
			
		} catch (Exception e) {
			response.setMetadata("error", "500", "error al consultar libros");
			log.error("error al consultar categorias: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR); // devuelve 500
		}
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<LibroResponseRest> buscarPorId(Long id) {
		
		log.info("inicio metodo buscarPorId()");
		
		LibroResponseRest response = new LibroResponseRest();
		
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Optional<Libro> libro = libroDao.findById(id);
			
			if(libro.isPresent()) {
				list.add(libro.get());
				response.getLibroResponse().setLibro(list);
			} else {
				log.error("error al consultar libro");
				response.setMetadata("Respuesta incorrecta", "400", "libro no encontrado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
			
		} catch (Exception e) {
			log.error("error al consultar libro");
			response.setMetadata("Respuesta incorrecta", "500", "error al consultar libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("respuesta ok", "200", "respuesta exitosa");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> crearLibro(Libro libro) {
		log.info("inicio metodo crearLibro()");
		
		LibroResponseRest response = new LibroResponseRest();
		
		List<Libro> list = new ArrayList<>();
		
		try {
			
			Libro libroGuardado = libroDao.save(libro);
			
			if(libroGuardado!=null) {
				list.add(libroGuardado);
				response.getLibroResponse().setLibro(list);
			} else {
				log.error("error al guardar el libro");
				response.setMetadata("Respuesta incorrecta", "400", "libro no guardado");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			log.error("error al guardar el libro");
			response.setMetadata("Respuesta incorrecta", "500", "error al guardar el libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetadata("respuesta ok", "200", "libro creado");
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> actualizarLibro(Libro libro, Long id) {
		
		log.info("inicio metodo actualizarLibro()");
		
		LibroResponseRest response = new LibroResponseRest();
		List<Libro> list = new ArrayList<>();
		
		try {
			Optional<Libro> libroBuscado = libroDao.findById(id);
			
			if(libroBuscado.isPresent()) {
				libroBuscado.get().setNombre(libro.getNombre());
				libroBuscado.get().setDescripcion(libro.getDescripcion());
				libroBuscado.get().setCategoria(libro.getCategoria());
				
				Libro libroActualizar = libroDao.save(libroBuscado.get()); // actualizando libro
				
				if(libroActualizar != null) {
					response.setMetadata("respuesta ok", "200", "libro actualizado");
					list.add(libroActualizar);
					response.getLibroResponse().setLibro(list);
				} else {
					log.error("error al actualizar el libro");
					response.setMetadata("Respuesta incorrecta", "400", "error al actualizar el libro");
					return new ResponseEntity<LibroResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				log.error("error al actualizar el libro");
				response.setMetadata("Respuesta incorrecta", "400", "error al actualizar el libro");
				return new ResponseEntity<LibroResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("error al actualizar el libro", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta incorrecta", "500", "error al actualizar el libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

	@Override
	@Transactional
	public ResponseEntity<LibroResponseRest> eliminarLibro(Long id) {
		
		log.info("inicio metodo eliminarLibro()");
		LibroResponseRest response = new LibroResponseRest();
		
		try {
			
			// eliminar un registro
			libroDao.deleteById(id);
			response.setMetadata("respuesta ok", "200", "libro eliminado");
		} catch (Exception e) {
			log.error("error al eliminar el libro", e.getMessage());
			e.getStackTrace();
			response.setMetadata("Respuesta incorrecta", "500", "error al eliminar el libro");
			return new ResponseEntity<LibroResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<LibroResponseRest>(response, HttpStatus.OK); // devuelve 200
	}

}
