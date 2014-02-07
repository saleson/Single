package com.single.db.query;



import com.single.db.structure.Table;

/**
 * 查询的表 
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class QueryTable {
	
	
	private String tableName;
	private String alias;
	private Table table;
	
	/*public QueryTable(String tableName){
		this(tableName, "");
	}*/
	
	public QueryTable(String tableName, String alias){
		this.tableName = tableName;
		this.alias = alias;
	}
	
	public String getTableName(){
		return tableName;
	}
	
	public String getAlias(){
		return alias;
	}
	
	public void setAlias(String alias){
		this.alias = alias;
	}

	
	public Table getTable(){
		if(table==null){
			table = DBDriver.getRegisteredDBDriver().getDBStructure().findTable(tableName);
		}
		return table;
	}
	
	@Override
	public String toString() {
		return tableName + " as "+ alias;
	}
}