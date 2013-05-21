package br.com.honorato.dao.implement;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.honorato.dao.entity.User;

public class UserDAO extends JpaDAO<User> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public UserDAO(EntityManager manager){
		super(manager);
	}
	
	public User login(String login){
		
		User result;

		TypedQuery<User> consult = this.getEntityManager().createQuery("from User u where u.login = :login", this.persistentClass);
		consult.setParameter("login", login);

		try {
			
			result = consult.getSingleResult();
			
		} catch (NoResultException e) {
			
			result = null;
			
		}

		return result;
	}

}