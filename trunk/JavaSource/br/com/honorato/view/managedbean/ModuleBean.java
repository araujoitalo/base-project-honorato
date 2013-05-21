package br.com.honorato.view.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.honorato.dao.entity.Module;
import br.com.honorato.ejb.service.ModuleEJB;

@ManagedBean(name = "moduleBean")
@ViewScoped
public class ModuleBean implements Serializable {
	
	@EJB
	private ModuleEJB moduleEJB;

	List<Module> moduleList;
	
	private static final long serialVersionUID = 1L;

	public ModuleBean() {
		
		moduleList = null;
		
	}
	
	public void searchModules(){
		
		moduleList = moduleEJB.searchModules();
		
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}
	
}