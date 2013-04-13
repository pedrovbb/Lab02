package estrategias;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EstrategiaAleatoriaSemRepeticao extends Estrategia {

private List<String> linhasEmbaralhadas, linhasUsadas;
	
	@Override
	public void setTexto(String textoRecombinado) {
		super.setTexto(textoRecombinado);
		linhasEmbaralhadas = super.getLinhas();
		linhasUsadas = new ArrayList<String>();
	}
	
	@Override
	public String getLinhaRecombinada() {
		if (!linhasEmbaralhadas.isEmpty()) {
			Collections.shuffle(linhasEmbaralhadas);
			String linha = linhasEmbaralhadas.get(0);
			while (linhasUsadas.contains(linha)) {
				Collections.shuffle(linhasEmbaralhadas);

				System.out.println("linhas usadas: " + linhasUsadas);
				System.out.println("linhas restantes: " + linhasEmbaralhadas);
				
				linha = linhasEmbaralhadas.get(0);
			}
			linhasUsadas.add(linha);
			linhasEmbaralhadas.remove(0);
			return "\n" + linha;
		}
		return null;
	}
}
