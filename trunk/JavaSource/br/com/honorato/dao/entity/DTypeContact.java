package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TP_MODULE")
public class DTypeContact extends DomainBase implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "TypeModule [getIdDomain()=" + getIdDomain() + ", getCode()="
				+ getCode() + "]";
	}

}