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

import br.com.honorato.dao.entity.DYesNo;
import br.com.honorato.dao.entity.Function;
import br.com.honorato.dao.entity.Module;
import br.com.honorato.dao.entity.Resource;
import br.com.honorato.dao.entity.SystemModule;
import br.com.honorato.dao.enumeration.EModuleType;
import br.com.honorato.ejb.service.implement.DYesNoEJB;
import br.com.honorato.ejb.service.implement.ResourceEJB;
import br.com.honorato.exception.EJBException;
import br.com.honorato.util.FacesUtil;
import br.com.honorato.view.components.FunctionDataModel;

@SuppressWarnings("serial")
@ManagedBean(name = "documentsController")
@ViewScoped
public class DocumentsController implements Serializable {  

	private TreeNode root;  

	@EJB
	private ResourceEJB moduleEJB;  
	
	@EJB
	private DYesNoEJB dYesNoEJB;  
	
	@EJB
	private ResourceEJB resourceEJB;  
	
	private Resource selectedDocument;
	private Module newModule;
	
	private TreeNode selectedNode;
	private TreeNode newNode;
	
	private DYesNo accumulatorFunction;
	
	private Function[] selectedFunctions;
	private FunctionDataModel freeFunctions;
	
	private String msgCheckAvailabilityCode;
	
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
			
			accumulatorFunction = dYesNoEJB.getNo();
			
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
		for(Resource res: ress.getDependentResources()) {
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
		
		try {
			
//			newModule.setCode(newModule.getName());
			
			if (selectedFunctions!=null){
				for (Function selectFunction : selectedFunctions) {
					newModule.getDependentResources().add(selectFunction);
				}
			}
			
			moduleEJB.saveResource(selectedDocument, newModule);
			
			//TODO: bundle
			FacesUtil.showSucessMessage("Operacao Efetuada com Sucesso!", "Sucesso", true);
			newModule = null;
			init();
			//this.setDlgSucessOpen(true);

		} catch (EJBException err) {
			FacesUtil.showFatalMessage(err.getErrorCode(), err.getMessage(),false);
		}		
		
	}
	
	public void removeNode() {


		if (selectedDocument==null){
			//TODO: bundle
			FacesUtil.showErrorMessage("Selectione um modulo para exclusao!", "Erro", false);

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
		
		if (newModule==null){
			newModule = new Module();
		}
		
		return newModule;
	}

	public void setNewResource(Module newResource) {
		this.newModule = newResource;
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
	
	public List<EModuleType> getEModuloTypeList(){
		
		return EModuleType.getListValues();
		
	}
	
	public List<DYesNo> getDYesNolist(){
		
		try {
			return dYesNoEJB.dYesNoList();
		} catch (EJBException err) {
			FacesUtil.showFatalMessage(err.getErrorCode(), err.getMessage(),false);
			return null;
		} 
		
	}

	public DYesNo getAccumulatorFunction() {
		return accumulatorFunction;
	}

	public void setAccumulatorFunction(DYesNo accumulatorFunction) {
		this.accumulatorFunction = accumulatorFunction;
	}
	
	public Function[] getSelectedFunctions() {
		return selectedFunctions;
	}

	public void setSelectedFunctions(Function[] selectedFunctions) {
		this.selectedFunctions = selectedFunctions;
	}

	public void setFreeFunctions(FunctionDataModel freeFunctions) {
		this.freeFunctions = freeFunctions;
	}

	public FunctionDataModel getFreeFunctions() {
		
//		if (freeFunctions==null){
			try {
				freeFunctions = new FunctionDataModel(resourceEJB.selectFreeFunctions());
			} catch (EJBException err) {
				FacesUtil.showFatalMessage(err.getErrorCode(), err.getMessage(),false);
			} 
	//	}
		return freeFunctions;
	}	
	
	public List<Function> getlist() {

		try {
			System.out.println(accumulatorFunction.getCode());
			return resourceEJB.selectFreeFunctions();
		} catch (EJBException err) {
			FacesUtil.showFatalMessage(err.getErrorCode(), err.getMessage(),false);
		} 
		return null;
	}

	public void checkAvailabilityCode() {

		try {

			if (this.getNewResource().getCode().length()>2){

				List<Resource> list = resourceEJB.checkAvailabilityCode(newModule.getCode());

				if (!list.isEmpty()){
					//TODO: recuperar do bundle
					msgCheckAvailabilityCode = "Ja existe este codigo"; 
				}else{
					msgCheckAvailabilityCode = "";
				}		
			}else{
				msgCheckAvailabilityCode = "";
			}
			
		} catch (EJBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//		resourceEJB.saveResource(selectedDocument, newModule);
		//		
//		
//		System.out.println(newNode.getData().toString());
//		Resource folha = new Resource(new Integer(0), String.valueOf(newNode.getData()));
//		newNode.setParent(selectedNode);
//		
//		TreeNode documents = new DefaultTreeNode(newNode, root);  
//		
//		System.out.println(folha.getCode());

	}

	public String getMsgCheckAvailabilityCode() {
		return msgCheckAvailabilityCode;
	}

	public void setMsgCheckAvailabilityCode(String msgCheckAvailabilityCode) {
		this.msgCheckAvailabilityCode = msgCheckAvailabilityCode;
	}	

}