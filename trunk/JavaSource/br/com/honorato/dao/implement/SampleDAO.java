package br.com.honorato.dao.implement;

import javax.persistence.EntityManager;

import br.com.honorato.dao.entity.Sample;

public class SampleDAO extends JpaDAO<Sample> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public SampleDAO(EntityManager manager){
		super(manager);
	}
	
}