package excecoes;

public class InvalidTextException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidTextException() {
		super("Texto Inválido");
	}
}
