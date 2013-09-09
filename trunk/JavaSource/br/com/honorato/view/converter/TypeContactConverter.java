package br.com.honorato.view.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.honorato.dao.entity.DTypeContact;
import br.com.honorato.ejb.service.implement.TypeContactEJB;
import br.com.honorato.exception.EJBException;

@FacesConverter("typeContactConverter")
public class TypeContactConverter implements Converter {
	
	private TypeContactEJB typeContactEJB;	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		DTypeContact typeContact = null;

		if (!"Selecione".equals(value) && !"".equals(value)) {
			try {
				InitialContext ini = new InitialContext();
				typeContactEJB = (TypeContactEJB) ini.lookup("java:module/TypeContactEJB!br.com.honorato.ejb.service.implement.TypeContactEJB");
				typeContact = typeContactEJB.getTypeContatcByKey(new Integer(value));
			} catch (NumberFormatException | EJBException | NamingException e ) {
				// TODO Logar erro e pegar do bundle
				throw new ConverterException(new FacesMessage("Erro, entre em contato com o administrador do sistema!"));
			}
		} else {
			return null;
		}
		
		return typeContact; 
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value != null ) {
			if(value instanceof DTypeContact){
				return String.valueOf(((DTypeContact) value).getIdDomain());
			} else {
			if ("Selecione".equals(value))  
				return null;
			}
		}
		return null;
		
	}

}
