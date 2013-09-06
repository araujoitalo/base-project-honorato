package br.com.honorato.dao.implement;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.honorato.exception.DAOException;

public class JpaDAO<T> implements GenericDAO<T>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Class<T> persistentClass;
	private EntityManager manager;
	private ArrayList<Predicate> predicates;
	private CriteriaQuery<T> criteriaQuery;
	private Root<T> fromRoot;
	private Query query;

	@SuppressWarnings("unchecked")
	public JpaDAO(EntityManager manager) {

		Type superClasse = getClass().getGenericSuperclass();
		Type tipo = ((ParameterizedType) superClasse).getActualTypeArguments()[0];
		this.persistentClass = (Class<T>) tipo;
		this.manager = manager;

	}

	@Override
	public EntityManager getEntityManager()	{

		return this.manager;

	}


	@Override
	public T selectByKey(Object id) {
		return (T) this.getEntityManager().find(this.persistentClass, id);
	}

	@Override
	public List<T> selectAll() {

		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> c = cb.createQuery(this.persistentClass);
		c.select(c.from(this.persistentClass));
		List<T> result = this.getEntityManager().createQuery(c).getResultList();
		return result;

	}

	@Override
	public T save(T object) throws DAOException {

		try {

			this.getEntityManager().merge(object);
			return object;

		}catch(PersistenceException ex){
			throw new DAOException("",ex.getMessage());
		}

	}

	@Override
	public void delete(T object) throws DAOException {
		
		try {

			this.getEntityManager().remove(object);

		}catch(PersistenceException ex){
			throw new DAOException("",ex.getMessage());
		}

	}

	public CriteriaBuilder getCriteriaBuilder() {
		return this.getEntityManager().getCriteriaBuilder();
	}

	public ArrayList<Predicate> getPredicates() {
		
		if (predicates==null)
			predicates = new ArrayList<Predicate>();
		return predicates;
	}

	public void setPredicates(ArrayList<Predicate> predicates) {
		this.predicates = predicates;
	}

	public CriteriaQuery<T> getCriteriaQuery() {
		return criteriaQuery;
	}

	public void setCriteriaQuery(CriteriaQuery<T> query) {
		this.criteriaQuery = query;
	}

	public Root<T> getFromRoot() {
		return fromRoot;
	}

	public void setFromRoot(Root<T> fromRoot) {
		this.fromRoot = fromRoot;
	}
	
	public TypedQuery<T> getTypeQuery(){
		 return this.getEntityManager().createQuery(getCriteriaQuery());
	}
	
	public void setWhereInQueryWhithPredicatea(){
		
		if (getPredicates().size() > 0){
			Predicate[] params = new Predicate[getPredicates().size()];
			getCriteriaQuery().where(getPredicates().toArray(params));
		}		

	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public final void setParameters(Query query, Object[] args) {

		if ((args != null) && (query != null)) {	
			for (int i = 0; i < args.length; i++) {	
				Object arg = args[i];
				query.setParameter(i+1, arg);
			}
		}
	}	
}