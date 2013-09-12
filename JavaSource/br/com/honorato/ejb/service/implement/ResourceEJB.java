package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import br.com.honorato.dao.entity.Function;
import br.com.honorato.dao.entity.Resource;
import br.com.honorato.dao.entity.SystemModule;
import br.com.honorato.dao.implement.FunctionDAO;
import br.com.honorato.dao.implement.ResourceDAO;
import br.com.honorato.dao.implement.SystemDAO;
import br.com.honorato.exception.DAOException;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.Depurador;
import br.com.honorato.util.InterceptorDeCallback;
import br.com.honorato.util.LoggerInterceptor;

/**
 * Session Bean implementation class ModuleEJB
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({Depurador.class, InterceptorDeCallback.class})
public class ResourceEJB extends BaseEJB {
	
	public ResourceEJB() {
		
	}

	public List<SystemModule> selectSystemTree() throws EJBException{

		try {
			return new SystemDAO(getEm()).selectSystemTree();
		} catch (DAOException err) {
			throw new EJBException(err.getErrorCode(), err.getMessage());
		}
		
	}

	@LoggerInterceptor
	public void saveResource(Resource resource) throws EJBException {

		try {
			new ResourceDAO(getEm()).save(resource);
		} catch ( DAOException e ) {
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}

	}	

	@LoggerInterceptor
	public void deleteResource(Resource resource) throws EJBException {

		try {
			
			ResourceDAO resourceDAO = new ResourceDAO(getEm());
			resourceDAO.updateChildrenWithParentToRemove(resource.getModuleReference(), resource);
			resource = resourceDAO.selectByKey(resource.getIdModule());
			resourceDAO.delete(resource);

		} catch ( DAOException e ) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}

	}
	
	public List<Function> selectFreeFunctions() throws EJBException{

		try {
			return new FunctionDAO(getEm()).selectFreeFunctions();
		} catch (DAOException err) {
			throw new EJBException(err.getErrorCode(), err.getMessage());
		}
		
	}	
}