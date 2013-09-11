package br.com.honorato.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.honorato.dao.enumeration.EModuleType;

@FacesConverter("typeModuleConverter")
public class TypeModuleConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {

		EModuleType eModuleType = null;
		//TODO: recurepar de alguns lugar este valor (Selecione)  
		if (!"Selecione".equals(value) && !"".equals(value)) {

			eModuleType = EModuleType.getEModuleType(value);

		} else {
			eModuleType = null;
		}

		return eModuleType; 

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if(value != null ) {
			if(value instanceof EModuleType){
				return String.valueOf(((EModuleType) value).getCode());
			} else {
			//TODO: recurepar de alguns lugar este valor (Selecione)  
			if ("Selecione".equals(value))  
				return null;
			}
		}
		return null;
	}

}
