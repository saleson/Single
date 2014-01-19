package com.single.context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.single.db.structure.oracle.OracleTableGenerator;


public class WebContext {
	private static Logger logger = Logger.getLogger(OracleTableGenerator.class.getName());
	
	private static boolean debug;
	private static ServletContext servletContext = null;
	private static ApplicationContext ctx = null;
	
	
	/**
	 * 加载程序的各种属性
	 * @param properties
	 */
	private static void loadProperties(Properties properties){
		logger.info("loading properties...");
		debug = "TRUE".equalsIgnoreCase(properties.getProperty("debug"));
		if(debug)
			logger.info("enable debug!!!");
		
		logger.info("load properties successfully.");
	}
	
	/**
	 * 加载程序的各种属性
	 * @param in
	 */
	static void loadProperties(InputStream in){
		try {
			Properties properties = new Properties();
			properties.load(in);
			loadProperties(properties);
			properties.clear();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 程序是否有启用调试.
	 * @return
	 */
	public static boolean isDebug() {
		return debug;
	}
	
	
	static void setApplicationContext(ApplicationContext ctx){
		WebContext.ctx = ctx;
	}
	
	/**
	 * 返回{@link org.springframework.context.ApplicationContext}实例
	 * @return
	 */
	public static ApplicationContext getApplicationContext(){
		return ctx;
	}
	
	static void setServletContext(ServletContext servletContext){
		WebContext.servletContext = servletContext;
	}
	
	/**
	 * 返回{@link javax.servlet.ServletContext}实例
	 * @return
	 */
	public static ServletContext getServletContext(){
		return servletContext;
	}
	
	/**
	 * 从spring Bean池中获取实例
	 * @param beanId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId) {
		return (T) ctx.getBean(beanId);
	}
	
	/**
	 * 从spring Bean池中获取实例
	 * @param beanId
	 * @param args
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanId, Object... args) {
		return (T) ctx.getBean(beanId, args);
	}
	
	
}
