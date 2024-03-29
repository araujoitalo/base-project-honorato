package br.com.honorato.ejb.service.implement;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import br.com.honorato.dao.entity.Rule;
import br.com.honorato.dao.entity.User;
import br.com.honorato.dao.implement.RuleDAO;
import br.com.honorato.dao.implement.UserDAO;
import br.com.honorato.exception.DAOException;
import br.com.honorato.exception.EJBException;

/**
 * Session Bean implementation class LoginEJB
 */
@SuppressWarnings("deprecation")
@Stateless
public class AuthenticationEJB extends BaseEJB {

	public AuthenticationEJB() {
	}

	public org.springframework.security.core.userdetails.User authenticateUser(String login) throws EJBException{

		User user = null;
		try {
		
			user = new UserDAO(getEm()).login(login);
		
		} catch (DAOException e) {
			// TODO Logar erro
			user = null;
			throw new EJBException(e); 
		}
		
		List<Rule> ruleList = null;
		
		if (user==null){
			return null;
		}
		
		//TODO: recuperar usuario administrador padr�o do banco tabela de configura��o
		if ("admin".equalsIgnoreCase(login)){
			ruleList = new RuleDAO(getEm()).selectAll();	
		}else{
			//TODO: recuperar somente RULES do referido usuario			
			ruleList = new RuleDAO(getEm()).selectAll();
		}
		
		List<GrantedAuthority> userGrantedAuthorityList = new ArrayList<GrantedAuthority>(); 

		for (Rule rule : ruleList) {
			userGrantedAuthorityList.add(new GrantedAuthorityImpl(rule.getCodeRule()));
		}

		org.springframework.security.core.userdetails.User userOut = 
				new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true,
						true, true, userGrantedAuthorityList);

		return userOut;
	}
}