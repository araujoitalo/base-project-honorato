package br.com.honorato.view.managedbean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "appSessionBean")
@SessionScoped
public class AppSessionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean showCockPit = true;

	public AppSessionBean() {
		
	}

	public boolean isShowCockPit() {
		return showCockPit;
	}

	public void setHideCockPit() {
		this.showCockPit = false;
	}

	public void setShowCockPit() {
		this.showCockPit = true;
	}

}
