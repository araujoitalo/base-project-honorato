package br.com.honorato.managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

import br.com.honorato.application.AuthenticationService;
import ejb.UsuarioEJBService;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userLogin;
	private String password;

	@ManagedProperty(value = "#{authenticationService}")
	private AuthenticationService authenticationService; // injected Spring defined service for bikes


	public String login() {

		boolean success = authenticationService.login(userLogin, password);
		success = true;
		String defaultPage = "home.jsf";
		String loginPage = "login.jsf";
		
		UsuarioEJBService udud = new UsuarioEJBService();
		udud.existeLogin("userLogin");

		if (success){
			if (null!=getSpringSecuritySavedRequestKey()){
				defaultPage = getSpringSecuritySavedRequestKey();
			}
			return defaultPage;

		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login or password incorrect."));
			return loginPage;
		}
	}

	public String logout() {
		String defaultPage = "home.jsf";
		authenticationService.logout();
		return defaultPage;
	}	

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String login) {
		this.userLogin = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public String getSpringSecuritySavedRequestKey(){

		String out = null;

		try{
			out = ((DefaultSavedRequest)((SecurityContextHolderAwareRequestWrapper)FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST")).getServletPath();
		}catch(NullPointerException exception){
			out = null;
		}

		return out;

	}


}
