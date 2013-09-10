package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.DTypeModule;
import br.com.honorato.dao.implement.DTypeModuleDAO;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.Constants;

/**
 * Session Bean implementation class TypeContactEJB
 */
@Stateless
public class DTypeModuleEJB extends BaseEJB {

	public DTypeModuleEJB() {
	}
	
	public List<DTypeModule> typeModuleList() throws EJBException {
		
		return (new DTypeModuleDAO(getEm()).selectAll());
		
	}	

	public DTypeModule getDTypeModuleByKey(int key) throws EJBException {
		
		return (new DTypeModuleDAO(getEm()).selectByKey(key));
		
	}	
	
	public DTypeModule getDTypeModuleByCode(String code) throws EJBException {
		
		DTypeModule filter = new DTypeModule();
		filter.setCode(code);
		return (new DTypeModuleDAO(getEm()).recoverySingleByCriteria(filter));
		
	}	
	
	public DTypeModule getSytem() throws EJBException {
		
		return getDTypeModuleByCode(Constants.TYPE_MODULE_SYSTEM);
		
	}
	
	public DTypeModule getModule() throws EJBException {
		
		return getDTypeModuleByCode(Constants.TYPE_MODULE_MODULE);
		
	}
	
	public DTypeModule getFunction() throws EJBException {
		
		return getDTypeModuleByCode(Constants.TYPE_MODULE_FUNCTION);
		
	}
	
	public List<DTypeModule> getTypeModules() throws EJBException {
		
		return (new DTypeModuleDAO(getEm()).selectAll());
		
	}	

}