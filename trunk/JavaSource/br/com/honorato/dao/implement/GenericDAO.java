package br.com.honorato.dao.implement;

import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDAO<T> {
	
	public EntityManager getEntityManager();
	public T lerPorId(Object id);
	public List<T> lerTodos();
	public T salvar(T objeto);
	public void excluir(T objeto);

}