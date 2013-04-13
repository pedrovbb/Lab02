package jUnit;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import estrategias.Estrategia;
import estrategias.EstrategiaInversa;

public class EstrategiaInversaTest {

	private Estrategia estrategia;

	@Before
	public void setUp() {
		estrategia = new EstrategiaInversa();
	}

	@Test
	public void testGetLinhaRecombinada() {
		String texto = "";
		texto = intPopulate(25); // 3 linhas de texto
		estrategia.setTexto(texto);
		int aux = 3;
		while (aux > 0) {
			String linha = estrategia.getLinhaRecombinada();
			texto += linha;
			--aux;
		}
		
		String esperado = texto;
		List<String> linhasInvertidas = estrategia.getLinhas();
		Collections.reverse(linhasInvertidas);
		esperado = concatenaArrayComString(linhasInvertidas, esperado);

		assertEquals(esperado, texto);
	}


	// --------- AUXILIAR ---------
	private String intPopulate(int numeroDePalavras) {
		String texto = "";
		int contador = 1;
		while (contador <= numeroDePalavras) {
			texto += String.valueOf(contador++) + " ";
		}
		return texto.trim();
	}
	
	private String concatenaArrayComString(List<String> linhasInvertidas,	String esperado) {
		for (String linha : linhasInvertidas) {
			esperado += " " + linha;
		}
		return esperado;
	}
}
