package com.single.db.structure;

/***********************************************************************
 * Module:  TableAnalyzer.java
 * Author:  Administrator
 * Purpose: Defines the Interface TableAnalyzer
 ***********************************************************************/

/** @pdOid 81fd466f-9e6e-4595-ab85-5af7790c6a7e */
public interface TableResolve {
	
	/**
	 * 返回数据库结构上下文{@DBStructureContext}.
	 * 该对象可以访问数据库的限制条件,和规则;
	 * 可以获取数据库的表分析器;
	 * 可以获取生成数据库表,字段,主键,外键,索引的生成器;
	 * 可以访问数据库结构.
	 * @return
	 */
	public DBStructureContext getDBStructureContext();
	
	/**
	 * 分析指定的表.
	 * 会将该的描述信息,字段信息,约束键(主键,外键)信息分析出来.(不分析唯一键)
	 * @param tableName
	 * @return
	 */
	public Table resolveTable(String tableName);
	
	/**
	 * 分析表的字段信息.
	 * 将表中的所有的字段信息读取出来,并存储到table中.
	 * @param table
	 * @return
	 */
	public boolean resolveTableColumn(Table table);
	
	/**
	 * 分析表的外键信息.
	 * 将表中的所有的外键信息读取出来,将存储到Table中.
	 * @param table
	 * @return
	 */
	public boolean resolveTableForeignKey(Table table);
	
	/**
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception;
}