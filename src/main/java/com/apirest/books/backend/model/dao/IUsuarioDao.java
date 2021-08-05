package com.apirest.books.backend.model.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.apirest.books.backend.model.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	public Usuario findByNombreUsuario(String nombreUsuario);
	
	@Query("select u from Usuario u where u.nombreUsuario = ?1") // con ?1 le pasamos el  campo nombreUsuario
	public Usuario findByIdNombreUsuarioV2(String nombreUsuario);
}
