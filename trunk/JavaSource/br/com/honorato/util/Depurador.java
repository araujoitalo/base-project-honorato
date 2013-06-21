package br.com.honorato.util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Depurador {	

	@AroundInvoke
	public Object efetuaLog(InvocationContext invocationContext) throws Exception {	

		// O objeto InvocationContext, que deve constar como argumento do
		// método  anotado   com  @AroundInvoke,   provê  uma   série  de
		// características que tornam o mecanismo AOP muito flexível. Com
		// este objeto é possível, por exemplo, recuperar  os  parâmetros
		// passados  para  o  método  que  foi  interceptado e  até mesmo
		// modificar  seus  valores.  E  permite  ainda  que  dados sejam
		// anexados  a  este  objeto para que  possam ser recuperados por 
		// outro interceptador.

		long inicio = System.currentTimeMillis();

		// Neste  caso  sempre retornamos o objeto  retornado pelo método
		// proceed().  Isto diz ao container que ele deve prosseguir para 
		// o  próximo  ex06.interceptador   na   cadeia   de  execução ou 
		// chamar o método de negócio interceptado. 

		// Esta  característica   pode  ser  bastante  útil  no  caso  de
		// segurança   quando  um   método  só   pode  ser  executado  se
		// determinadas  condições  forem  verdadeiras.  Por  exemplo, no
		// caso de um método que só possa ser executado em dias úteis. 

		// É  possível  ter  um  ex06.interceptador  para  um  método, um
		// interceptador para uma classe e um interceptador default que é
		// aplicado  a  todos  os métodos de um  módulo EJB. Para definir
		// este último tipo de  interceptador é preciso especificá-lo  no
		// arquivo  ejb-jar.xml  (deploymente descriptor).  A  ordem   de
		// chamada  dos interceptadores  é a  seguinte, caso  todos  eles
		// sejam definidos:

		// 1. Interceptador default
		// 2. Interceptador de classe
		// 3. Interceptador de método
		// É possível mudar esta ordem no ejb-jar.xml
		
//		Log.debug(this.getClass(), "Depurando .... ");

		Object objeto = null;
		boolean ocorreuErro = false;
		Exception erroOcorrido = null;
		StringBuilder builder = new StringBuilder();

		if (invocationContext.getMethod().isAnnotationPresent(Logger.class)){

//			if(Log.getLogger().isDebugEnabled()){

				builder.append("O método ");
				builder.append(invocationContext.getTarget() + "." + invocationContext.getMethod().getName() +  "() ");
				builder.append("começou com os argumentos: ");
				for (int i = 0; i < invocationContext.getParameters().length; i++) {
					builder.append((invocationContext.getParameters())[i] + "; ");
				}
				builder.append(" )");

//				Log.debug(invocationContext.getTarget().getClass(), builder.toString());
//			}

			try {
				objeto = invocationContext.proceed();

			} catch (Exception ex){
				ocorreuErro = true;
				erroOcorrido = ex;
			}

			long fim = System.currentTimeMillis();
			builder.delete(0, builder.length());

			if (ocorreuErro) {

				builder.append("Erro ao executar o método em ");
				builder.append((fim - inicio));
				builder.append(" milissegundos o método ");
				builder.append(invocationContext.getTarget() + "." + invocationContext.getMethod().getName() +  "() ");
//				Log.error(invocationContext.getTarget().getClass(), builder.toString(),erroOcorrido);
				
				throw erroOcorrido;
			} else {

//				if(Log.getLogger().isDebugEnabled()) {
					
					builder.append("Executado com sucesso em ");
					builder.append((fim - inicio));
					builder.append(" milissegundos o método ");
					builder.append(invocationContext.getTarget() + "." + invocationContext.getMethod().getName() +  "() ");

//					Log.debug(invocationContext.getTarget().getClass(), builder.toString());
//				}
			}
		} else {

			objeto = invocationContext.proceed();
		}

		return objeto;
	}
}