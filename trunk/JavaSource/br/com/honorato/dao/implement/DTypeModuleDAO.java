package br.com.honorato.dao.implement;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;

import br.com.honorato.dao.entity.DTypeModule;
import br.com.honorato.exception.DAOException;
import br.com.honorato.util.Constants;

public class DTypeModuleDAO extends JpaDAO<DTypeModule> {

	private static final long serialVersionUID = 1291101069692295470L;
	
	public DTypeModuleDAO(EntityManager manager){
		super(manager);
	}
	
	public DTypeModule recoverySingleByCriteria (DTypeModule dtypeModule){

		setCriteriaQuery(getCriteriaBuilder().createQuery(DTypeModule.class));
		setFromRoot(getCriteriaQuery().from(DTypeModule.class));
		getCriteriaQuery().select(getFromRoot());

		if (!"".equals(dtypeModule.getCode())){
			Predicate codePredicate = getCriteriaBuilder().equal(getFromRoot().get("code"), dtypeModule.getCode());
			getPredicates().add(codePredicate);
		}

		setWhereInQueryWhithPredicatea();

		return getTypeQuery().getSingleResult();

	}
	
	public DTypeModule selectByCode (String code){
		
		DTypeModule dtypeModuleFilter = new DTypeModule();
		dtypeModuleFilter.setCode(code);
		return recoverySingleByCriteria(dtypeModuleFilter);

	}
	
	public DTypeModule getSystem() throws DAOException{
		
		DTypeModule dtypeModule = new DTypeModule();
		dtypeModule.setCode(Constants.TYPE_MODULE_SYSTEM);
		dtypeModule = recoverySingleByCriteria(dtypeModule);
		
		if (dtypeModule==null){
			dtypeModule = new DTypeModule();
			dtypeModule.setCode(Constants.TYPE_MODULE_SYSTEM);
			dtypeModule.setName(Constants.TYPE_MODULE_SYSTEM);
			dtypeModule.setDescription(Constants.TYPE_MODULE_SYSTEM);
			dtypeModule = this.save(dtypeModule);
		}
		
		return dtypeModule;

	}		
}