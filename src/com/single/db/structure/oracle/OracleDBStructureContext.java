package com.single.db.structure.oracle;

import java.sql.SQLException;
import org.apache.log4j.Logger;
import com.single.db.structure.DBStructure;
import com.single.db.structure.DBStructureContext;
import com.single.db.structure.DataBaseRule;
import com.single.db.structure.TableGenerator;
import com.single.db.structure.TableResolve;

public class OracleDBStructureContext implements DBStructureContext {

	private DBStructure structure;
	private static OracleDataBaseRule rule = OracleDataBaseRule.getInstance();
	static Logger logger = Logger.getLogger(OracleTableGenerator.class.getName());
	
	
	
	public OracleDBStructureContext(DBStructure structure){
		this.structure = structure;
	}
	
	@Override
	public DataBaseRule getDataBaseRule() {
		return rule;
	}

	@Override
	public TableResolve getTableresolve() {
		try {
			return new OracleTableResolve(this, false);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	@Override
	public TableGenerator getTableGenerator() {
		return new OracleTableGenerator(structure.getConnection());
	}

	@Override
	public DBStructure getDBStructure() {
		return structure;
	}

}
