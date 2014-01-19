package com.single.db.structure;

import java.io.Serializable;

/***********************************************************************
 * Module:  Key.java
 * Author:  Saleson
 * Purpose: Defines the Interface Table
 ***********************************************************************/

/**
 * 数据库表的键. 
 * @author Salesion
 *
 */
public interface Key extends Serializable{

	/**
	 * 主键类型
	 */
	public static int PRIMARY_KEY = 2;
	
	/**
	 * 外键类型
	 */
	public static int FOREIGN_KEY = 12;
	
	/**
	 * 唯一键类型
	 */
	public static int UNIQUE_KEY = 23;
	
	
	/**
	 * 返回键类型:{主键,外键,唯一键}
	 * @return
	 */
	public int getType();
	
	/**
	 * 返回键的名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 为键添加字段
	 * @param column
	 * @return
	 */
	public boolean addColumn(Column column);
	
	/**
	 * 将指定的字段名称从键的字段中删除.
	 * @param columnName
	 * @return
	 */
	public Column removeColumn(String columnName);
	
	/**
	 * 返回键的字段
	 * @return
	 */
	public Column[] getColumns();
	
	/**
	 * 是否包含该字段
	 * @param column
	 * @return
	 */
	public boolean containsColumn(Column column);
	
	/**
	 * 是否含有指定字段名的字段,如果有,则返回该字段.
	 * @param columnName
	 * @return
	 */
	public Column containsColumn(String columnName);
	
	/**
	 * 返回键所在的表.
	 * @return
	 */
	public Table getTable();
}
