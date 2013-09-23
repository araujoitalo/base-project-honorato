package br.com.honorato.dao.implement;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import br.com.honorato.dao.entity.DYesNo;
import br.com.honorato.dao.util.FilterQuery;
import br.com.honorato.exception.DAOException;

public class DYesNoDAO extends JpaDAO<DYesNo> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public DYesNoDAO(EntityManager manager){
		super(manager);
	}
	
	public DYesNo recoverySingleByCriteria (ArrayList<FilterQuery> filterList) throws DAOException{
		
		return this.recoverySingleByFilter(DYesNo.class, filterList);
		
	}	
}