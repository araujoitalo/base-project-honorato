package br.com.honorato.dao.enumeration;

public enum ESampleStatus {

	/*TODO: Pegar do bundle*/
	ACTIVE("Ativo"),
	INACTIVE("Inativo"), 
	BLOCKED("Parte Final");

	private final String label; 

	private ESampleStatus(String label) { 
		this.label = label; 
	} 

	public String getLabel() { 
		return this.label; 
	} 

}
