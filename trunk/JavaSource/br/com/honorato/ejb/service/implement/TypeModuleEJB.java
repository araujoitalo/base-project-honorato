package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.enumeration.EModuleType;
import br.com.honorato.exception.EJBException;

/**
 * Session Bean implementation class TypeContactEJB
 */
@Stateless
public class TypeModuleEJB extends BaseEJB {

	public TypeModuleEJB() {
	}
	
	public EModuleType getTypeModuleByCode(String code) throws EJBException {
		
		for (EModuleType eModuleType : getTypeModules()) {
			if (eModuleType.getCode().equalsIgnoreCase(code)){
				return eModuleType;
			}
		}
		
		return null;
		
	}
	
	public List<EModuleType> getTypeModules() throws EJBException {
		
		return EModuleType.getListValues();
		
	}	
	
}