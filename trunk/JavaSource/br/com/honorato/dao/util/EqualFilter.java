package br.com.honorato.dao.util;

import java.io.Serializable;


public class EqualFilter extends FilterQuery implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public EqualFilter(){
		
	}
	
	public EqualFilter(String name, Object value){
		this.setName(name);
		this.setValue(value);
	}	

}