package br.com.honorato.util;

import org.apache.log4j.Logger;

public class LogUtil{	

	private static Logger logger = null;

	static{	
		//logger = Logger.getLogger(LogUtil.class);
	}

	@SuppressWarnings("rawtypes")
	public static void info(Class classe, String msg) {
		//logger = Logger.getLogger(classe);
		logger = Logger.getRootLogger();
		logger.info(msg);
	}

	@SuppressWarnings("rawtypes")
	public static void debug(Class classe, String msg) {
		logger = Logger.getLogger(classe);
//		logger.debug(msg);
//		logger = Logger.getRootLogger();
		logger.debug(msg);
	}

	@SuppressWarnings("rawtypes")
	public static void error(Class classe, String msg, Exception exception) {
		logger.error(msg);
		logger.error(exception, exception.getCause());
	}
	
	public static Logger getLogger(){
		return logger;
	}
}