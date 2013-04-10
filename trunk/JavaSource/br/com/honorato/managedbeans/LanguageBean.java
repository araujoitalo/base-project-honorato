package br.com.honorato.managedbeans;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final Locale PORTUGUES_BR = new Locale("pt", "BR");
	private static final Locale SIMPLIFIED_CHINESE = Locale.SIMPLIFIED_CHINESE;
	
	public Locale getPortuguesBr(){
		return LanguageBean.PORTUGUES_BR;
	}
	
	public Locale getSimplifiedChinese(){
		return LanguageBean.SIMPLIFIED_CHINESE;
	}
	
	public void changeLocaleCode(ActionEvent event){
		
		UIParameter parameter = (UIParameter) event.getComponent().findComponent("newLocale");
		
		FacesContext.getCurrentInstance()
		.getViewRoot().setLocale((Locale)parameter.getValue());
		
	}
	
}