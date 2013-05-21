package br.com.honorato.view.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.honorato.dao.entity.User;
import br.com.honorato.dao.enumeration.EUserStatus;
import br.com.honorato.ejb.service.UserEJB;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.FacesUtil;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean implements Serializable {
	
	@EJB
	private UserEJB userEJB;
	private User user;
	
	List<User> searchList;
	
	private static final long serialVersionUID = 1L;

	public UserBean() {
		
		user = new User();
		
		/*TODO: Status Iniciado com valor padrão de Inclusão*/
		user.setStatus(EUserStatus.BLOCKED);
		searchList = null;
		
	}
	
	public void searchList(){
		
		searchList = userEJB.searchUsers();
		
	}

	public List<User> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<User> searchList) {
		this.searchList = searchList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void save() {
		
		try {
			userEJB.saveUser(user);
		} catch (EJBException err) {
			/*TODO recuperar do bundle*/
			FacesUtil.showFatalMessage("Erro Inesperado", err.getMessage(),false);
		}
		
	}
	
	public List<EUserStatus> userStatusList() {
		
		List<EUserStatus> out = null;
		try {
			out = userEJB.userStatusList();
		} catch (EJBException e) {
			// TODO logar erro
			e.printStackTrace();
		}
		return out;
		
	}	

}