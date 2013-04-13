package jUnit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import demaisClasses.GerenciadorTextos;
import excecoes.InvalidTextException;

public class GerenciadorTextosTest {

	GerenciadorTextos gerenciador;

	@Before
	public void setUp() {
		gerenciador = new GerenciadorTextos();
	}

	@Test
	public void multiTest() {
		assertEquals(0, gerenciador.getNumeroTextos());
		gerenciador.salvar("texto1");
		assertEquals(1, gerenciador.getNumeroTextos());
		gerenciador.salvar("texto2");
		assertEquals(2, gerenciador.getNumeroTextos());
	}

	@Test
	public void testGetTextos() {
		String texto01 = "texto01", texto02 = "texto02", texto03 = "texto03";
		String[] lista = { texto03, texto02, texto01 };
		gerenciador.salvar(texto01);
		gerenciador.salvar(texto02);
		gerenciador.salvar(texto03);
		assertArrayEquals(lista, gerenciador.getTextos().toArray());
	}

	@Test
	public void testGet12Palavras() {
		// ----------- Situação 01 ------------
		String texto = "";
		texto = intPopulate(50);
		String resultado = "";
		try {
			resultado = gerenciador.get12Palavras(texto);
		} catch (InvalidTextException e) {
			e.printStackTrace();
		}
		String esperado = "1 2 3 4 5 6 7 8 9 10 11 12";
		assertEquals(esperado, resultado);
		
		
		// ----------- Situação 02 ------------
		texto = ""; // vazio
		try {
			resultado = gerenciador.get12Palavras(texto);
			fail(); // deveria lançar exceção 
		} catch (InvalidTextException e) {
			// passa
		} catch (Exception e) {
			e.printStackTrace(); // erro inesperado
		}

		
		// ----------- Situação 03 ------------
		texto = null;
		try {
			resultado = gerenciador.get12Palavras(texto);
			fail(); // deveria lançar exceção 
		} catch (InvalidTextException e) {
			// passa
		} catch (Exception e) {
			e.printStackTrace(); // erro inesperado
		}

		
		// ----------- Situação 04 ------------
		texto = "";
		resultado = "";
		texto = intPopulate(5);// menos de 12 palavras
		try {
			resultado = gerenciador.get12Palavras(texto);
		} catch (Exception e) {
			e.printStackTrace(); // erro inesperado
		}
		esperado = "1 2 3 4 5";
		assertEquals(esperado, resultado);
	}
	
	// --------- AUXILIAR ---------
	private String intPopulate(int numeroDePalavras){
		String texto = "";
		int contador = 1;
		while (contador <= numeroDePalavras) {
			texto += String.valueOf(contador++) + " ";
		}
		return texto.trim();
	}
}
