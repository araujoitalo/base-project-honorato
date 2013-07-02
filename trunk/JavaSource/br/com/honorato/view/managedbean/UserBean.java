package br.com.honorato.view.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import samples.CustomerService;
import br.com.honorato.dao.entity.Contact;
import br.com.honorato.dao.entity.TypeContact;
import br.com.honorato.dao.entity.User;
import br.com.honorato.dao.enumeration.EUserStatus;
import br.com.honorato.ejb.service.implement.UserEJB;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.FacesUtil;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean extends BaseBean implements Serializable {
	
	@EJB
	private UserEJB userEJB;
	private User user;
	private User filter;
	private Contact newContact;
	
	private final static String VIEW_LIST = "List";
	private final static String VIEW_INSERT = "Insert";
	private final static String VIEW_EDIT = "Edit";

	private List<User> searchList;
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{customerService}")
	private CustomerService teste; // injected Spring defined service for bikes

	public UserBean() {
		
		//user = new User();
		//searchList = null;
		
	}
	
	@PostConstruct
	private void init(){

		if (FacesContext.getCurrentInstance().getViewRoot().getViewId().contains(VIEW_LIST)){
			initList();
		}else if(FacesContext.getCurrentInstance().getViewRoot().getViewId().contains(VIEW_EDIT)){
			initEdit();
		}else if(FacesContext.getCurrentInstance().getViewRoot().getViewId().contains(VIEW_INSERT)){
			initInsert();
		}
		
	}
	
	public void initList(){

		if (filter==null){
			
			if (FacesUtil.getFlash().containsKey("userFilter")){
				filter = (User) FacesUtil.getFlash().get("userFilter");	
			}
			
		}
		
		searchUsers();
		
	}	
	
	public void initEdit(){

		if(FacesUtil.getFlash().containsKey("userParam")){			
			user = (User) FacesUtil.getFlash().get("userParam");
		}
		
		if(FacesUtil.getFlash().containsKey("userFilter")){			
			filter = (User) FacesUtil.getFlash().get("userFilter");
		}
		
		addContact();
		
	}
	
	public void initInsert(){
		
		user = new User();
		user.setStatus(EUserStatus.ACTIVE);

		if(FacesUtil.getFlash().containsKey("userFilter")){			
			filter = (User) FacesUtil.getFlash().get("userFilter");
		}
		
		addContact();
		
	}		
	
	public void delete() {
		
		try {
			userEJB.deleteUser(user);
			FacesUtil.showSucessMessage("Operação Efetuada com Sucesso!", "Sucesso", true);
			//this.setDlgSucessOpen(true);
		} catch (EJBException | javax.ejb.EJBAccessException err) {
			/*TODO recuperar do bundle*/
			FacesUtil.showFatalMessage("Erro Inesperado", err.getMessage(),true);
		}
		
	}
	
	public void deleteInList() {
		
		delete();
		initList();
		
	}
	
	public void searchUsers() {
		
		try {
			
			if (filter!=null){
				setSearchList(userEJB.searchUser(filter));
			}
			
			//FacesUtil.showSucessMessage("Operação Efetuada com Sucesso!", "Sucesso", false);
			//this.setDlgSucessOpen(true);
		} catch (EJBException err) {
			/*TODO recuperar do bundle*/
			FacesUtil.showFatalMessage("Erro Inesperado", err.getMessage(),false);
		}
		
	}	
	
//	public void searchList(){
//		searchList = userEJB.searchUsers();
//	}

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
	
	public Contact getNewContact() {
		if (newContact==null)
			newContact = new Contact();

		return newContact;
	}

	public void setNewContact(Contact newContact) {
		this.newContact = newContact;
	}	
	
	public void save() {
		
		try {
			userEJB.saveUser(user);
			FacesUtil.showSucessMessage("Operação Efetuada com Sucesso!", "Sucesso", false);
			//this.setDlgSucessOpen(true);
		} catch (EJBException err) {
			/*TODO recuperar do bundle*/
			FacesUtil.showFatalMessage("Erro Inesperado", err.getMessage(),false);
		}
		
	}
	
	public List<EUserStatus> userStatusList() {
		
		List<EUserStatus> out = null;
		try {
			out = userEJB.getUserStatusList();
		} catch (EJBException e) {
			// TODO logar erro
			e.printStackTrace();
		}
		return out;
		
	}	
	
	public void addContact() {
		
		newContact = new Contact();
		
		for (TypeContact type : getTypeContactList()) {
			newContact.setType(type);
			break;
		}
		
	}	

	public List<TypeContact> getTypeContactList() {
		
		List<TypeContact> out = null;
		try {
			out = userEJB.getTypeContactList();
		} catch (EJBException e) {
			// TODO logar erro
			e.printStackTrace();
		}
		return out;
		
	}
	
	public void putContact() {
		
		newContact.setOwner(user);
		user.getContactList().add(newContact);
		addContact();
	}	

	public void setTypeInNewContact() {
		
		newContact.setOwner(user);
		user.getContactList().add(newContact);
	}	

	public User getFilter() {
		if (filter==null)
			filter=new User();
		return filter;
	}

	public void setFilter(User user) {
		this.filter = user;
	}

	public String redirect(String page){
		FacesUtil.getFlash().put("userParam", user);
		FacesUtil.getFlash().put("userFilter", filter);
		return page + "?faces-redirect=true";
	}
	
	public void initAdd(){

		/*TODO: Status Iniciado com valor padrão de Inclusão*/
		user.setStatus(EUserStatus.BLOCKED);
		
		if(FacesUtil.getFlash().containsKey("userParam")){			
			user = (User) FacesUtil.getFlash().get("userParam");
		}
		
		addContact();
		
	}

	public CustomerService getTeste() {
		return teste;
	}

	public void setTeste(CustomerService teste) {
		this.teste = teste;
	}

}