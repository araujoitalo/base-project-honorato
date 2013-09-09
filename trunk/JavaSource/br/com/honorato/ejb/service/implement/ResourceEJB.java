package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import br.com.honorato.dao.entity.Resource;
import br.com.honorato.dao.entity.SystemModule;
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
	
	ResourceDAO resourceDAO;
	SystemDAO systemDAO;

	public ResourceEJB() {
		
		resourceDAO = new ResourceDAO(getEm());
		systemDAO = new SystemDAO(getEm());
	}

//	public List<Resource> selectBuildTree(){
//		resourceDAO = new ResourceDAO(getEm());
//		return resourceDAO.selectBuildTree();
//	}

	public List<SystemModule> selectSystemTree() throws EJBException{

		systemDAO = new SystemDAO(getEm());
		try {
			return systemDAO.selectSystemTree();
		} catch (DAOException err) {
			throw new EJBException(err.getErrorCode(), err.getMessage());
		}
		
	}

	@LoggerInterceptor
	public void saveResource(Resource resource) throws EJBException {

		try {
			resourceDAO.save(resource);
		} catch ( DAOException e ) {
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}

	}	

	@LoggerInterceptor
	public void deleteResource(Resource resource) throws EJBException {

		try {
			
			resourceDAO.updateChildrenWithParentToRemove(resource.getModuleReference(), resource);
			resource = resourceDAO.selectByKey(resource.getIdModule());
			resourceDAO.delete(resource);

		} catch ( DAOException e ) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}

	}	
}