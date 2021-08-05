package com.apirest.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssertEqualsTeoria {

	@Test
	public void miTest() {
		
		assertEquals(1, 1); // parametros: valor esperado y valor actual
		//assertEquals(1, 2);
	}
}
