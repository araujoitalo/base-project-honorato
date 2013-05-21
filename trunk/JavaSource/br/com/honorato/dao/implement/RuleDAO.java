package br.com.honorato.dao.implement;

import javax.persistence.EntityManager;

import br.com.honorato.dao.entity.Rule;

public class RuleDAO extends JpaDAO<Rule> {

	private static final long serialVersionUID = 1291101069692295470L;

	public RuleDAO(EntityManager manager){
		super(manager);
	}

}
