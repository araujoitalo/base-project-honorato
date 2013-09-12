package br.com.honorato.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import br.com.honorato.dao.entity.Resource;
import br.com.honorato.dao.enumeration.EModuleType;

public class ResourceDAO extends JpaDAO<Resource> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public ResourceDAO(EntityManager manager){
		super(manager);
	}
	
	public List<Resource> selectBuildTree(){

		setCriteriaQuery(getCriteriaBuilder().createQuery(Resource.class));
		setFromRoot(getCriteriaQuery().from(Resource.class));
		getCriteriaQuery().select(getFromRoot());
		Predicate codePredicate = getCriteriaBuilder().equal(getFromRoot().get("IN_TYPE"), EModuleType.SYSTEM);
		getPredicates().add(codePredicate);
		setWhereInQueryWhithPredicatea();

		return getTypeQuery().getResultList();

	}
	
	public void updateChildrenWithParentToRemove(Resource newResource, Resource oldResource){
		
		setQuery(getEntityManager().createNamedQuery("Resource.updateChildrenWithParentToRemove"));
		setParameters(getQuery(), new Object[]{newResource, oldResource});
		getQuery().executeUpdate();
		
	}		
}