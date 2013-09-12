package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.DYesNo;
import br.com.honorato.dao.implement.DYesNoDAO;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.Constants;

/**
 * Session Bean implementation class TypeContactEJB
 */
@Stateless
public class DYesNoEJB extends BaseEJB {

	public DYesNoEJB() {
	}
	
	public List<DYesNo> dYesNoList() throws EJBException {
		
		return (new DYesNoDAO(getEm()).selectAll());
		
	}	

	public DYesNo getDYesNoByKey(Integer key) throws EJBException {
		
		return (new DYesNoDAO(getEm()).selectByKey(key));
		
	}	
	
	public DYesNo getDYesNoByCode(String code) throws EJBException {
		
		DYesNo filter = new DYesNo();
		filter.setCode(code);
		return (new DYesNoDAO(getEm()).recoverySingleByCriteria(filter));
		
	}	
	
	public DYesNo getNo() throws EJBException {
		
		return getDYesNoByCode(Constants.NO);
		
	}	

	public DYesNo getYes() {
		
		try {

			return getDYesNoByCode(Constants.YES);
			
		} catch (EJBException e) {
			// TODO logar erro
			return null;
		}
		
	}	
}