package br.com.honorato.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class FacesUtil {


//	public static String getRequestParameter(String name) {
//		return (String)FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
//	}
//
	public static void showSucessMessage(String summary, String message, boolean keepAlive){
		showMessage(FacesMessage.SEVERITY_INFO, summary, message, keepAlive);
	}

	public static void showAlertMessage(String summary, String message, boolean keepAlive) {
		showMessage(FacesMessage.SEVERITY_WARN, summary, message, keepAlive);
	}

	public static void showErrorMessage(String summary, String message, boolean keepAlive) {
		showMessage(FacesMessage.SEVERITY_ERROR, summary, message, keepAlive);
	}

	public static void showFatalMessage(String summary, String message, boolean keepAlive) {
		showMessage(FacesMessage.SEVERITY_FATAL, summary, message, keepAlive);
	}

	private static void showMessage(FacesMessage.Severity severity, String summary, String message, boolean keepAlive) {

		if (null==summary){
			summary = "";
		}
		if(keepAlive){
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}

		FacesMessage facesMessage = new FacesMessage(severity, summary, message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

//	public static ExternalContext getExternalContext() {
//		return FacesContext.getCurrentInstance().getExternalContext();
//	}

//	@SuppressWarnings("rawtypes")
//	public static Map getSessionMap() {
//		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
//	}

//	public static HttpSession getSession() {
//		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
//	}

//	public static ServletContext getServletContext() {
//		return (ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
//	}

//	public static HttpServletRequest getServletRequest() {
//		return (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
//	}

//	public static HttpServletResponse getServletResponse() {
//		return (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
//	}

	/**
	 * Metodo que retorna um objeto Flash para permitir que sejam armazenados valores entre diversas páginas.
	 * @return
	 */
	public static Flash getFlash(){
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}

	/**
	 * Metodo que recupera um objeto
	 * @param key representa a chave utilizada para armazenar o objeto no Flash.
	 * @param objectClass representa a classe do objeto que se deseja recuperar. ? utilizada para comparar a classe
	 * do objeto recuperado pela chave, caso seja diferente retorna null e remove o valor da tabela.
	 * @return o objeto armazenado anteriormente pela chave.
	 */
//	public static Object flashGet(String key, Class<?> objectClass) {
//		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
//
//		Object value = flash.get(key);
//		if (value == null)
//			return null;
//
//		// testa se a classe do objeto encontrado ? diferente da classe desejada
//		if (objectClass != null) {
//			if (value.getClass() != objectClass)	{
//				// se for diferente, deve remover o objeto antigo e retornar null
//				flash.remove(key);
//				return null;
//			}
//		}
//
//		return value;
//	}

//	public static HttpSession getHttpSession() {
//		return (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
//	}
//
//	@SuppressWarnings("unchecked")
//	public static <T> T getSessionBean(String beanName, Class<T> beanClass){
//
//		Object obj = getHttpSession().getAttribute(beanName);
//		return (T)obj;
//	}
//
//	public static String getClientIP(){
//		return getServletRequest().getRemoteAddr();
//	}

}