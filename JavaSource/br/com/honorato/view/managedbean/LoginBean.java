package br.com.honorato.view.managedbean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

@ManagedBean(name = "loginBean")
@ViewScoped
public class LoginBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userLogin = "admin";
	private String password = "admin";

	//	@ManagedProperty(value = "#{authenticationService}")
	//	private AuthenticationService authenticationService; // injected Spring defined service for bikes

	public String login() {
		
		getAppSessionBean().setLoggedUser(null);

		boolean success = getAppSessionBean().getAuthenticationService().login(userLogin, password);

		String defaultPage = "/application/home?faces-redirect=true";
		String loginPage = "/public/login?faces-redirect=true";


		if (success){
			
			if (null!=getSpringSecuritySavedRequestKey()){
				defaultPage = getSpringSecuritySavedRequestKey() + "?faces-redirect=true";
			}
			return defaultPage;

		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login or password incorrect."));
			return loginPage;
		}
	}

	public String logout() {
		String defaultPage = "/application/home?faces-redirect=true";
		getAppSessionBean().logout();
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
