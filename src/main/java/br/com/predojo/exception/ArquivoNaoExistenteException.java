package br.com.predojo.exception;

/**
 * Caso arquivo não existe.
 * 
 * @author Vinicius A Gai
 *
 */
public class ArquivoNaoExistenteException extends Exception {

	private static final long serialVersionUID = -1323954377249751180L;

	public ArquivoNaoExistenteException() {
		super();
	}

	public ArquivoNaoExistenteException(String message, Throwable cause) {
		super(message, cause);
	}

	public ArquivoNaoExistenteException(String message) {
		super(message);
	}

}
