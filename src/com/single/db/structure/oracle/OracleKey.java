package com.single.db.structure.oracle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import com.single.db.structure.Column;
import com.single.db.structure.Key;
import com.single.db.structure.Table;

public abstract class OracleKey implements Key {

	/**
	 * 
	 */
	private static final transient long serialVersionUID = 1L;
	
	protected String name;
	protected Table table;
	protected Set<Column> columns;
	protected int type;
	
	public OracleKey(Table table, String keyName, int type){
		this.name = keyName;
		this.table = table;
		this.type = type;
		columns = new HashSet<Column>();
	}
	
	@Override
	public int getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean addColumn(Column column) {
		return columns.add(column);
	}

	@Override
	public Column removeColumn(String columnName) {
		for(Column column : columns){
			if(columnName.equals(column.getColumnName())){
				columns.remove(column);
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
	public Table getTable() {
		return table;
	}

	@Override
	public boolean containsColumn(Column column) {
		return columns.contains(column);
	}
	
	@Override
	public Column containsColumn(String columnName) {
		Iterator<Column> l = columns.iterator();
		while(l.hasNext()){
			Column c = l.next();
			if(c.getColumnName().equalsIgnoreCase(columnName)){
				return c;
			}
		}
		return null;
	}
}
