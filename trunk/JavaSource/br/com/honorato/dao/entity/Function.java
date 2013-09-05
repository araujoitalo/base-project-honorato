package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.com.honorato.dao.enumeration.EModuleType;

@Entity
@DiscriminatorValue("FUNCTION")
public class Function extends Resource implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Function(){
		this.setType(EModuleType.FUNCTION);
	}
}