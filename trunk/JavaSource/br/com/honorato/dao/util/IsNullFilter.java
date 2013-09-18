package br.com.honorato.dao.util;

import java.io.Serializable;


public class IsNullFilter extends FilterQuery implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public IsNullFilter(){
		
	}
	
	public IsNullFilter(String name){
		this.setName(name);
	}	

}