package br.com.honorato.dao.entity;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("FUNCTION")
public class Function extends Resource implements Serializable {
	
	private static final long serialVersionUID = 1L;

}