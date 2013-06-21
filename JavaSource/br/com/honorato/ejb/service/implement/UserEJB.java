package br.com.honorato.ejb.service.implement;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import br.com.honorato.dao.entity.TypeContact;
import br.com.honorato.dao.entity.User;
import br.com.honorato.dao.enumeration.EUserStatus;
import br.com.honorato.dao.implement.TypeContactDAO;
import br.com.honorato.dao.implement.UserDAO;
import br.com.honorato.exception.DAOException;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.Logger;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
@Local
public class UserEJB extends BaseEJB {

	public UserEJB() {
	}
	
	public List<User> searchUsers(){
		
		return new UserDAO(getEm()).selectAll();
		
	}
	
	@Logger
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
	
	@PreAuthorize("hasRole('ssds')")
	@PostAuthorize("hasRole('ssdsmm')")	
	public void deleteUser(User user) throws EJBException {
		
		try {
			user = getEm().find(User.class, user.getIdUser());
			new UserDAO(getEm()).delete(user);
		} catch (DAOException e) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}
		
	}	
	
	public List<User> searchUser(User filter) throws EJBException {
		
		try {
			//TODO: gerar senha automática
			return new UserDAO(getEm()).recoveryByCriteria(filter);
		} catch (DAOException e) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}
		
	}	
	
	public List<EUserStatus> getUserStatusList() throws EJBException {
		
		return Arrays.asList(EUserStatus.values());
		
	}	
	
	public List<TypeContact> getTypeContactList() throws EJBException {
		
		return new TypeContactDAO(getEm()).selectAll();
		
	}	
	
}