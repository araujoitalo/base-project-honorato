package br.com.honorato.ejb.service;

import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.TypeContact;
import br.com.honorato.dao.implement.TypeContactDAO;
import br.com.honorato.exception.EJBException;

/**
 * Session Bean implementation class TypeContactEJB
 */
@Stateless
public class TypeContactEJB extends BaseEJB {

	public TypeContactEJB() {
	}
	
	public List<TypeContact> typeContatcList() throws EJBException {
		
		return (new TypeContactDAO(getEm()).selectAll());
		
	}	

	public TypeContact getTypeContatcByKey(Integer key) throws EJBException {
		
		return (new TypeContactDAO(getEm()).selectByKey(key));
		
	}	
	
	
}