package br.com.honorato.dao.enumeration;


public enum EModuleType {

	/*TODO: Pegar do bundle*/
	MODULE("Módulo"),
	FUNCTION("Função");

	private final String label; 
	
	private EModuleType(String label) { 
		this.label = label; 
	} 

	public String getLabel() { 
		return this.label; 
	} 

}
