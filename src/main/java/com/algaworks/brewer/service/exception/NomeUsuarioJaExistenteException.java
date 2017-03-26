package com.algaworks.brewer.service.exception;

public class NomeUsuarioJaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NomeUsuarioJaExistenteException(String message) {
		super(message);
	}
}
