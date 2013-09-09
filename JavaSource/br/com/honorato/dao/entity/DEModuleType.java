package br.com.honorato.dao.entity;

import java.io.Serializable;

import br.com.honorato.dao.enumeration.EModuleType;

public class DEModuleType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private EModuleType eModuleType;
	
	public EModuleType geteModuleType() {
		return eModuleType;
	}

	public void seteModuleType(EModuleType eModuleType) {
		this.eModuleType = eModuleType;
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		EModuleType other = (EModuleType) obj;
		
		if (!this.geteModuleType().getCode().equals(other.getCode())){
			return false;
		}
		
		return true;
	}

}