package br.com.honorato.exception;


public class EncriptException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	public EncriptException(String codigo, String mensagen){
		super(codigo, mensagen);
	}
	
	public EncriptException(Exception e){	
		super(e);
	}
	
}	