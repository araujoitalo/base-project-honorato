package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.SampleType;
import br.com.honorato.dao.implement.SampleTypeDAO;

/**
 * Session Bean implementation class SampleEJB
 */
@Stateless
public class SampleTypeEJB extends BaseEJB {

	public SampleTypeEJB() {
	}
	
	public List<SampleType> selectAllSampeType(){
		
		return new SampleTypeDAO(getEm()).selectAll();
		
	}
	
}