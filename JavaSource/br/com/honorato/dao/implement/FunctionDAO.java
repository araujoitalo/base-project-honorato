package br.com.honorato.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;

import br.com.honorato.dao.entity.Function;
import br.com.honorato.dao.util.FilterQuery;
import br.com.honorato.dao.util.IsNullFilter;
import br.com.honorato.dao.util.JoinFilter;
import br.com.honorato.exception.DAOException;

public class FunctionDAO extends JpaDAO<Function> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public FunctionDAO(EntityManager manager){
		super(manager);
	}
	
	public List<Function> selectFreeFunctions () throws DAOException {

		setCriteriaQuery(getCriteriaBuilder().createQuery(Function.class));
		setFromRoot((getCriteriaQuery().from(Function.class)));
		getCriteriaQuery().select(getFromRoot());
		
		JoinFilter joinFilter = new JoinFilter(JoinType.LEFT,"referencesResources");
		
		joinFilter.getFilterList().add(new IsNullFilter("idModule"));
		
		ArrayList<FilterQuery> filterList = new ArrayList<FilterQuery>();
		filterList.add(joinFilter);
		
		return this.recoveryByFilter(Function.class, filterList);
		
	}
}