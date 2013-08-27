package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.Function;
import br.com.honorato.dao.entity.Module;
import br.com.honorato.dao.entity.Node;
import br.com.honorato.dao.entity.Resource;
import br.com.honorato.dao.implement.ResourceDAO;

/**
 * Session Bean implementation class ModuleEJB
 */
@Stateless
public class ResourceEJB extends BaseEJB {

	public ResourceEJB() {
	}
	
	public List<Module> selectAllModules(){
		return new ResourceDAO(getEm()).selectAllModules();
	}

	public List<Node> selectAllNodes(){
		return new ResourceDAO(getEm()).selectAllNodes();
	}
	
	public List<Function> selectAllFunctions(){
		return new ResourceDAO(getEm()).selectAllFunctions();
	}
	
	public List<Resource> selectAllResources(){
		return new ResourceDAO(getEm()).selectAll();
	}	
}