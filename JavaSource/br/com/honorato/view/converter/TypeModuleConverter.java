package br.com.honorato.view.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.honorato.dao.entity.DEModuleType;
import br.com.honorato.dao.enumeration.EModuleType;
import br.com.honorato.ejb.service.implement.TypeModuleEJB;
import br.com.honorato.exception.EJBException;

@FacesConverter("typeModuleConverter")
public class TypeModuleConverter implements Converter {
	
	private TypeModuleEJB typeModuleEJB;	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		EModuleType eModuleType = null;
		
		 if (!"Selecione".equals(value) && !"".equals(value)) {
			 
			try {
				InitialContext ini = new InitialContext();				
				typeModuleEJB = (TypeModuleEJB) ini.lookup("java:module/TypeModuleEJB!br.com.honorato.ejb.service.implement.TypeModuleEJB");
				
				DEModuleType deModuleType = new DEModuleType();
				deModuleType.seteModuleType(typeModuleEJB.getTypeModuleByCode(value));
			} catch (EJBException | NamingException e ) {
				// TODO Logar erro e pegar do bundle
				throw new ConverterException(new FacesMessage("Erro, entre em contato com o administrador do sistema!"));
			}			 
			 
		} else {
			return null;
		}
		
		return eModuleType; 
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value != null ) {
			if(value instanceof EModuleType){
				return String.valueOf(((EModuleType) value).getCode());
			} else {
			if ("Selecione".equals(value))  
				return null;
			}
		}
		return null;
		
	}

}
