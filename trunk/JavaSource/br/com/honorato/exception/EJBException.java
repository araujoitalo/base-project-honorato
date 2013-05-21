package br.com.honorato.exception;


public class EJBException extends BaseException {

	private static final long serialVersionUID = 1L;
	
	public EJBException(String codigo, String mensagen){
		super(codigo, mensagen);
	}
	
	public EJBException(Exception e){	
		super(e);
	}
	
}	