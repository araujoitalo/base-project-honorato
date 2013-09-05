package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.Resource;
import br.com.honorato.dao.implement.ResourceDAO;
import br.com.honorato.exception.DAOException;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.LoggerInterceptor;

/**
 * Session Bean implementation class ModuleEJB
 */
@Stateless
public class ResourceEJB extends BaseEJB {

	public ResourceEJB() {
	}
	
	public List<Resource> selectBuildTree(){
		return new ResourceDAO(getEm()).selectBuildTree();
	}
	
	@LoggerInterceptor
	public void saveResource(Resource resource) throws EJBException {

		try {
			new ResourceDAO(getEm()).save(resource);
		} catch ( DAOException e ) {
			/*TODO: RECUPERAR MENSAGEM DO BUNDLE*/
			throw new EJBException(e.getErrorCode(), e.getMessage());
		}

	}	

//	public List<Module> selectAllModules(){
//		return new ResourceDAO(getEm()).selectAllModules();
//	}
//
//	public List<Node> selectAllNodes(){
//		return new ResourceDAO(getEm()).selectAllNodes();
//	}
//	
//	public List<Function> selectAllFunctions(){
//		return new ResourceDAO(getEm()).selectAllFunctions();
//	}
//	
	public List<Resource> selectAllResources(){
		return new ResourceDAO(getEm()).selectAll();
	}	
}