package br.com.honorato.dao.implement;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.honorato.dao.entity.SystemModule;
import br.com.honorato.dao.util.FilterQuery;
import br.com.honorato.exception.DAOException;

public class SystemDAO extends JpaDAO<SystemModule> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public SystemDAO(EntityManager manager){
		super(manager);
	}
	
	public List<SystemModule> selectSystemTree() throws DAOException{
		
		return recoveryListByFilter(SystemModule.class, new ArrayList<FilterQuery>());
		
	}
}