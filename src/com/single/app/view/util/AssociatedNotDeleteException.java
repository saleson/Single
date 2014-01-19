package com.single.app.view.util;

/**
 * 在删除数据时,如果有关联的数据未增删除,抛出此异常.
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class AssociatedNotDeleteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String[] associatedTables;
	private String[] associatedIds;
	
	
	public AssociatedNotDeleteException(String message){
		super(message);
	}
	
	public AssociatedNotDeleteException(String message, Throwable cause){
		super(message, cause);
	}
	
	public AssociatedNotDeleteException(Throwable cause){
		super(cause);
	}
	
}
