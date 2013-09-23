package br.com.honorato.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.honorato.dao.entity.User;
import br.com.honorato.dao.util.EqualFilter;
import br.com.honorato.dao.util.FilterQuery;
import br.com.honorato.exception.DAOException;

public class UserDAO extends JpaDAO<User> {

	private static final long serialVersionUID = 1291101069692295470L;

	public UserDAO(EntityManager manager){
		super(manager);
	}

	public User login(String login) throws DAOException{

		User result;
		User userFilter = new User();
		userFilter.setLogin(login);

		try {
			ArrayList<FilterQuery> filterList = new ArrayList<FilterQuery>();
			filterList.add(new EqualFilter("login",login));

			result =  recoverySingleByCriteria(filterList);
			
		} catch (NoResultException e) {

			result = null;

		}

		return result;
	}

	public User recoverySingleByCriteria (ArrayList<FilterQuery> filterList) throws DAOException{
		
		return this.recoverySingleByFilter(User.class, filterList);
		
	}	

	public List<User> recoveryListByCriteria (ArrayList<FilterQuery> filterList) throws DAOException{
		
		return this.recoveryListByFilter(User.class, filterList);
		
	}	

}