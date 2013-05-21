package br.com.honorato.exception;

import java.util.List;

public abstract class BaseException extends Exception {

	private final static long serialVersionUID = 1;

	private String errorCode;
	private List<String> messages;

	public BaseException(){
	}

	public BaseException(String msg){	
		super(msg);
	}

	public BaseException(List<String> mensagens){	
		this.messages = mensagens;
	}

	public BaseException(String code, String msg){	
		super(msg);
		this.errorCode = code;
	}

	public BaseException(Exception e){	
		super(e);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}	