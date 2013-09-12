package br.com.honorato.view.converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.honorato.dao.entity.DYesNo;
import br.com.honorato.ejb.service.implement.DYesNoEJB;
import br.com.honorato.exception.EJBException;

@FacesConverter("dYesNoConverter")
public class DYesNoConverter implements Converter {
	
	private DYesNoEJB dYesNoEJB;	
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		DYesNo dYesNo = null;
		//TODO: recurepar de alguns lugar este valor (Selecione)
		if (!"Selecione".equals(value) && !"".equals(value)) {
			
			try {
			
				InitialContext ini = new InitialContext();
				dYesNoEJB = (DYesNoEJB) ini.lookup("java:module/DYesNoEJB!br.com.honorato.ejb.service.implement.DYesNoEJB");
				dYesNo = dYesNoEJB.getDYesNoByCode(new String(value));
			
			} catch (EJBException | NamingException e ) {
				// TODO Logar erro e pegar do bundle
				throw new ConverterException(new FacesMessage("Erro, entre em contato com o administrador do sistema!"));
			}
			
		} else {
			return null;
		}
		
		return dYesNo; 		

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value != null ) {
			if(value instanceof DYesNo){
				return String.valueOf(((DYesNo) value).getCode());
			} else {
			//TODO: recurepar de alguns lugar este valor (Selecione)
			if ("Selecione".equals(value))  
				return null;
			}
		}
		return null;		
	}

}
