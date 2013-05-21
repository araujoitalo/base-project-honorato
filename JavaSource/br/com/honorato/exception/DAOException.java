package br.com.honorato.exception;


public class DAOException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	public DAOException(String codigo, String mensagen){
		super(codigo, mensagen);
	}
	
	public DAOException(Exception e){	
		super(e);
	}
	
}	