package com.single.db.structure.oracle;

import com.single.db.structure.Key;
import com.single.db.structure.PrimaryKey;
import com.single.db.structure.Table;

public class OraclePrimaryKey extends OracleKey implements PrimaryKey{

	
	/**
	 * 
	 */
	private static final transient long serialVersionUID = 1L;
	
	public OraclePrimaryKey(Table table, String primaryKeyName) {
		super(table, primaryKeyName, Key.PRIMARY_KEY);
	}

}
