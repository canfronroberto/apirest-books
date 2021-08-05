package com.apirest.books.backend.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
	
	Calculadora calc;

	@BeforeAll
	public static void primero() {
		System.out.println("primero");
	}
	
	@AfterAll
	public static void ultimo() {
		System.out.println("ultimo");
	}
	
	@BeforeEach // este metodo se ejecuta antes de una prueba unitaria
	public void instanciaObjecto() {
		calc = new Calculadora();
		System.out.println("@BeforeEach");
	}
	
	@AfterEach // este metodo se ejecuta despues de una prueba unitaria
	public void despuesTest() {
		System.out.println("@AfterEach");
	}
	
	@Test
	@DisplayName("prueba unitaria assertEqual") // esta anotacion es para darle descripcion a la prueba unitaria
	@Disabled("esta prueba no se ejecutara") // esta anotacion permite deshabilitar la prueba unitaria
	public void calculadoraAssertEqualsTest() {
		
		assertEquals(2, calc.sumar(1, 1));
		assertEquals(5, calc.restar(7, 2));
		assertEquals(10, calc.multiplicar(2, 5));
		assertEquals(2, calc.dividir(4, 2));
		
		System.out.println("calculadoraAssertEqualsTest");
	}
	
	@Test
	public void calculadoraTrueFalse() {
		
		assertTrue(calc.sumar(1, 1) == 2);
		assertTrue(calc.restar(2, 1) == 1);
		assertFalse(calc.multiplicar(5, 2) == 13);
		assertFalse(calc.dividir(4, 3) == 2);
		
		System.out.println("calculadoraTrueFalse");
	}
}
