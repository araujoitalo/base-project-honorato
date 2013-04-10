package br.com.honorato.managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "appSessionBean")
@SessionScoped
public class appSessionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean showCockPit = true;

	public appSessionBean() {
		
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
