package estrategias;

import java.util.Collections;
import java.util.List;

public class EstrategiaAleatoriaComRepeticao extends Estrategia {

	private List<String> linhasEmbaralhadas;
	
	@Override
	public void setTexto(String textoRecombinado) {
		super.setTexto(textoRecombinado);
		linhasEmbaralhadas = super.getLinhas();
	}
	
	@Override
	public String getLinhaRecombinada() {
		Collections.shuffle(linhasEmbaralhadas);
		String linha = linhasEmbaralhadas.get(0); 
		return "\n" + linha;
	}
}
