package jUnit;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import estrategias.Estrategia;

public class EstrategiaTest {
	
	private Estrategia estrategia;
	
	@Before
	public void setUp(){
		estrategia = new Estrategia() {
			
			@Override
			public String getLinhaRecombinada() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Test
	public void testGetLinhas() {
		estrategia.setTexto("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25");
		String[] linhasDivididas = converteArray(estrategia.getLinhas());
		String[] linhasEsperadas = {"1 2 3 4 5 6 7 8 9 10", "11 12 13 14 15 16 17 18 19 20", "21 22 23 24 25"};
		
		//imprimeArray(linhasDivididas);
		//imprimeArray(linhasEsperadas);
		
		assertArrayEquals(linhasEsperadas, linhasDivididas);
		
	}

	/**
	 * Apenas para testes
	 * @param array
	 */
	@SuppressWarnings("unused")
	private void imprimeArray(String[] array) {
		String result = "";
		for (int i = 0; i < array.length; i++) {
			result += array[i] + " ";
		}
		System.out.println(result.trim());
	}

	private String[] converteArray(List<String> lista) {
		String[] linhas = new String[lista.size()];
		for (int i = 0; i < linhas.length; i++) {
			linhas[i] = lista.get(i);
		}
		return linhas;
	}

}
