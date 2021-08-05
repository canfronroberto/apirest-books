package com.apirest.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEquals {

	@Test
	public void pruebaArrayIguales() {
		String [] array1 = {"a", "b"};
		String [] array2 = {"a", "b"};
		String [] array3 = {"a", "b", "c"};
		
		assertArrayEquals(array1, array2);
		assertArrayEquals(array1, array3);
		assertArrayEquals(array2, array3);
	}
}
