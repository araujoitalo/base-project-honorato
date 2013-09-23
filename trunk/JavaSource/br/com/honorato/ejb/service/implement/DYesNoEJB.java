package br.com.honorato.ejb.service.implement;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.DYesNo;
import br.com.honorato.dao.implement.DYesNoDAO;
import br.com.honorato.dao.util.EqualFilter;
import br.com.honorato.dao.util.FilterQuery;
import br.com.honorato.exception.DAOException;
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
		
		ArrayList<FilterQuery> filterList = new ArrayList<FilterQuery>();
		filterList.add(new EqualFilter("code",code));
		
		try {
			return (new DYesNoDAO(getEm()).recoverySingleByCriteria(filterList));
		} catch (DAOException e) {
			//TODO: BUNDLE
			throw new EJBException("","Erro ao tentar selecionar Yes ou NO");
		}
		
	}	
	
	public DYesNo getNo() throws EJBException {
		
		return getDYesNoByCode(Constants.NO);
		
	}	

	public DYesNo getYes() throws EJBException {
		
		return getDYesNoByCode(Constants.YES);
		
	}	
}