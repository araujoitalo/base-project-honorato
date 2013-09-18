package br.com.honorato.util;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class Depurador {	

	@AroundInvoke
	public Object efetuaLog(InvocationContext invocationContext) throws Exception {
		
		// O objeto InvocationContext, que deve constar como argumento do
		// m�todo  anotado   com  @AroundInvoke,   prov�  uma   s�rie  de
		// caracter�sticas que tornam o mecanismo AOP muito flex�vel. Com
		// este objeto � poss�vel, por exemplo, recuperar  os  par�metros
		// passados  para  o  m�todo  que  foi  interceptado e  at� mesmo
		// modificar  seus  valores.  E  permite  ainda  que  dados sejam
		// anexados  a  este  objeto para que  possam ser recuperados por 
		// outro interceptador.

		long inicio = System.currentTimeMillis();

		// Neste  caso  sempre retornamos o objeto  retornado pelo m�todo
		// proceed().  Isto diz ao container que ele deve prosseguir para 
		// o  pr�ximo  ex06.interceptador   na   cadeia   de  execu��o ou 
		// chamar o m�todo de neg�cio interceptado. 

		// Esta  caracter�stica   pode  ser  bastante  �til  no  caso  de
		// seguran�a   quando  um   m�todo  s�   pode  ser  executado  se
		// determinadas  condi��es  forem  verdadeiras.  Por  exemplo, no
		// caso de um m�todo que s� possa ser executado em dias �teis. 

		// �  poss�vel  ter  um  ex06.interceptador  para  um  m�todo, um
		// interceptador para uma classe e um interceptador default que �
		// aplicado  a  todos  os m�todos de um  m�dulo EJB. Para definir
		// este �ltimo tipo de  interceptador � preciso especific�-lo  no
		// arquivo  ejb-jar.xml  (deploymente descriptor).  A  ordem   de
		// chamada  dos interceptadores  � a  seguinte, caso  todos  eles
		// sejam definidos:

		// 1. Interceptador default
		// 2. Interceptador de classe
		// 3. Interceptador de m�todo
		// � poss�vel mudar esta ordem no ejb-jar.xml
		

		Object objeto = null;

		if (invocationContext.getMethod().isAnnotationPresent(LoggerInterceptor.class)){

			LogUtil.debug(this.getClass(), "Depurando .... ");
			LogUtil.info(this.getClass(), "Depurando INFO .... ");
			
			boolean ocorreuErro = false;
			Exception erroOcorrido = null;
			StringBuilder builder = new StringBuilder();
			
			//if(LogUtil.getLogger().isDebugEnabled()){

				builder.append("O m�todo ");
				builder.append(invocationContext.getTarget() + "." + invocationContext.getMethod().getName() +  "() ");
				builder.append("come�ou com os argumentos: ");
				for (int i = 0; i < invocationContext.getParameters().length; i++) {
					builder.append((invocationContext.getParameters())[i] + "; ");
				}
				builder.append(" )");

				LogUtil.debug(invocationContext.getTarget().getClass(), builder.toString());
			//}

			try {
				objeto = invocationContext.proceed();

			} catch (Exception ex){
				ocorreuErro = true;
				erroOcorrido = ex;
			}

			long fim = System.currentTimeMillis();
			builder.delete(0, builder.length());

			if (ocorreuErro) {

				builder.append("Erro ao executar o m�todo em ");
				builder.append((fim - inicio));
				builder.append(" milissegundos o m�todo ");
				builder.append(invocationContext.getTarget() + "." + invocationContext.getMethod().getName() +  "() ");
				LogUtil.error(invocationContext.getTarget().getClass(), builder.toString(),erroOcorrido);
				
				throw erroOcorrido;
			} else {

				if(LogUtil.getLogger().isDebugEnabled()) {
					
					builder.append("Executado com sucesso em ");
					builder.append((fim - inicio));
					builder.append(" milissegundos o m�todo ");
					builder.append(invocationContext.getTarget() + "." + invocationContext.getMethod().getName() +  "() ");

					LogUtil.debug(invocationContext.getTarget().getClass(), builder.toString());
				}
			}
		} else {

			objeto = invocationContext.proceed();
		}

		return objeto;
	}
}