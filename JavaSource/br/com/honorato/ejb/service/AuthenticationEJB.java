package br.com.honorato.ejb.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import br.com.honorato.dao.entity.Rule;
import br.com.honorato.dao.entity.User;
import br.com.honorato.dao.implement.RuleDAO;
import br.com.honorato.dao.implement.UserDAO;

/**
 * Session Bean implementation class LoginEJB
 */
@SuppressWarnings("deprecation")
@Stateless
public class AuthenticationEJB extends BaseEJB {

	public AuthenticationEJB() {
	}
	
	public org.springframework.security.core.userdetails.User authenticateUser(String login){
		
		User user = new UserDAO(getEm()).login(login);

		List<Rule> ruleList = new RuleDAO(getEm()).selectAll();
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