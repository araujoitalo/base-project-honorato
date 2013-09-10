package br.com.honorato.view.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.honorato.dao.entity.DTypeModule;
import br.com.honorato.ejb.service.implement.DTypeModuleEJB;
import br.com.honorato.exception.EJBException;

@FacesConverter("typeModuleConverter")
public class TypeModuleConverter implements Converter {
	
	private DTypeModuleEJB dTypeModuleEJB;	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		DTypeModule dTypeModule = null;
		
		 if (!"Selecione".equals(value) && !"".equals(value)) {
			 
			try {
				InitialContext ini = new InitialContext();				
				dTypeModuleEJB = (DTypeModuleEJB) ini.lookup("java:module/DTypeModuleEJB!br.com.honorato.ejb.service.implement.DTypeModuleEJB");
				dTypeModule = dTypeModuleEJB.getDTypeModuleByCode(value);
			} catch (EJBException | NamingException e ) {
				// TODO Logar erro e pegar do bundle
				throw new ConverterException(new FacesMessage("Erro, entre em contato com o administrador do sistema!"));
			}			 
			 
		} else {
			return null;
		}
		
		return dTypeModule; 
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value != null ) {
			if(value instanceof DTypeModule){
				return String.valueOf(((DTypeModule) value).getCode());
			} else {
			if ("Selecione".equals(value))  
				return null;
			}
		}
		return null;
	}

}
