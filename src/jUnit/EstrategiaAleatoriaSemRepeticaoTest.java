package jUnit;

import org.junit.Before;
import org.junit.Test;

import estrategias.Estrategia;
import estrategias.EstrategiaAleatoriaSemRepeticao;

public class EstrategiaAleatoriaSemRepeticaoTest {

	private Estrategia estrategia;

	@Before
	public void setUp() {
		estrategia = new EstrategiaAleatoriaSemRepeticao();
	}

	@Test
	public void testGetLinhaRecombinada() {
		String texto = "";
		texto = intPopulate(32); // 4 linhas de texto
		estrategia.setTexto(texto);
		int aux = 4;
		while (aux > 0) {
			String linha = estrategia.getLinhaRecombinada();
			System.out.println("linha: " + linha);
			texto += linha;
			--aux;
		}
		System.out.println("Resultado: " + texto);
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
}