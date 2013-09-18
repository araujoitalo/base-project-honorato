package br.com.honorato.dao.util;

import java.io.Serializable;


public class LikeFilter extends FilterQuery implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String INIT = "%#";
	public static final String END = "#%";
	public static final String BOTH = "%#%";
	
	private String typeLike;
	private String fullExpression;
	
	public LikeFilter(){
		
	}

	public LikeFilter(String typeLike, String name, String value){
		setFilterLike(typeLike, name, value);
	}

	public String getValue() {
		return (String)super.getValue();
	}

	public void setValue(String value) {
		super.setValue(value);
	}

	public String getTypeLike() {
		return typeLike;
	}

	public void setTypeLike(String typeLike) {
		this.typeLike = typeLike;
	}

	public void setFilterLike(String typeLike, String name, String value) {
		this.typeLike = typeLike;
		this.setName(name);
		this.setValue(value);
		fullExpression = this.typeLike.replaceAll("#", value); 
	}

	public String getFullExpression() {
		return fullExpression;
	}

	public String getName() {
		return super.getName();
	}

	public void setName(String name) {
		super.setName(name);
	}

}