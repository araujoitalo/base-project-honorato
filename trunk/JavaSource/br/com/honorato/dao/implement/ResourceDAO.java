package br.com.honorato.dao.implement;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

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
}