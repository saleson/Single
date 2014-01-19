package com.single.db.structure.test;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.PropertyConfigurator;

import com.single.db.structure.Column;
import com.single.db.structure.DBStructure;
import com.single.db.structure.ForeignKey;
import com.single.db.structure.PrimaryKey;
import com.single.db.structure.SingleDBStructure;
import com.single.db.structure.Table;
import com.single.db.structure.TableGenerator;
import com.single.db.structure.TableResolve;
import com.single.db.structure.oracle.OracleColumn;
import com.single.db.structure.oracle.OracleDBStructureContext;
import com.single.db.structure.oracle.OracleTable;
import com.single.db.structure.oracle.OracleTableGenerator;
import com.single.db.structure.oracle.OracleTableResolve;

public class TableGeneratorTest {
	
	
	private static void test1() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(new File("").getAbsolutePath());
		PropertyConfigurator.configure("src.resources/log4j.properties");
		DBStructure structure = new SingleDBStructure();
		structure.setOnwer("simple");
		OracleDBStructureContext context = new OracleDBStructureContext(structure);
		TableResolve resolve = new OracleTableResolve(context, false);
		Table table2 = resolve.resolveTable("A");
		//Table table1 = resolve.resolveTable("test_generate");
		OracleTable table = new OracleTable("simple", "test_generate");
		table.setComment("创建的测试表");
		OracleColumn column = (OracleColumn)table.addColumn("test1");
		column.setType(3);
		column.setColumnSize(16);
		column.setDecimalDigits(9);
		column.setComments("这是一个测试字段");
		PrimaryKey primaryKey = table.createPrimaryKey("PK_TEST_GENERATE_PRIMARYKEY", column);
		for(int i=2;i<6;i++){
			String columnName = "test_"+i;
			column = (OracleColumn)table.addColumn(columnName);
			column.setType(3);
			column.setComments("这是一个测试字段"+i);
		}
		ForeignKey f = table.createForeignKey("FK_TEST_GENERATE_A_D", new Column[]{table.getColumn("TEST_3"), table.getColumn("TEST_4")}, table2);
		//OracleDBStructureContext context = OracleDBStructureContext.getDBStructureContext(owner);
		Connection conn = context.getDBStructure().getConnection();
		OracleTableGenerator generator = new OracleTableGenerator(conn);
		System.out.println(generator.generateTableSQL(table));
		generator.generateTable(table);
		generator.dropTable(table.getName());
		generator.close();
		conn.close();
	}
	
	
	private static void test2() throws SQLException {
		PropertyConfigurator.configure("src.resources/log4j.properties");
		DBStructure structure = new SingleDBStructure();
		structure.setOnwer("simple");
		OracleDBStructureContext context = new OracleDBStructureContext(structure);
		TableResolve resolve = new OracleTableResolve(context, false);
		Table table = resolve.resolveTable("emp");
		Connection conn = context.getDBStructure().getConnection();
		OracleTableGenerator generator = new OracleTableGenerator(conn);
		//generator.modifyColumnType(table.getColumn("xm"), "varchar2(64)", TableGenerator.OPERATE_MODIFYCOLUMNTYPE_CONVERSION_DATA, "to_char(?,'yyyy-mm-dd hh24:mi:ss')");
		generator.generateColumn(table.getColumn("xm"));
	}
	
	
	public static void main(String[] args) throws SQLException {
		test2();
	}
}
