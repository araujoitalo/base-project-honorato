package br.com.honorato.dao.implement;

import javax.persistence.EntityManager;

import br.com.honorato.dao.entity.DTypeContact;

public class TypeContactDAO extends JpaDAO<DTypeContact> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public TypeContactDAO(EntityManager manager){
		super(manager);
	}
}