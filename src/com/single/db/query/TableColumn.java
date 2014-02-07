package com.single.db.query;

import com.single.commons.util.StringUtils;
import com.single.db.structure.Column;
import com.single.db.structure.DataType;

public class TableColumn implements SelectColumn{

	protected QueryTable table;
	protected String columnName;
	protected String showName;
	protected DataType dataType;
	protected Column column;
	
	public TableColumn(QueryTable table, String columnName){
		this(table, columnName, "");
	}
	
	public TableColumn(QueryTable table, String columnName, String showName){
		this(table, columnName, showName, null);
		//column = table.getTable().getColumn(columnName);
		//dataType = column;
			
	}
	
	public TableColumn(QueryTable table, String columnName, String showName, DataType dataType){
		this.table = table;
		this.columnName = columnName;
		this.showName = showName;
		this.dataType = dataType;
	} 
	
	@Override
	public QueryTable getTable() {
		return table;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public String getShowName() {
		return showName;
	}

	@Override
	public void setShowName(String showName) {
		this.showName = showName;
	}

	@Override
	public DataType getDataType() {
		if(dataType == null){
			
		}
		return dataType;
	}

	@Override
	public String toSQLString() {
		String str = table.getAlias() + "." + columnName;
		if(StringUtils.isNotEmpty(showName)){
			str += " as " + showName;
		}
		return str;
	}
	
	public String toString(){
		return toSQLString();
	}

}
