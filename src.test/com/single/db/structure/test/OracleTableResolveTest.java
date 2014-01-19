package com.single.db.structure.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


import com.single.db.structure.DBStructure;
import com.single.db.structure.SingleDBStructure;
import com.single.db.structure.Table;
import com.single.db.structure.TableResolve;
import com.single.db.structure.oracle.OracleDBStructureContext;
import com.single.db.structure.oracle.OracleTableResolve;

public class OracleTableResolveTest {

	public static void test1() throws SQLException{
		Date date = new Date();
		DBStructure structure = new SingleDBStructure();
		OracleDBStructureContext context = new OracleDBStructureContext(structure);
		TableResolve resolve = context.getTableresolve();
		Connection conn = structure.getConnection();
		DatabaseMetaData dbMeta = conn.getMetaData();
		ResultSet rs = null;
		rs = dbMeta.getTables(null, dbMeta.getUserName(), "%", new String[]{"TABLE"});
		while (rs.next()) { 
			String tableName = rs.getString("TABLE_NAME");
			System.out.println(tableName);
			Table table =  resolve.resolveTable(tableName); 
			structure.addTable(table);
		}
		Date date1 = new Date();
		System.out.println(date1.getTime() - date.getTime());
		System.out.println((date1.getTime() - date.getTime())/1000);
	}
	
	public static void test2() throws SQLException{
		Date date = new Date();
		DBStructure structure = new SingleDBStructure();
		OracleDBStructureContext context = new OracleDBStructureContext(structure);
		//TableResolve resolve = context.getTableresolve();
		OracleTableResolve resolve = new OracleTableResolve(context, true);
		Connection conn = structure.getConnection();
		DatabaseMetaData dbMeta = conn.getMetaData();
		ResultSet rs = null;
		rs = dbMeta.getTables(null, dbMeta.getUserName(), "%", new String[]{"TABLE"});
		while (rs.next()) { 
			String tableName = rs.getString("TABLE_NAME");
			System.out.println(tableName);
			Table table =  resolve.resolveTable(tableName, true); 
			structure.addTable(table);
		}
		Date date1 = new Date();
		System.out.println(date1.getTime() - date.getTime());
		System.out.println((date1.getTime() - date.getTime())/1000);
	}
	
	public static void main(String[] args) {
		try {
			test1();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
