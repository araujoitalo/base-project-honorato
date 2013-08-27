package br.com.honorato.view.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.honorato.ejb.service.implement.UserEJB;
import br.com.honorato.exception.EJBException;
import br.com.honorato.exception.EncriptException;
import br.com.honorato.util.Hashing;

@ManagedBean(name = "changePasswordBean")
@ViewScoped
public class ChangePasswordBean extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserEJB userEJB;
	private String currentPassword = "";
	private String password = "";
	private String confirmPassword = "";

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String changePassword(){

		String out = "";
		
		if (null==currentPassword || "".equals(currentPassword)){
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Senha inválida. Não é possível incluir senha vazia ou nula."));
			return out;
		}

		if (null==this.password || "".equals(this.confirmPassword)){
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Senha inválida. Não é possível incluir senha vazia ou nula."));
			return out;
		}else if(!this.password.equals(this.confirmPassword)){
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Senha de confirmação diferente da nova senha."));
			return out;
		}else{
			
			try {
				getUserEJB().changePassword(getAppSessionBean().getLoggedUser().getLogin(), currentPassword, password);
				getAppSessionBean().setLoggedUser(null);
				getAppSessionBean().getLoggedUser();
				/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Senha alterada com sucesso!"));
				
			} catch (EJBException e) {
				/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
				return out;
			}
			
		}
		
		return out;
	}

	public UserEJB getUserEJB() {
		return userEJB;
	}

	public void setUserEJB(UserEJB userEJB) {
		this.userEJB = userEJB;
	}

}
