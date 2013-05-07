package br.com.honorato.dao.implement;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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
	public T lerPorId(Object id) {
		return (T) this.getEntityManager().find(this.persistentClass, id);
	}

	@Override
	public List<T> lerTodos() {
		
		CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> c = cb.createQuery(this.persistentClass);
		c.select(c.from(this.persistentClass));

		List<T> resultado = this.getEntityManager().createQuery(c).getResultList();
		return resultado;
		
	}

	@Override
	public T salvar(T objeto) {

		this.getEntityManager().merge(objeto);
		return objeto;

	}

	@Override
	public void excluir(T objeto) {

		this.getEntityManager().remove(objeto);

	}
}