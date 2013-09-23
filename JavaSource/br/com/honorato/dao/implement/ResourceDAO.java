package br.com.honorato.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.honorato.dao.entity.Resource;
import br.com.honorato.dao.enumeration.EModuleType;
import br.com.honorato.dao.util.EqualFilter;
import br.com.honorato.dao.util.FilterQuery;
import br.com.honorato.exception.DAOException;

public class ResourceDAO extends JpaDAO<Resource> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public ResourceDAO(EntityManager manager){
		super(manager);
	}
	
	public List<Resource> selectBuildTree() throws DAOException{
		
		ArrayList<FilterQuery> filterList = new ArrayList<FilterQuery>();
		filterList.add(new EqualFilter("IN_TYPE",EModuleType.SYSTEM));
		return recoveryListByFilter(Resource.class, filterList);

	}
	
	public List<Resource> checkAvailabilityCode(ArrayList<FilterQuery> filters) throws DAOException{
		
		return this.recoveryListByFilter(Resource.class, filters);
		
	}	
}