package br.com.honorato.view.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.honorato.ejb.service.implement.UserEJB;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.FacesUtil;
import br.com.honorato.util.PasswordUtil;

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
		if (password==null){
			password = "";
		}

		if (new PasswordUtil().validate(password)){

			if(this.password.equals(this.confirmPassword)){

				try {
			
					getUserEJB().changePassword(getAppSessionBean().getLoggedUser().getLogin(), currentPassword, password);
					getAppSessionBean().setLoggedUser(null);
					getAppSessionBean().getLoggedUser();
					/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
					FacesUtil.showSucessMessage("Senha alterada com sucesso!", "Senha alterada com sucesso!", true);
				} catch (EJBException e) {

					/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
					FacesUtil.showErrorMessage(e.getMessage(), e.getMessage(), true);
					return out;
				}

			}else{
				
				/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
				FacesUtil.showErrorMessage("Senha de confirmação diferente da nova senha.", "message", true);
				return out;
			}			
			
		}else{

			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			FacesUtil.showErrorMessage("Senha fora do padrão.","A senha deve conter de 6 a 20 caracteres com pelo menos um caracter maiúsculos, um mainúsculos e um número.", true);
			return out;
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
