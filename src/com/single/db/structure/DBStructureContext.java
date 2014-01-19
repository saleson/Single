package com.single.db.structure;

/***********************************************************************
 * Module:  DBStructureContext.java
 * Author:  Saleson
 * Purpose: Defines the Interface DBStructureContext
 ***********************************************************************/

/**
 * 
 * @author Saleson
 *
 */
public interface DBStructureContext {
	
	/**
	 * 返回数据库的限制条件,和规则对象.
	 * @return 
	 */
	public DataBaseRule getDataBaseRule();
	
	/**
	 * 返回数据库中将表分析成com.single.db.structure.Table的分析器
	 * @return
	 */
	public TableResolve getTableresolve();
	
	/**
	 * 返回生成数据库表,字段,主键,外键,索引的生成器.
	 * @return
	 */
	public TableGenerator getTableGenerator();
	
	/**
	 * 返回结构
	 * @return
	 */
	public DBStructure getDBStructure();
}