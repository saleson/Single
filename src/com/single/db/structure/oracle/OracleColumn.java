package com.single.db.structure.oracle;


import org.apache.commons.lang3.ArrayUtils;

import com.single.db.structure.Column;
import com.single.db.structure.DataBaseRule;
import com.single.db.structure.Table;

public class OracleColumn implements Column{

	/**
	 * 
	 */
	private static final transient long serialVersionUID = 1L;
	
	private final static transient String[] noSizeType = {"BLOB", "CLOB", "DATE", "NCLOB"};
	
	private String columnName;
	private String comments;
	private int type;
	private int columnSize;
	private int decimalDigits;
	private String defaultValue;
	private Table table;
	
	
	public OracleColumn(String columnName, String comments){
		this(columnName, comments, 12, 0, 0);
	}
	
	public OracleColumn(String columnName, String comments, String typeString){
		this(columnName, comments, 12, 0, 0);
		setTypeString(typeString);
	}
	
	public OracleColumn(String columnName, String comments, int type, int columnnSize, int decimalDigits){
		this.columnName = columnName;
		this.comments = comments;
		this.type = type;
		this.columnSize = columnnSize;
		this.decimalDigits = decimalDigits;
	}
	
	protected void setTable(Table table) {
		this.table = table;
	}
	
	@Override
	public Table getTable() {
		return table;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void setType(int type) {
		// TODO Auto-generated method stub
		this.type = type;
	}

	@Override
	public int getColumnSize() {
		return columnSize;
	}

	@Override
	public void setColumnSize(int size) {
		this.columnSize = size;
	}

	@Override
	public int getDecimalDigits() {
		return decimalDigits;
	}

	@Override
	public void setDecimalDigits(int decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	@Override
	public void setColumnSize(int size, int decimalDigits) {
		this.columnSize = size;
		this.decimalDigits = decimalDigits;
		
	}

	@Override
	public String getTypeString() {
		DataBaseRule rule = OracleDataBaseRule.getInstance();
		String _type = rule.getColumnTypeString(type);
		if(ArrayUtils.indexOf(noSizeType, _type)==-1 && columnSize!=0){
			_type += " (" + columnSize;
			if(decimalDigits!=0)
				_type += "," + decimalDigits;
			_type += ")";
		}
		return _type;
	}

	@Override
	public void setTypeString(String typeString) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDefault(String def) {
		this.defaultValue = def;
	}

	@Override
	public String getDefault() {
		return defaultValue;
	}

	@Override
	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String getComments() {
		return comments;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof OracleColumn){
			OracleColumn column = (OracleColumn) obj;
			return table==column.getTable() && columnName == column.getColumnName();
		}
		return false;
	}

}
