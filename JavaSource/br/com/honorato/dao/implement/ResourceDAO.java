package br.com.honorato.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import br.com.honorato.dao.entity.Function;
import br.com.honorato.dao.entity.Module;
import br.com.honorato.dao.entity.Node;
import br.com.honorato.dao.entity.Resource;

public class ResourceDAO extends JpaDAO<Resource> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public ResourceDAO(EntityManager manager){
		super(manager);
	}
	
	public Resource getFullTree(String rootCode){
		
		Resource result;

		TypedQuery<Resource> consult = this.getEntityManager().createQuery("from Module m where m.code = :rootCode", this.persistentClass);
		consult.setParameter("rootCode", rootCode);

		try {
			
			result = consult.getSingleResult();
			
		} catch (NoResultException e) {
			
			result = null;
			
		}

		return result;
	}
	
	public List<Resource> selectBuildTree(){
		
		setQuery(getCriteriaBuilder().createQuery(Resource.class));
		setFromRoot(getQuery().from(Resource.class));
		getQuery().select(getFromRoot());
		
		Predicate codePredicate = getCriteriaBuilder().isNull(getFromRoot().get("moduleReference"));
		getPredicates().add(codePredicate);
		setWhereInQueryWhithPredicatea();

		return getTypeQuery().getResultList();
	}

//	public List<Module> selectAllModules(){
//		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
//		CriteriaQuery<Module> c = cb.createQuery(Module.class);
//		c.select(c.from(Module.class));
//		
//		return this.getEntityManager().createQuery(c).getResultList();
//	}
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