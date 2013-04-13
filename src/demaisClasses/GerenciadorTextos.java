package demaisClasses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import excecoes.InvalidTextException;

public class GerenciadorTextos implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<String> textos;

	public GerenciadorTextos() {
		setTextos(new ArrayList<String>());
	}

	public void salvar(String value) {
		this.textos.add(0, value);
	}

	public int getNumeroTextos() {
		return getTextos().size();
	}

	public List<String> getTextos() {
		return textos;
	}

	public void setTextos(List<String> textos) {
		this.textos = textos;
	}
	
	/**
	 * Retorna as 12 primeiras palavras do texto
	 * 
	 * @param texto
	 * @return String
	 * @throws InvalidTextException 
	 */
	public String get12Palavras(String texto) throws InvalidTextException {
//		if (texto == null || texto.trim().isEmpty())
//			throw new InvalidTextException();
		String[] palavras = texto.split(" ");
		int tamanho = palavras.length, limite = 12;
		String dozePlavras = "";
		for (int i = 0; i < tamanho; i++) {
			if (i >= limite)
				break;
			dozePlavras += palavras[i] + " ";
		}
		return dozePlavras.trim();
	}
}
