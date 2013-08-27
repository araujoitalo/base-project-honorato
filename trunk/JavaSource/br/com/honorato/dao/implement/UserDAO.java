package br.com.honorato.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.EntityType;

import br.com.honorato.dao.entity.User;
import br.com.honorato.exception.DAOException;

public class UserDAO extends JpaDAO<User> {

	private static final long serialVersionUID = 1291101069692295470L;

	public UserDAO(EntityManager manager){
		super(manager);
	}

	public User login(String login){

		User result;
		User userFilter = new User();
		userFilter.setLogin(login);

		try {

			result =  recoverySingleByCriteria (userFilter);

		} catch (NoResultException e) {

			result = null;

		}

		return result;
	}

	public User recoverySingleByCriteria (User userFilter){

		setQuery(getCriteriaBuilder().createQuery(User.class));
		setFromRoot(getQuery().from(User.class));
		getQuery().select(getFromRoot());

		if (!"".equals(userFilter.getLogin())){
			Predicate loginPredicate = getCriteriaBuilder().equal(getFromRoot().get("login"), userFilter.getLogin());
			getPredicates().add(loginPredicate);
		}

		setWhereInQueryWhithPredicatea();

		return getTypeQuery().getSingleResult();

	}

	public List<User> recoveryByCriteria (User userFilter) throws DAOException {

		setQuery(getCriteriaBuilder().createQuery(User.class));
		setFromRoot(getQuery().from(User.class));
		getQuery().select(getFromRoot());
		EntityType<User> type = getEntityManager().getMetamodel().entity(User.class);

		if (userFilter==null){

			/*TODO: recuperar do bundle*/
			throw new DAOException("CODIGO","filro de usuário não informado");
			
		} else {

			if (!"".equals(userFilter.getLogin()) && null!=userFilter.getLogin()){
				Predicate loginPredicate = getCriteriaBuilder().equal(getFromRoot().get("login"), userFilter.getLogin());
				getPredicates().add(loginPredicate);
			}

			if (!"".equals(userFilter.getName()) && null!=userFilter.getName()){
				Predicate namePredicate = getCriteriaBuilder().like(getCriteriaBuilder().lower(getFromRoot().get(type.getDeclaredSingularAttribute("name", String.class))), userFilter.getName().toLowerCase() + "%");
				getPredicates().add(namePredicate);
			}

			if (userFilter.getStatus()!=null){
				Predicate statusPredicate = getCriteriaBuilder().equal(getFromRoot().get("status"), userFilter.getStatus());
				getPredicates().add(statusPredicate);
			}

			setWhereInQueryWhithPredicatea();

			return getTypeQuery().getResultList();			
		}

	}	


}