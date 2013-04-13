package jUnit;

import org.junit.Before;
import org.junit.Test;

import estrategias.Estrategia;
import estrategias.EstrategiaAleatoriaComRepeticao;

public class EstrategiaAleatoriaComRepeticaoTest {

	private Estrategia estrategia;

	@Before
	public void setUp() {
		estrategia = new EstrategiaAleatoriaComRepeticao();
	}

	@Test
	public void testGetLinhaRecombinada() {
		String texto = "";
		texto = intPopulate(19); // 2 linhas de texto
		estrategia.setTexto(texto);
		int aux = 3;
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
