package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import br.com.honorato.dao.entity.Resource;
import br.com.honorato.dao.implement.ResourceDAO;
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

	public ResourceEJB() {
		
		resourceDAO = new ResourceDAO(getEm());
	}

	public List<Resource> selectBuildTree(){
		resourceDAO = new ResourceDAO(getEm());
		return resourceDAO.selectBuildTree();
	}

	@LoggerInterceptor
	public void saveResource(Resource resource) throws EJBException {

		try {
			resourceDAO.save(resource);
		} catch ( DAOException e ) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
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