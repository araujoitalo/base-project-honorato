package br.com.honorato.dao.implement;

import javax.persistence.EntityManager;

import br.com.honorato.dao.entity.SampleType;

public class SampleTypeDAO extends JpaDAO<SampleType> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public SampleTypeDAO(EntityManager manager){
		super(manager);
	}
	
}