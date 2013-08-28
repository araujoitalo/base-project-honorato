package br.com.honorato.dao.implement;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import br.com.honorato.dao.entity.DYesNo;

public class DYesNoDAO extends JpaDAO<DYesNo> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public DYesNoDAO(EntityManager manager){
		super(manager);
	}
	
	public DYesNo recoverySingleByCriteria (DYesNo DYesNoFilter){

		setQuery(getCriteriaBuilder().createQuery(DYesNo.class));
		setFromRoot(getQuery().from(DYesNo.class));
		getQuery().select(getFromRoot());

		if (!"".equals(DYesNoFilter.getCode())){
			Predicate codePredicate = getCriteriaBuilder().equal(getFromRoot().get("code"), DYesNoFilter.getCode());
			getPredicates().add(codePredicate);
		}

		setWhereInQueryWhithPredicatea();

		return getTypeQuery().getSingleResult();

	}	
}