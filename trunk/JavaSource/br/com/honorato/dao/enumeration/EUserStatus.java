package br.com.honorato.dao.enumeration;


public enum EUserStatus {

	/*TODO: Pegar do bundle*/
	ACTIVE("Ativo"),
	INACTIVE("Inativo"), 
	BLOCKED("Bloqueado");

	private final String label; 
	
	private EUserStatus(String label) { 
		this.label = label; 
	} 

	public String getLabel() { 
		return this.label; 
	} 

}
