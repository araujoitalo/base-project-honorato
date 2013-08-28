package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.DTypeContact;
import br.com.honorato.dao.implement.TypeContactDAO;
import br.com.honorato.exception.EJBException;

/**
 * Session Bean implementation class TypeContactEJB
 */
@Stateless
public class TypeContactEJB extends BaseEJB {

	public TypeContactEJB() {
	}
	
	public List<DTypeContact> typeContatcList() throws EJBException {
		
		return (new TypeContactDAO(getEm()).selectAll());
		
	}	

	public DTypeContact getTypeContatcByKey(Integer key) throws EJBException {
		
		return (new TypeContactDAO(getEm()).selectByKey(key));
		
	}	
	
	
}