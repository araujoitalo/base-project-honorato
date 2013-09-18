package br.com.honorato.dao.util;

import java.io.Serializable;

public class FilterQuery implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String ISNULL = "ISNULL";
	
	private Object value;
	private String name;
	
	public FilterQuery(){
		
	}

	public FilterQuery(String name, Object value){
		this.name = name;
		this.value = value;
	}
	
	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}