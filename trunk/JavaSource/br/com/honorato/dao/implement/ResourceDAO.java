package br.com.honorato.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import br.com.honorato.dao.entity.DTypeModule;
import br.com.honorato.dao.entity.Resource;
import br.com.honorato.exception.DAOException;

public class ResourceDAO extends JpaDAO<Resource> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public ResourceDAO(EntityManager manager){
		super(manager);
	}
	
//	public Resource getFullTree(String rootCode){
//		
//		Resource result;
//
//		TypedQuery<Resource> consult = this.getEntityManager().createQuery("from Module m where m.code = :rootCode", this.persistentClass);
//		consult.setParameter("rootCode", rootCode);
//
//		try {
//			
//			result = consult.getSingleResult();
//			
//		} catch (NoResultException e) {
//			
//			result = null;
//			
//		}
//
//		return result;
//	}
	
	public List<Resource> selectBuildTree(){
		
		List<Resource> out = null;
		
		try {

			DTypeModuleDAO systemModuleDAO = new DTypeModuleDAO(this.getEntityManager());
			DTypeModule systemModule;
			
			systemModule = systemModuleDAO.getSystem();

			setCriteriaQuery(getCriteriaBuilder().createQuery(Resource.class));
			setFromRoot(getCriteriaQuery().from(Resource.class));
			getCriteriaQuery().select(getFromRoot());
			Predicate codePredicate = getCriteriaBuilder().equal(getFromRoot().get("IN_TYPE"), systemModule);
			getPredicates().add(codePredicate);
			setWhereInQueryWhithPredicatea();
			
			out  = getTypeQuery().getResultList();
			
		} catch (DAOException e) {
			// TODO erro do bundle
			out = null; 
		}
		
		return out;
	}
	
	public void updateChildrenWithParentToRemove(Resource newResource, Resource oldResource){
		
		setQuery(getEntityManager().createNamedQuery("Resource.updateChildrenWithParentToRemove"));
		setParameters(getQuery(), new Object[]{newResource, oldResource});
		getQuery().executeUpdate();
		
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