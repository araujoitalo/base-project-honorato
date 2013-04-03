package br.com.honorato.managedbeans;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
 
@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String localeCode;
	
	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
		countries.put("Português", new Locale("pt", "BR")/* Locale.ENGLISH*/); //label, value
		countries.put("Chinese", Locale.SIMPLIFIED_CHINESE);
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}
	
//	public static void main(String[] args){
//		Locale[] locale = Locale.getAvailableLocales();
//		Locale lll  = new Locale("pt", "BR");
//		System.out.println("Supported locales: "); 
//		for (int i=0; i<locale.length; i++) 
//		{ 
//		    System.out.println( locale[i].getLanguage()+", "+locale[i].getCountry()+", " 
//		                        +locale[i].getVariant()+", "+locale[i].getDisplayName() 
//		                      ); 
//		} 
//	}
	
	public String getLocaleCode() {
		return localeCode;
	}


	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}


	public void countryLocaleCodeChanged(ValueChangeEvent e){
		
		String newLocaleValue = e.getNewValue().toString();
		
		//loop a map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
        
        	if(entry.getValue().toString().equals(newLocaleValue)){
        		
        		FacesContext.getCurrentInstance()
        			.getViewRoot().setLocale((Locale)entry.getValue());
        		
        	}
        }

	}

}