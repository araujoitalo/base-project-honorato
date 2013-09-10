package br.com.honorato.dao.enumeration;

import java.util.Arrays;
import java.util.List;


public enum EModuleTypeold  {

	/*TODO: Pegar do bundle*/
	SYSTEM("SYSTEM", "Sistema"),
	MODULE("MODULE", "Modulo"),
	FUNCTION("FUNCTION", "Funcao");

	private final String code; 
	private final String label;
	
	private EModuleTypeold(String code, String label) {
		this.code = code;
		this.label = label; 
	} 

	public String getCode() { 
		return this.code; 
	}
	
	public String getLabel() { 
		return this.label; 
	}

	public static List<EModuleTypeold> getListValues() { 
		return Arrays.asList(EModuleTypeold.values()); 
	}
	
}
