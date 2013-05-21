package br.com.honorato.dao.implement;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.honorato.exception.DAOException;

public class JpaDAO<T> implements GenericDAO<T>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Class<T> persistentClass;
	private EntityManager manager;

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
	public void delete(T object) {

		this.getEntityManager().remove(object);

	}
}