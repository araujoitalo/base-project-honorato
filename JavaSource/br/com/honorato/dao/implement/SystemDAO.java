package br.com.honorato.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.EntityType;

import br.com.honorato.dao.entity.SystemModule;
import br.com.honorato.exception.DAOException;

public class SystemDAO extends JpaDAO<SystemModule> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public SystemDAO(EntityManager manager){
		super(manager);
	}
	
	public List<SystemModule> recoveryByCriteria (SystemModule systemFilter) throws DAOException {

		setCriteriaQuery(getCriteriaBuilder().createQuery(SystemModule.class));
		setFromRoot(getCriteriaQuery().from(SystemModule.class));
		getCriteriaQuery().select(getFromRoot());
		EntityType<SystemModule> type = getEntityManager().getMetamodel().entity(SystemModule.class);

		if (systemFilter!=null){

			if (!"".equals(systemFilter.getCode()) && null!=systemFilter.getCode()){
				Predicate loginPredicate = getCriteriaBuilder().equal(getFromRoot().get("code"), systemFilter.getCode());
				getPredicates().add(loginPredicate);
			}

			if (!"".equals(systemFilter.getName()) && null!=systemFilter.getName()){
				Predicate namePredicate = getCriteriaBuilder().like(getCriteriaBuilder().lower(getFromRoot().get(type.getDeclaredSingularAttribute("name", String.class))), systemFilter.getName().toLowerCase() + "%");
				getPredicates().add(namePredicate);
			}

			setWhereInQueryWhithPredicatea();
		}
		
		return getTypeQuery().getResultList();			

	}		
	
	public List<SystemModule> selectSystemTree() throws DAOException{
		
		return recoveryByCriteria(null);
		
	}
//
//	public List<Node> selectAllNodes(){
//		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
//		CriteriaQuery<Node> c = cb.createQuery(Node.class);
//		c.select(c.from(Node.class));
//		
//		return this.getEntityManager().createQuery(c).getResultList();
//	}
//
//	public List<Function> selectAllFunctions(){
//		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
//		CriteriaQuery<Function> c = cb.createQuery(Function.class);
//		c.select(c.from(Function.class));
//		
//		return this.getEntityManager().createQuery(c).getResultList();
//	}
//
	
}