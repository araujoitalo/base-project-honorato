package br.com.honorato.dao.implement;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.honorato.dao.entity.Module;

public class ModuleDAO extends JpaDAO<Module> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public ModuleDAO(EntityManager manager){
		super(manager);
	}
	
	public Module getFullTree(String rootCode){
		
		Module result;

		TypedQuery<Module> consult = this.getEntityManager().createQuery("from Module m where m.code = :rootCode", this.persistentClass);
		consult.setParameter("rootCode", rootCode);

		try {
			
			result = consult.getSingleResult();
			
		} catch (NoResultException e) {
			
			result = null;
			
		}

		return result;
	}
}