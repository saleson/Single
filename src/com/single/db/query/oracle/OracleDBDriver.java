package com.single.db.query.oracle;

import com.single.db.query.DBDriver;
import com.single.db.query.From;
import com.single.db.query.exceptions.DBDriverFailRegisteredException;
import com.single.db.structure.DataType;

public class OracleDBDriver extends DBDriver{

	@Override
	public void init() throws DBDriverFailRegisteredException {
		registerSQLBuilder(From.class.getName(), new FromSQLBuilderOracle());
	}
	
	
	@Override
	public String getValueString(Object value, DataType dataType) {
		// TODO Auto-generated method stub
		return null;
	}


}
