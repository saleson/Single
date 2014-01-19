package com.single.db.util.jfinal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.internal.OracleTypes;

import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.ICallback;

public class OracleDbProcedure implements ICallback {

	private String statement;
	private String[] params;
	@Override
	public Object run(Connection conn) throws SQLException {
		// TODO Auto-generated method stub

		CallableStatement proc = null;
		try {
			ResultSet rs = null;
			proc = conn.prepareCall("{ " + statement + " }");
			for(int i=0;i<params.length;i++){
				proc.setString(i+1, params[i]);
			}
			//proc.registerOutParameter(2, OracleTypes.CURSOR);
			proc.execute();
			//rs = (ResultSet) proc.getObject(2);
			return null;
		} finally {
			DbKit.close(proc, conn);
		}

	}

}
