package com.single.db.structure.oracle;

import com.single.db.structure.Column;
import com.single.db.structure.DBStructure;
import com.single.db.structure.ForeignKey;
import com.single.db.structure.Key;
import com.single.db.structure.PrimaryKey;
import com.single.db.structure.Table;

public class OracleForeignKey extends OracleKey implements ForeignKey {

	/**
	 * 
	 */
	private static final transient long serialVersionUID = 1L;
	
	private String referencingTableName;
	private Table referencingTable;
	
	public OracleForeignKey(Table table, String keyName, Table referencingTable) {
		this(table, keyName, referencingTable.getName());
		this.referencingTable = referencingTable;
	}
	
	public OracleForeignKey(Table table, String keyName, String referencingTableName) {
		super(table, keyName, Key.FOREIGN_KEY);
		this.referencingTableName = referencingTableName;
	}
	
	public void setreferencingTableName(String referencingTableName){
		this.referencingTableName = referencingTableName;
	}

	protected boolean findAndContactForeignKey(DBStructure structure) {
		referencingTable = structure.findTable(referencingTableName);
		if(referencingTable!=null){
			return true;
		}
		return false;
	}

	@Override
	public String getReferencingTableName() {
		return referencingTableName;
	}

	@Override
	public String getReferencingPrimaryKeyName() {
		PrimaryKey primaryKey = getReferencingPrimaryKey();
		return primaryKey==null ? null : primaryKey.getName();
	}

	
	@Override
	public PrimaryKey getReferencingPrimaryKey() {
		return referencingTable == null ? null : referencingTable.getPrimaryKey();
	}

	@Override
	public Table getReferencingTable() {
		return referencingTable;
	}

	@Override
	public Column[] getReferencingColumns() {
		PrimaryKey primaryKey = getReferencingPrimaryKey();
		return primaryKey==null ? null : primaryKey.getColumns();
	}

	@Override
	public void setReferencingTable(Table referencingTable) {
		this.referencingTable = referencingTable;
	}

}
