package br.com.honorato.view.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.honorato.dao.entity.Module;
import br.com.honorato.dao.entity.Resource;
import br.com.honorato.ejb.service.implement.ResourceEJB;

@SuppressWarnings("serial")
@ManagedBean(name = "treeBean")
@ViewScoped
public class TreeBean implements Serializable {  

	private TreeNode root;  

	@EJB
	private ResourceEJB moduleEJB;  

	private TreeNode selectedNode;
	private TreeNode newNode;
	
	private boolean showDialog;

	public TreeBean() {  
		showDialog = false;
	} 
	
	public void buildTree(TreeNode root, Resource ress) {
		
		TreeNode node = new DefaultTreeNode(ress, root);
		for(Resource res: ress.getResources()) {
			buildTree(node, res);
		}
	}	
	
	@PostConstruct
	private void init(){
		
		root = new DefaultTreeNode("Root", null);

		List<Module> lista = moduleEJB.selectAllModules();
		
		for (Resource module : lista) {
			
			buildTree(root,module);

		}
		
	}    

	public TreeNode getRoot() {  
		return root;  
	}  

	public TreeNode getSelectedNode() {  
		return selectedNode;  
	}  

	public void setSelectedNode(TreeNode selectedNode) {  
		this.selectedNode = selectedNode;  
	}  

	public void displaySelectedSingle(ActionEvent event) {  
		
		if(selectedNode == null) {
			showDialog = false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nenhum nó selecionado", "");  
			FacesContext.getCurrentInstance().addMessage(null, message);  
		}else{
			showDialog = true;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", selectedNode.getData().toString());  
			FacesContext.getCurrentInstance().addMessage(null, message);  
		}
		
	}

	public ResourceEJB getModuleEJB() {
		return moduleEJB;
	}

	public void setModuleEJB(ResourceEJB moduleEJB) {
		this.moduleEJB = moduleEJB;
	}

	public TreeNode getNewNode() {
		if (newNode==null)
			newNode = new DefaultTreeNode("",selectedNode);	
		return newNode;
	}

	public void setNewNode(TreeNode newNode) {
		this.newNode = newNode;
	} 
	
	public void addNode() {
		
		if(selectedNode == null) {  
			showDialog = false;
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "É necessário selecionar um nó", "");  
			FacesContext.getCurrentInstance().addMessage(null, message);  
		}else{
			showDialog = true;
			newNode = new DefaultTreeNode(null,selectedNode);
			newNode.setParent(selectedNode);
		}		
		
		
	}
	
	public void putNode() {
		
		System.out.println(newNode.getData().toString());
		Resource folha = new Resource(new Integer(0), String.valueOf(newNode.getData()));
		System.out.println(folha.getCode());
		
	}		

	public boolean isShowDialog() {
		return showDialog;
	}

	public void setShowDialog(boolean showDialog) {
		this.showDialog = showDialog;
	}	
}