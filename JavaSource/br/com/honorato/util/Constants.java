package br.com.honorato.util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "constants")
@RequestScoped
public class Constants implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String YES = "YES";
	public static final String NO = "NO";
	
	public Constants(){
	}

	public static String getYes() {
		return YES;
	}

	public static String getNo() {
		return NO;
	}

}
