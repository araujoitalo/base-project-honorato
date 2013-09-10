package br.com.honorato.view.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import br.com.honorato.dao.entity.DTypeModule;
import br.com.honorato.dao.entity.Module;
import br.com.honorato.dao.entity.Resource;
import br.com.honorato.dao.entity.SystemModule;
import br.com.honorato.ejb.service.implement.DTypeModuleEJB;
import br.com.honorato.ejb.service.implement.ResourceEJB;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.FacesUtil;

@SuppressWarnings("serial")
@ManagedBean(name = "documentsController")
@ViewScoped
public class DocumentsController implements Serializable {  

	private TreeNode root;  

	@EJB
	private ResourceEJB moduleEJB;  
	
	@EJB
	private DTypeModuleEJB dTypeModuleEJB;  
	
	private Resource selectedDocument;
	private Module newResource;
	
	private TreeNode selectedNode;
	private TreeNode newNode;	

	public DocumentsController() {  
		root = new DefaultTreeNode("root", null);  

	}  

	public TreeNode getRoot() {  
		return root;  
	}  

	public Resource getSelectedDocument() {
		return selectedDocument;  
	}  

	public void setSelectedDocument(Resource selectedDocument) {  
		this.selectedDocument = selectedDocument;  
	}

	@PostConstruct
	private void init(){

		root = new DefaultTreeNode("Root", null);

		List<SystemModule> lista;
		try {

			lista = moduleEJB.selectSystemTree();
			for (Resource module : lista) {

				buildTree(root,module);

			}
			
		} catch (EJBException e) {

			// TODO RECUPOERAR MENSAGEM DO BUNDLE
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Nao e possivel CRIAR A HIERARQUIA DE MODULOS", "");  
			FacesContext.getCurrentInstance().addMessage(null, message);					
			
		}


	}

	public ResourceEJB getModuleEJB() {
		return moduleEJB;
	}

	public void setModuleEJB(ResourceEJB moduleEJB) {
		this.moduleEJB = moduleEJB;
	}    

	public void buildTree(TreeNode root, Resource ress) {

		System.out.println(ress.getCode());

		TreeNode node = new DefaultTreeNode(ress, root);
		for(Resource res: ress.getResources()) {
			buildTree(node, res);
		}
	}	
	
	private TreeNode getTreeNodeSelected(TreeNode root, String code){
		
		TreeNode out = null;
		
		for (TreeNode node : root.getChildren()) {
			
			if (code.equals(((Resource)node.getData()).getCode())){
				out = node;
			}else{
				out = getTreeNodeSelected(node, code);
			}
			
			if (out!=null){
				break;
			}
			
		}

		return out;

	}	

	public void addNode() {

		System.out.println(selectedDocument);

		selectedNode =  getTreeNodeSelected(root,selectedDocument.getCode());
		
		System.out.println(selectedNode);
		
		try {
			newResource.setCode(newResource.getName());
			newResource.setModuleReference(selectedDocument);
			moduleEJB.saveResource(newResource);
			//TODO: bundle
			FacesUtil.showSucessMessage("Operacao Efetuada com Sucesso!", "Sucesso", false);
			newResource = null;
			init();
			//this.setDlgSucessOpen(true);

		} catch (EJBException err) {
			FacesUtil.showFatalMessage(err.getErrorCode(), err.getMessage(),false);
		}		
		
	}
	
	public void removeNode() {


		if (selectedDocument==null){
			//TODO: bundle
			FacesUtil.showErrorMessage("Selectione um módulo para exclusão!", "Erro", false);

		}else{

			try {

				moduleEJB.deleteResource(selectedDocument);
				init();
				///TODO: bundle
				FacesUtil.showSucessMessage("OperaCAo Efetuada com Sucesso!", "Sucesso", false);

			} catch (EJBException err) {
				FacesUtil.showFatalMessage(err.getErrorCode(), err.getMessage(),false);
			}

		}
	}

	public TreeNode getNewNode() {
		if (newNode==null)
			newNode = new DefaultTreeNode(new Resource(null, ""),selectedNode);
		return newNode;
	}

	public void setNewNode(TreeNode newNode) {
		this.newNode = newNode;
	}

	public Resource getNewResource() {
		
		if (newResource==null){
			try {
				newResource = new Module();
			} catch (EJBException e) {
				// TODO Bundle ou propagar erro
				FacesUtil.showFatalMessage("Erro ao tentar recuperar modulo!", "Falha", false);
			}	
		}
		
		return newResource;
	}

	public void setNewResource(Module newResource) {
		this.newResource = newResource;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
	
	public List<DTypeModule> getEModuloTypeList(){
		
		List<DTypeModule> out = null;
		
		try {
			out = dTypeModuleEJB.getTypeModules();
		} catch (EJBException err) {
			// TODO BUNDLEAuto-generated catch block
			FacesUtil.showFatalMessage(err.getErrorCode(), err.getMessage(),false);
		}
		
		return out;
		
	}

}
//} 
//public class DocumentsController implements Serializable {  
//    
//    private TreeNode root;  
//  
//    private Resource selectedResource;  
//    private TreeNode newNode;
//    
//    @EJB
//	private ResourceEJB moduleEJB;  
//
//	
//    public DocumentsController() {  
//        
//    	  
//    }  
//
//	@PostConstruct
//	private void init(){
//
//		root = new DefaultTreeNode("Root", null);
//
//		List<Module> lista = moduleEJB.selectAllModules();
//
//		for (Resource module : lista) {
//
//			buildTree(root,module);
//
//		}
//
//	}
//	
//    public TreeNode getRoot() {  
//        return root;  
//    }  
//      
//    public Resource getSelectedResource() {  
//        return selectedResource;  
//    }  
//  
//    public void setSelectedResource(Resource selectedDocument) {  
//        this.selectedResource = selectedDocument;  
//    }
//    

//    
//	public void buildTree(TreeNode root, Resource ress) {
//
//		System.out.println(ress.getCode());
//				
//		TreeNode node = new DefaultTreeNode(ress, root);
//		for(Resource res: ress.getResources()) {
//			buildTree(node, res);
//		}
//	}
//	
//	public ResourceEJB getModuleEJB() {
//		return moduleEJB;
//	}
//
//	public void setModuleEJB(ResourceEJB moduleEJB) {
//		this.moduleEJB = moduleEJB;
//	}
//
//	public void addNode() {
//		
//		System.out.println(selectedResource);
//		
//		TreeNode treeSelected =  dsd(root,selectedResource.getCode());
//		
//		System.out.println(treeSelected);
////		Resource res = new Resource();
////		res.setIdModule(selectedResource.getIdModule());
////		//Salvo e releio a tela
////		System.out.println(newNode);
//
//
//	}
//
//	public TreeNode getNewNode() {
//		return newNode;
//	}
//
//	public void setNewNode(TreeNode newNode) {
//		this.newNode = newNode;
//	}	
//	
//}