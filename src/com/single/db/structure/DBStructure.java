package com.single.db.structure;

/***********************************************************************
 * Module:  DBStructure.java
 * Author:  Saleson
 * Purpose: Defines the Class DBStructure
 ***********************************************************************/

import java.io.Serializable;
import java.sql.Connection;
import java.util.*;
import java.util.Map.Entry;
import org.apache.log4j.Logger;

public abstract class DBStructure implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static Logger logger = Logger.getLogger(DBStructure.class.getName());
	protected DBStructureContext context;
	protected Map<String, Table> tableMapping = new HashMap<String, Table>();
	protected String onwer;
	
	/**
	 * 将表添加到数据库结构中
	 * @param table
	 */
	public void addTable(Table table){
		tableMapping.put(table.getName(), table);
	}
	
	/**
	 * 查找指定的表名,如果查找到,就返回表结构(Table),否则返回空.
	 * @param tableName
	 * @return
	 */
	public Table findTable(String tableName){
		return tableMapping.get(tableName);
	}
	
	
	protected Table removewTable(String tableName){
		return tableMapping.remove(tableName);
	}
	
	
	protected boolean removewTable(Table table){
		return tableMapping.remove(table.getName())!=null;
	}
	
	/**
	 * 刷新结构中所有的表的外键联系.
	 */
	protected void relationshipForeignKey(){
		Iterator<Entry<String, Table>> it = tableMapping.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, Table> entry = it.next();
			relationshipForeignKey(entry.getValue());
		}
	}
	
	/**
	 * 刷新表的外键联系.
	 * @param table
	 */
	protected void relationshipForeignKey(Table table){
		ForeignKey[] FKeys  = table.getForeignKeys();
		for(ForeignKey FKey : FKeys){
			String tableName = FKey.getReferencingTableName();
			Table referencingTable = tableMapping.get(tableName);
			if(referencingTable==null)
				throw null;
			FKey.setReferencingTable(referencingTable);
		}
	}
	
	/**
	 * 重新到数据库中加载表的结构信息,但不保存到当前结构中.
	 * @param tableName
	 * @return
	 */
	public Table refreshTableUnKeep(String tableName){
		TableResolve resolve = context.getTableresolve();
		Table table = resolve.resolveTable(tableName);
		if(table!=null){
			relationshipForeignKey(table);
		}
		try {
			resolve.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return table;
	}
	
	/**
	 * 重新到数据库中加载表的结构信息.
	 * @param tableName
	 * @return
	 */
	public boolean refreshTable(String tableName){
		Table table = refreshTableUnKeep(tableName);
		if(table==null) 
			return false;
		tableMapping.put(tableName, table);
		return true;
	}
	
	/**
	 * 返回数据库结构的上下文环境
	 * @return
	 */
	public DBStructureContext getDBStructureContext(){
		return context;
	}
	
	/**
	 * 返回数据库的所有者
	 * @return
	 */
	public String getOnwer(){
		return onwer;
	}
	
	/**
	 * 设置数据库的所有者
	 * @param onwer
	 */
	public void setOnwer(String onwer){
		this.onwer = onwer;
	}
	
	/**
	 * 返回一个数据库连接器.
	 * @return
	 */
	public abstract Connection getConnection();
}