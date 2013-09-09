package br.com.honorato.dao.enumeration;

import java.util.Arrays;
import java.util.List;


public enum EModuleType  {

	/*TODO: Pegar do bundle*/
	SYSTEM("SYSTEM", "Sistema"),
	MODULE("MODULE", "Modulo"),
	FUNCTION("FUNCTION", "Funcao");

	private final String code; 
	private final String label;
	
	private EModuleType(String code, String label) {
		this.code = code;
		this.label = label; 
	} 

	public String getCode() { 
		return this.code; 
	}
	
	public String getLabel() { 
		return this.label; 
	}

	public static List<EModuleType> getListValues() { 
		return Arrays.asList(EModuleType.values()); 
	}
	
}
