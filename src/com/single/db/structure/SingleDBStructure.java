package com.single.db.structure;

import java.sql.Connection;

import com.single.db.query.DBDriver;
import com.single.db.query.exceptions.DBDriverFailRegisteredException;
import com.single.db.query.oracle.OracleDBDriver;
import com.single.db.structure.oracle.OracleDBStructureContext;
import com.single.db.structure.test.TableTest;

public class SingleDBStructure extends DBStructure{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public SingleDBStructure() throws DBDriverFailRegisteredException{
		context = new OracleDBStructureContext(this);
		DBDriver.registerDBDriver(new OracleDBDriver());
		DBDriver.getRegisteredDBDriver().setDBStructure(this);
	}
	
	public Connection getConnection(){
		try {
			return TableTest.getConnection();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
