package com.single.db.structure;


/***********************************************************************
 * Module:  ForeignKey.java
 * Author:  Saleson
 * Purpose: Defines the Interface Table
 ***********************************************************************/

/**
 * 表的外键
 * @author Saleson
 *
 */
public interface ForeignKey extends Key{
	
	/**
	 * 被关联的表名
	 * @return
	 */
	public String getReferencingTableName();
	
	/**
	 * 被关联的主键名称
	 * @return
	 */
	public String getReferencingPrimaryKeyName();
	
	/**
	 * 被关联的主键
	 * @return
	 */
	public PrimaryKey getReferencingPrimaryKey();
	
	/**
	 * 设置被关联的表
	 * @param referencingTable
	 */
	public void setReferencingTable(Table referencingTable);
	
	/**
	 * 被关联的表
	 * @return
	 */
	public Table getReferencingTable();
	
	/**
	 * 被关联的(主键)字段.
	 * @return
	 */
	public Column[] getReferencingColumns();
}
