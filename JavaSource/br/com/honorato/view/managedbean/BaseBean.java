package br.com.honorato.view.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
public class BaseBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{appSessionBean}")
	private AppSessionBean appSessionBean;

//	private boolean dlgSucessOpen;
	
	public BaseBean() {
//		dlgSucessOpen = false;
	}
	
//	public boolean isDlgSucessOpen() {
//		return dlgSucessOpen;
//	}
//
//	public void setDlgSucessOpen(boolean dlgSucessOpen) {
//		this.dlgSucessOpen = dlgSucessOpen;
//	}
//	
	public AppSessionBean getAppSessionBean() {
		return appSessionBean;
	}

	public void setAppSessionBean(AppSessionBean appSessionBean) {
		this.appSessionBean = appSessionBean;
	}

}
