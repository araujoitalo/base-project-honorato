package br.com.honorato.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.InvocationContext;

public class InterceptorOfCallback {	
	
	//Logger logger = Logger.getRootLogger();
	
	// Life cycle callback methods n�o podem propagar exce��es checked e
	// n�o retornam nada. N�o faz sentido um m�todo deste tipo  propagar
	// uma exce��o checked uma vez que n�o h� um cliente para receber  a 
	// exce��o.
	
	// Um m�todo do tipo callback pode ser definido na pr�pria classe do
	// bean  ou  em  uma  classe  interceptadora. Ou  em  ambas. M�todos
	// callback definidos em classes interceptadoras s�o conhecidos como
	// "lifecycle callback interceptors".

	@PostConstruct 
	public void postconstruct(InvocationContext context) {	
		
		//logger.info(">>>>>>>>>>>> Executando postConstruct() de InterceptadorDeCallback");
		
		try {	
			// A chamada ao m�todo proceed() garante que o pr�ximo m�todo 
			// interceptador do ciclo de vida do bean ser� chamado.
			context.proceed();
		} catch(Exception e) {	
			throw new RuntimeException(e);
		}
	}

	@PrePassivate
	public void prePassivate(InvocationContext context) {	
		
		//logger.info(">>>>>>>>>>>> Executando prePassivate() de InterceptadorDeCallback");
		
		try {	
			context.proceed();
		} catch(Exception e) {	
			throw new RuntimeException(e);
		}
	}

	@PostActivate
	public void postActivate(InvocationContext context) {	
		
		//logger.info(">>>>>>>>>>>> Executando postActivate() de InterceptadorDeCallback");
		
		try {	
			context.proceed();
		} catch(Exception e) {	
			throw new RuntimeException(e);
		}
	}

	@PreDestroy
	public void preDestroy(InvocationContext context) {	

		//logger.info(">>>>>>>>>>>> Executando preDestroy() de InterceptadorDeCallback");
		
		try {	
			context.proceed();
		} catch(Exception e) {	
			throw new RuntimeException(e);
		}
	}
}