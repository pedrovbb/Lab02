package estrategias;

import java.util.ArrayList;
import java.util.List;

public abstract class Estrategia {
	
	private String texto;
	private List<String> linhas;
	private int controleInicioLinha, controleFimLinha;
	
//	protected Estrategia() {
//		setTexto(new String());
//	}

	/**
	 * Método que será sobrescrito nas classes filhas
	 * 
	 * @return String
	 */
	public abstract String getLinhaRecombinada();
	
	public String getTexto() {
		return texto;
	}
	
	public void setTexto(String textoRecombinado) {
		texto = textoRecombinado;
		controleInicioLinha = 0;
		controleFimLinha = 10;
		divideTexto();
	}

	/**
	 * Divide o texto separando suas linhas
	 */
	private void divideTexto() {
		linhas = new ArrayList<String>();
		int aux = getNumLinhas(texto);
		while (aux > 0) {
			linhas.add(get10Palavras(controleInicioLinha, controleFimLinha, texto));
			--aux;
			controleInicioLinha += 10;
			controleFimLinha += 10;
		}
	}

	private int getNumLinhas(String texto) {
		String[] palavras = texto.split(" ");
		return (int) Math.ceil((double) palavras.length / 10);
	}
	
	/**
	 * Retorna uma linha (10 palavras) do texto
	 * 
	 * @param texto
	 * @return String
	 */
	private String get10Palavras(int inicio, int fim, String texto) {
		String[] palavras = texto.split(" ");
		String dezPlavras = "";
		for (int i = inicio; i < palavras.length; i++) {
			if (i == fim)
				break;
			dezPlavras += palavras[i] + " ";
		}
		return dezPlavras.trim();
	}

	public List<String> getLinhas() {
		return linhas;
	}
}
