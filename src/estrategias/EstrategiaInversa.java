package estrategias;

import java.util.Collections;
import java.util.List;

public class EstrategiaInversa extends Estrategia {

	private List<String> linhasInvertidas;

	@Override
	public void setTexto(String textoRecombinado) {
		super.setTexto(textoRecombinado);
		linhasInvertidas = super.getLinhas();
		Collections.reverse(linhasInvertidas);
	}
	
	/**
	 * Retorna a linha da vez caso ainda seja possível
	 */
	@Override
	public String getLinhaRecombinada() {
		if (!linhasInvertidas.isEmpty()) {
			String linha = linhasInvertidas.get(0);
			linhasInvertidas.remove(0);
			return "\n" + linha;
		}
		return null;
	}
}
