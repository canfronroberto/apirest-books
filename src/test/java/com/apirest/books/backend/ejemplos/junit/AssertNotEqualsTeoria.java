package com.apirest.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AssertNotEqualsTeoria {

	@Test
	public void miTest() {
		assertNotEquals(3, 2); // parametros: valor esperado y valor actual
	}
}
