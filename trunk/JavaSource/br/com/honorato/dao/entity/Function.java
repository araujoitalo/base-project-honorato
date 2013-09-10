package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.convert.ConverterException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.honorato.ejb.service.implement.DTypeModuleEJB;
import br.com.honorato.exception.EJBException;

@Entity
@DiscriminatorValue("FUNCTION")
public class Function extends Resource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Function() throws EJBException{
		
		try {
			InitialContext ini = new InitialContext();				
			DTypeModuleEJB typeContactEJB = (DTypeModuleEJB) ini.lookup("java:app/Base-Project/TypeContactEJB!br.com.honorato.ejb.service.implement.TypeContactEJB");
			this.setType(typeContactEJB.getFunction());
		} catch (EJBException | NamingException e ) {
			// TODO Logar erro e pegar do bundle
			throw new ConverterException(new FacesMessage("Erro, entre em contato com o administrador do sistema!"));
		}	
	}
}