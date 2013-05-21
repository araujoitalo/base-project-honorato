package br.com.honorato.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.honorato.exception.DAOException;

public interface GenericDAO<T> {
	
	public EntityManager getEntityManager();
	public T selectByKey(Object id);
	public List<T> selectAll();
	public T save(T objeto) throws DAOException;
	public void delete(T objeto);

}