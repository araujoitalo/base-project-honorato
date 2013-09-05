package br.com.honorato.view.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.honorato.dao.entity.User;
import br.com.honorato.ejb.service.implement.UserEJB;
import br.com.honorato.exception.EJBException;
import br.com.honorato.security.AuthenticationService;
import br.com.honorato.util.FacesUtil;

@ManagedBean(name = "appSessionBean")
@SessionScoped
public class AppSessionBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB
	private UserEJB userEJB;
	private User loggedUser;
	
	public AppSessionBean() {
		loggedUser = null;
	}

	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService; // injected Spring defined service for bikes
	
	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}	

	public AuthenticationService getAuthenticationService() {
		return this.authenticationService;
	}

	public User getLoggedUser() {
		
		if (loggedUser==null){
			
			try {
				loggedUser = userEJB.getUserByLogin(FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName());				
			} catch (EJBException err) {
				FacesUtil.showFatalMessage(err.getErrorCode(), err.getMessage(),false);
			}
				
		}
		
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}	
	
	public boolean login(String username, String password) {
		
		return getAuthenticationService().login(username, password);

	}	
	
	public void logout() {
		getAuthenticationService().logout();
		loggedUser = null;
	}	
}
