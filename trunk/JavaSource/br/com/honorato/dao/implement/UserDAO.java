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

	public User lerPorLogin(String login){
		User resultado;

		TypedQuery<User> consulta = this.getEntityManager().createQuery("from User u where u.login = :login", this.persistentClass);
		consulta.setParameter("login", login);

		try {
			
			resultado = consulta.getSingleResult();
			
		} catch (NoResultException e) {
			
			resultado = null;
			
		}

		return resultado;
	}

}
