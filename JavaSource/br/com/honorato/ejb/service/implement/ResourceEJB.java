package br.com.honorato.ejb.service.implement;

import java.util.ArrayList;
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
import br.com.honorato.dao.util.EqualFilter;
import br.com.honorato.dao.util.FilterQuery;
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
	public Resource saveResource(Resource resourceReference, Resource resource) throws EJBException {
		
		try {
			resource = new ResourceDAO(getEm()).save(resource);
			
			if (resourceReference.getDependentResources()==null){
				resourceReference.setDependentResources(new ArrayList<Resource>());
			}
			resourceReference.getDependentResources().add(resource);
			new ResourceDAO(getEm()).save(resourceReference);
			
		} catch ( DAOException e ) {
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}
		
		return resource;

	}
	
	@LoggerInterceptor
	public void deleteResource(Resource resource) throws EJBException {

		try {
			
			ResourceDAO resourceDAO = new ResourceDAO(getEm());
			
			if(resource.getReferencesResources()!=null){
				
				for (Resource moduleReference : resource.getReferencesResources()) {
					
					moduleReference.getDependentResources().remove(resource);
					
					if (resource.getDependentResources()!=null && !resource.getDependentResources().isEmpty()){
						moduleReference.getDependentResources().addAll(resource.getDependentResources());
					}
					
					resourceDAO.save(moduleReference);
				}
				
			}
			
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
	
	public List<Resource> checkAvailabilityCode(String code) throws EJBException{

		ArrayList<FilterQuery> filterList = new ArrayList<FilterQuery>();
		filterList.add(new EqualFilter("code",code));
		
		try {
			return new ResourceDAO(getEm()).checkAvailabilityCode(filterList);
		} catch (DAOException e) {
			// TODO recuperar do bundle ou propagar
			throw new EJBException("sds", "Erro ao checar codigo do modulo!");
		}
		
	}
			
}