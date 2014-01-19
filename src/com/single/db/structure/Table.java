package com.single.db.structure;

import java.io.Serializable;

/***********************************************************************
 * Module:  Table.java
 * Author:  Saleson
 * Purpose: Defines the Interface Table
 ***********************************************************************/

/**
 * 数据库中的表的描述信息.
 * 包括表名,表的备注,表中的字段,表的主键,外键.
 * @author Saleson
 *
 */
public interface Table extends Serializable{
	/**
	 * 
	 * @return
	 */
	public String getName();
	
	/**
	 * 
	 * @return
	 */
	public String getComment();
	
	/**
	 * 设置表的描述信息.
	 * @param comment
	 */
	public void setComment(String comment);
	
	/**
	 * 添加字段
	 * @param column
	 * @return
	 */
	public boolean addColumn(Column column);
	
	/**
	 * 添加字段
	 * @param columnName
	 * @return
	 */
	public Column addColumn(String columnName);
	
	/**
	 * 删除字段
	 * @param columnName
	 * @return
	 */
	public Column removeColumn(String columnName);
	
	/**
	 * 返回字段
	 * @return
	 */
	public Column[] getColumns();
	
	/**
	 * 查找字段名称并返回字段,如果没有找到,就返回null.
	 * @param columnName
	 * @return
	 */
	public Column getColumn(String columnName);
	
	/**
	 * 返回表的主键.
	 * @return
	 */
	public PrimaryKey getPrimaryKey();
	
	/**
	 * 删除主键
	 * @return
	 */
	public boolean removePrimaryKey();
	
	/**
	 * 创建主键
	 * @param primaryKeyName	主键名称
	 * @param columns			主键的字段,可以多个.
	 * @return
	 */
	public PrimaryKey createPrimaryKey(String primaryKeyName, Column... columns);
	
	/**
	 * 返回外键
	 * @return
	 */
	public ForeignKey[] getForeignKeys();
	
	/**
	 * 返回指定名称的外键
	 * @param foreignKeyName
	 * @return 
	 */
	public ForeignKey getForeignKey(String foreignKeyName);
	
	/**
	 * 创建外键
	 * @param foreignKeyName		外键的名称
	 * @param columns				外键的字段.
	 * @param feferencingTable		被外键引用的表
	 * @return
	 */
	public ForeignKey createForeignKey(String foreignKeyName, Column[] columns, Table feferencingTable);
	
	/**
	 * 创建外键
	 * @param foreignKeyName		外键的名称
	 * @param columnNames			外键的字段名
	 * @param feferencingTableName	被外键引用的表名
	 * @return
	 */
	public ForeignKey createForeignKey(String foreignKeyName, String[] columnNames, String feferencingTableName);
	
	/**
	 * 删除外键
	 * @param foreignKeyName	外键的名称
	 * @return
	 */
	public ForeignKey removeForeignKey(String foreignKeyName);
	
	/**
	 * 删除外键
	 * @param feferencingTable	被外键引用的表
	 * @return
	 */
	public ForeignKey removeForeignKey(Table feferencingTable);
	
	/**
	 * 返回其它表联系该表的外键
	 * @return
	 * @deprecated
	 */
	public ForeignKey[] getExportedForeignKeys();
	
	/**
	 * 添加其它表联系该表的外键
	 * @param foreignKey
	 * @deprecated
	 */
	public void addExportedForeignKey(ForeignKey foreignKey);
	
	/**
	 * 删除其它表联系该表的外键
	 * @param foreignKey
	 * @return
	 * @deprecated
	 */
	public boolean removeExportedForeignKey(ForeignKey foreignKey);
	
	/**
	 * 返回数据库用户
	 * @return
	 */
	public String getOwnerName();
	
}