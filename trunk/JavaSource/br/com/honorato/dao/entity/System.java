package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.honorato.dao.enumeration.EModuleType;

@Entity
@DiscriminatorValue("SYSTEM")
public class System extends Resource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public System(){
		this.setType(EModuleType.SYSTEM);
	}	

}