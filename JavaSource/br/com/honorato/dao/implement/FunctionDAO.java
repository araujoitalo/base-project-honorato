package br.com.honorato.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.EntityType;

import br.com.honorato.dao.entity.Function;
import br.com.honorato.exception.DAOException;

public class FunctionDAO extends JpaDAO<Function> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public FunctionDAO(EntityManager manager){
		super(manager);
	}
	
	public List<Function> recoveryByCriteria (Function functionFilter) throws DAOException {

		setCriteriaQuery(getCriteriaBuilder().createQuery(Function.class));
		setFromRoot((getCriteriaQuery().from(Function.class)));
		getCriteriaQuery().select(getFromRoot());
		EntityType<Function> type = getEntityManager().getMetamodel().entity(Function.class);
		
		if (functionFilter!=null){

			if (!"".equals(functionFilter.getCode()) && null!=functionFilter.getCode()){
				Predicate loginPredicate = getCriteriaBuilder().equal(getFromRoot().get("code"), functionFilter.getCode());
				getPredicates().add(loginPredicate);
			}

			if (!"".equals(functionFilter.getName()) && null!=functionFilter.getName()){
				Predicate namePredicate = getCriteriaBuilder().like(getCriteriaBuilder().lower(getFromRoot().get(type.getDeclaredSingularAttribute("name", String.class))), functionFilter.getName().toLowerCase() + "%");
				getPredicates().add(namePredicate);
			}
		}
		
		setWhereInQueryWhithPredicatea();
		
		return getTypeQuery().getResultList();			

	}		
	
	public List<Function> selectFreeFunctions () throws DAOException {

		setCriteriaQuery(getCriteriaBuilder().createQuery(Function.class));
		setFromRoot((getCriteriaQuery().from(Function.class)));
		getCriteriaQuery().select(getFromRoot());
		
		Predicate namePredicate = getCriteriaBuilder().isNull(getFromRoot().get("moduleReference"));
		getPredicates().add(namePredicate);
		
		setWhereInQueryWhithPredicatea();
		
		return getTypeQuery().getResultList();			

	}	
	
}