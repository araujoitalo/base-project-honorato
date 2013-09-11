package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.honorato.dao.enumeration.EModuleType;
import br.com.honorato.exception.EJBException;

@Entity
@DiscriminatorValue("SYSTEM")
public class SystemModule extends Resource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public SystemModule() throws EJBException{
		
		this.setType(EModuleType.SYSTEM);

	}	

}