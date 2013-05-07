package br.com.honorato.ejb.service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.honorato.dao.implement.UserDAO;
import br.com.honorato.dao.model.User;

/**
 * Session Bean implementation class TesteEJB
 */
@Stateless
public class TesteEJB {

	/**
	 * Default constructor. 
	 */
	public TesteEJB() {
		// TODO Auto-generated constructor stub
		System.out.println("TEste");
	}

	public void escreve(String oque){
		System.out.println(oque);
		UserDAO userDao = new UserDAO(em);
		List<User> list = userDao.lerTodos();
		System.out.println(list.size());
	}
	
	@Resource 
	protected SessionContext context;

	@PersistenceContext 
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public SessionContext getContext() {
		return context;
	}

	public void setContext(SessionContext context) {
		this.context = context;
	}

	public void refresh(Object obj){
		getEm().refresh(obj);
	}	

}
