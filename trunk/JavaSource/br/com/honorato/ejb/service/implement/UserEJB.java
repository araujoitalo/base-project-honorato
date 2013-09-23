package br.com.honorato.ejb.service.implement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import br.com.honorato.dao.entity.DTypeContact;
import br.com.honorato.dao.entity.User;
import br.com.honorato.dao.enumeration.EUserStatus;
import br.com.honorato.dao.implement.TypeContactDAO;
import br.com.honorato.dao.implement.UserDAO;
import br.com.honorato.dao.util.EqualFilter;
import br.com.honorato.dao.util.FilterQuery;
import br.com.honorato.dao.util.LikeFilter;
import br.com.honorato.exception.DAOException;
import br.com.honorato.exception.EJBException;
import br.com.honorato.exception.EncriptException;
import br.com.honorato.util.Constants;
import br.com.honorato.util.Depurador;
import br.com.honorato.util.Hashing;
import br.com.honorato.util.InterceptorDeCallback;
import br.com.honorato.util.LoggerInterceptor;
import br.com.honorato.util.PasswordUtil;

/**
 * Session Bean implementation class UserEJB
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({Depurador.class, InterceptorDeCallback.class})
public class UserEJB extends BaseEJB {
	
	@EJB
	private DYesNoEJB DYesNoEJB;	

	public UserEJB() {
	}

	@LoggerInterceptor
	//@RolesAllowed({"USER_SEARCH"})
	public List<User> searchUsers(){

		return new UserDAO(getEm()).selectAll();

	}

	@LoggerInterceptor
	public void saveUser(User user) throws EJBException {

		try {
			new UserDAO(getEm()).save(user);
			//TODO: enviar senha por email
		} catch ( DAOException e ) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}

	}

	@LoggerInterceptor
	public void saveNewUser(User user) throws EJBException {

		user.setPassword(PasswordUtil.getRandomPasswordMD5(6));
		saveUser(user);
		//TODO: enviar senha por email

	}	

	@LoggerInterceptor
	public void deleteUser(User user) throws EJBException {

		try {
			user = getEm().find(User.class, user.getIdUser());
			new UserDAO(getEm()).delete(user);
		} catch (DAOException e) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}

	}	

	@LoggerInterceptor
	//@RolesAllowed({"ROLE_USER_SEARCH"})
	public List<User> searchUser(User userFilter) throws EJBException {
		
		ArrayList<FilterQuery> filterList = new ArrayList<FilterQuery>();
		
		if (userFilter==null){
			/*TODO: recuperar do bundle*/
			throw new EJBException("CODIGO","filro de usuario nao informado");			
		}
		
		if (!"".equals(userFilter.getLogin()) && null!=userFilter.getLogin())
			filterList.add(new EqualFilter("login",userFilter.getLogin()));

		if (!"".equals(userFilter.getName()) && null!=userFilter.getName())
			filterList.add(new LikeFilter(LikeFilter.BOTH,"name",userFilter.getName().toUpperCase()));

		if (userFilter.getStatus()!=null){
			filterList.add(new EqualFilter("status",userFilter.getStatus()));
		}
			

		try {

			return new UserDAO(getEm()).recoveryListByCriteria(filterList);

		} catch (DAOException e) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}

	}

	public void changePassword(String login, String currentPassword, String newPassord) throws EJBException {

		User currentUser = getUserByLogin(login);

		if (currentUser==null){
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException("changePassword", "Usuário não encontrado para alteração de senha!");
		}else{
			try {

				if(currentUser.getPassword().equals(Hashing.toMD5Hashing(currentPassword))){
					currentUser.setPassword(Hashing.toMD5Hashing(newPassord));
					currentUser.setChangePassword(DYesNoEJB.getDYesNoByCode(Constants.NO));
					saveUser(currentUser);
				}else{
					throw new EJBException("changePassword", "Senha atual não confere.");
				}
				
			} catch (EncriptException err) {
				throw new EJBException(err.getErrorCode(), err.getMessage());
			}

		}

	}

	public List<EUserStatus> getUserStatusList() throws EJBException {

		return Arrays.asList(EUserStatus.values());

	}	

	public List<DTypeContact> getTypeContactList() throws EJBException {

		return new TypeContactDAO(getEm()).selectAll();

	}

	@LoggerInterceptor
	public User getUserByLogin(String login) throws EJBException {

		try {
			
			ArrayList<FilterQuery> filterList = new ArrayList<FilterQuery>();
			filterList.add(new EqualFilter("login",login));

			return new UserDAO(getEm()).recoverySingleByCriteria(filterList);
			
		} catch (DAOException e) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}

	}	
}