package br.com.honorato.ejb.service;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.User;
import br.com.honorato.dao.enumeration.EUserStatus;
import br.com.honorato.dao.implement.UserDAO;
import br.com.honorato.exception.DAOException;
import br.com.honorato.exception.EJBException;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
public class UserEJB extends BaseEJB {

	public UserEJB() {
	}
	
	public List<User> searchUsers(){
		
		return new UserDAO(getEm()).selectAll();
		
	}
	
	public void saveUser(User user) throws EJBException {
		
		try {
			//TODO: gerar senha automática
			user.setPassword("default");
			new UserDAO(getEm()).save(user);
			//TODO: enviar senha por email
		} catch (DAOException e) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}
		
	}
	
	public List<EUserStatus> userStatusList() throws EJBException {
		
		return Arrays.asList(EUserStatus.values());
		
	}	
	
}