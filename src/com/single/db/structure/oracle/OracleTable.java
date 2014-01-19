package com.single.db.structure.oracle;

import java.util.ArrayList;
import java.util.List;
import com.single.commons.util.StringUtils;
import com.single.db.structure.Column;
import com.single.db.structure.ForeignKey;
import com.single.db.structure.PrimaryKey;
import com.single.db.structure.Table;

public class OracleTable implements Table {

	private String name;
	private String comment;
	private String onwerName;
	private List<Column> columns;
	private PrimaryKey primaryKey;
	private List<ForeignKey> foreignKeys;
	
	public OracleTable(String onwerName,String tableName){
		this.name = tableName;
		this.onwerName = onwerName;
		this.columns = new ArrayList<Column>();
		this.foreignKeys =  new ArrayList<ForeignKey>();
	}
	
	/**
	 * 
	 */
	private static final transient long serialVersionUID = 1L;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public boolean addColumn(Column column) {
		((OracleColumn) column).setTable(this);
		return columns.add(column);
	}

	@Override
	public Column addColumn(String columnName) {
		OracleColumn column = new OracleColumn(columnName, "");
		addColumn(column);
		return column;
	}

	@Override
	public Column removeColumn(String columnName) {
		for(int i=0;i<columns.size();i++){
			Column column = columns.get(i);
			if(columnName.equals(column.getColumnName())){
				columns.remove(i);
				return column;
			}
		}
		return null;
	}

	@Override
	public Column[] getColumns() {
		return columns.toArray(new Column[columns.size()]);
	}

	@Override
	public Column getColumn(String columnName) {
		for(int i=0;i<columns.size();i++){
			Column column = columns.get(i);
			if(columnName.equalsIgnoreCase(column.getColumnName())){
				return column;
			}
		}
		return null;
	}

	@Override
	public PrimaryKey getPrimaryKey() {
		return primaryKey;
	}

	@Override
	public boolean removePrimaryKey() {
		if(primaryKey==null){
			return false;
		}
		primaryKey = null;
		return true;
	}

	@Override
	public PrimaryKey createPrimaryKey(String primaryKeyName, Column... columns) {
		OraclePrimaryKey primaryKey = new OraclePrimaryKey(this, primaryKeyName);
		for(Column column : columns){
			if(column.getTable()!=this){
				throw new NullPointerException("column is empty!");
			}
			primaryKey.addColumn(column);
		}
		return (this.primaryKey = primaryKey);
	}

	@Override
	public ForeignKey[] getForeignKeys() {
		return foreignKeys.toArray(new ForeignKey[foreignKeys.size()]);
	}

	@Override
	public ForeignKey createForeignKey(String foreignKeyName, Column[] columns, Table feferencingTable) {
		OracleForeignKey foreignKey = new OracleForeignKey(this, foreignKeyName, feferencingTable);
		createForeignKey(foreignKey, columns);
		return foreignKey;
	}
	
	private void createForeignKey(OracleForeignKey foreignKey, Column[] columns){
		for(Column column : columns){
			if(column.getTable()!=this){
				throw new NullPointerException("table is empty!");
			}
			foreignKey.addColumn(column);
		}
		foreignKeys.add(foreignKey);
	}

	@Override
	public ForeignKey createForeignKey(String foreignKeyName, String[] columnNames, String feferencingTableName) {
		OracleForeignKey foreignKey = new OracleForeignKey(this, foreignKeyName, feferencingTableName);
		for(String columnName : columnNames){
			Column column = getColumn(columnName);
			if(column==null){
				throw new NullPointerException("column is empty!");
			}
			foreignKey.addColumn(column);
		}
		foreignKeys.add(foreignKey);
		return foreignKey;
	}

	
	@Override
	public ForeignKey removeForeignKey(String foreignKeyName) {
		if(StringUtils.isNotEmpty(foreignKeyName)){
			for(int i=0,n=foreignKeys.size();i<n;i++){
				if(foreignKeyName.equals(foreignKeys.get(i).getName())){
					return foreignKeys.remove(i);
				}
			}
		}
		return null;
	}

	@Override
	public ForeignKey removeForeignKey(Table feferencingTable) {
		for(int i=0,n=foreignKeys.size();i<n;i++){
			if(feferencingTable==foreignKeys.get(i).getTable()){
				return foreignKeys.remove(i);
			}
		}
		return null;
	}

	@Override
	@Deprecated
	public ForeignKey[] getExportedForeignKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public void addExportedForeignKey(ForeignKey foreignKey) {
		// TODO Auto-generated method stub

	}

	@Override
	@Deprecated
	public boolean removeExportedForeignKey(ForeignKey foreignKey) {
		return false;
	}

	@Override
	public String getOwnerName() {
		return onwerName;
	}

	@Override
	public ForeignKey getForeignKey(String foreignKeyName) {
		for(int i=0,n=foreignKeys.size();i<n;i++){
			ForeignKey key = foreignKeys.get(i);
			if(key.getName().equals(foreignKeyName)){
				return key;
			}
		}
		return null;
	}

}
