package com.single.app.service;

/**
 * 在使用某个功能时,但是该功能没有注册,就抛出这个异常.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class UnregisteredException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String functionName;
	
	public UnregisteredException(String functionName){
		super(functionName + " 未注册!");
		this.functionName = functionName;
	}
	
	
	public UnregisteredException(String functionName, String msg){
		super(msg);
		this.functionName = functionName;
	}
	
	
	public String getFunctionName(){
		return functionName;
	}
	
	
}
