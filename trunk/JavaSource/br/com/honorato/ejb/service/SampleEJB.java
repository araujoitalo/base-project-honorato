package br.com.honorato.ejb.service;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.Sample;
import br.com.honorato.dao.implement.SampleDAO;
import br.com.honorato.exception.DAOException;
import br.com.honorato.exception.EJBException;

/**
 * Session Bean implementation class SampleEJB
 */
@Stateless
public class SampleEJB extends BaseEJB {

	public SampleEJB() {
	}
	
	public void saveSample(Sample newSample) throws EJBException {
		
		try {
			new SampleDAO(getEm()).save(newSample);
		} catch (DAOException e) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}
		
	}
	
}