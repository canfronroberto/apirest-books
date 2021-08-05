package com.apirest.books.backend.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer // con esta anotacion sobreescribimos la configuracion base
public class ConfiguracionRecursos extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/v1/categorias").permitAll() // aqui vamos autorizar el recurso a los clientes para que puedan acceder libremente sin el token de autorizacion
		.anyRequest().authenticated(); // para los demas recursos deben estar autenticados
	}
	
}
