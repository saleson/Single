package com.single.db;

import com.jfinal.plugin.activerecord.Model;

public class TableInfo<M extends Model<M>>{
	private String tableName;
	private String primaryKey;
	private String sequenceName;
	private M modelClass;
	
	public TableInfo(String tableName, String primaryKey,
			String sequenceName, M modelClass) {
		this.tableName = tableName;
		this.primaryKey = primaryKey;
		this.sequenceName = sequenceName;
		this.modelClass = modelClass;
	}

	public String getTableName() {
		return tableName;
	}
	
	public String getPrimaryKey() {
		return primaryKey;
	}
	
	public String getSequenceName() {
		return sequenceName;
	}
	
	public M getModelClass() {
		return modelClass;
	}
}
