package br.com.honorato.ejb.service.implement;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class LoginEJB
 */
@Stateless
public class BaseEJB {

	@Resource 
	protected SessionContext context;

	@PersistenceContext 
	private EntityManager em;

	public BaseEJB() {
	}

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