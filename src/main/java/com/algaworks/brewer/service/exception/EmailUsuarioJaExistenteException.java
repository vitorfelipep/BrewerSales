package com.algaworks.brewer.service.exception;

public class EmailUsuarioJaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmailUsuarioJaExistenteException(String message) {
		super(message);
	}
}
