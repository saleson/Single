package com.single.db.query;

/***********************************************************************
 * Module:  SelectColumn.java
 * Author:  Administrator
 * Purpose: Defines the Class SelectColumn
 ***********************************************************************/


import com.single.db.structure.DataType;

/**
 * 查询字段
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public interface SelectColumn {
	
	/**
	 * 返回查询的表
	 * @return
	 */
	public QueryTable getTable();
	
	/**
	 * 返回查询的字段名
	 * @return
	 */
	public String getColumnName();
	
	/**
	 * 返回查询的字段的显示名称.
	 * @return
	 */
	public String getShowName();
	
	/**
	 * 设置字段的显示名称.
	 * @param showName
	 */
	public void setShowName(String showName);
   
	/**
	 * 返回字段的类型.
	 * @return
	 */
	public DataType getDataType();
	
	/**
	 * 返回字段显示的SQL语句.
	 * @return
	 */
	public String toSQLString();

}