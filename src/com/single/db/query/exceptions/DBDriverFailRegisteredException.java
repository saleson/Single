package com.single.db.query.exceptions;

/**
 * 程序数据库驱动注册失败时抛出的异常.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class DBDriverFailRegisteredException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DBDriverFailRegisteredException(String message, Throwable cause){
		super(message, cause);
	}
	
	public DBDriverFailRegisteredException(String message){
		super(message);
	}
}
