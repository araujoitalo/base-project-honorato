package br.com.honorato.ejb.service.implement;

import java.util.List;

import javax.ejb.Stateless;

import br.com.honorato.dao.entity.Module;
import br.com.honorato.dao.implement.ModuleDAO;

/**
 * Session Bean implementation class ModuleEJB
 */
@Stateless
public class ModuleEJB extends BaseEJB {

	public ModuleEJB() {
	}
	
	public void printTree(){
		
		Module moduleRoot = new ModuleDAO(getEm()).getFullTree("AAA");
		
		if (moduleRoot!=null){

			System.out.println(moduleRoot.getName());
			
			for(Module c: moduleRoot.getResources()) {
				System.out.println(c.getName());
			}
			
		}
	}
	
	public List<Module> searchModules(){
		
		return new ModuleDAO(getEm()).selectAll();
		
	}
}