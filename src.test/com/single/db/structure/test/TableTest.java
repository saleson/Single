package com.single.db.structure.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;

import org.slf4j.profiler.StopWatch;

public class TableTest {

	static {
		System.out.println("static TableTest");
	}
	
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		String url = "jdbc:oracle:thin:@localhost:1521:SAGE";
		String user = "simple";
		String password = "simple";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void test1(){
		try {
			Connection conn = getConnection();
			DatabaseMetaData dbMeta = conn.getMetaData();
			Date date = new Date();
			ResultSet rs = null ,rs1 = null;
			
			rs = dbMeta.getTables(null, dbMeta.getUserName(), "KC_SFDJ", new String[]{"TABLE"});
			while (rs.next()) { 
				System.out.println(rs.getString("TABLE_NAME") + ":" + rs.getString("TABLE_CAT")); 
				/*rs1 = dbMeta.getExportedKeys(dbMeta.getUserName(), null, rs.getString("TABLE_NAME"));
				while (rs1.next()) { 
					System.out.println(rs1.getString("PKTABLE_NAME") + ":" + rs1.getString("PKCOLUMN_NAME")
							+","+ rs1.getString("FKTABLE_NAME")+"-->"+rs1.getString("FKCOLUMN_NAME")
							+"   "+rs1.getString("PK_NAME")+","+rs1.getString("FK_NAME")); 
				} 
				rs1.close();*/
			} 
			rs.close(); 
			
			/*System.out.println(dbMeta.getMaxColumnsInTable());
			System.out.println(dbMeta.getMaxIndexLength());
			
			
			System.out.println("-------------------");*/
			
			rs = dbMeta.getColumns(null, dbMeta.getUserName(), "B", "%");
			while (rs.next()) { 
				System.out.println(rs.getString("COLUMN_NAME") + ":" + rs.getInt("COLUMN_SIZE")
						+","+ rs.getInt("DECIMAL_DIGITS")+"-->"+rs.getInt("DATA_TYPE")); 
			} 
			rs.close(); 
			
			/*rs = dbMeta.getImportedKeys(null, dbMeta.getUserName(), "KC_SFDJMX");
			while (rs.next()) { 
				System.out.println(rs.getString("PKTABLE_NAME") + ":" + rs.getString("PKCOLUMN_NAME")
						+","+ rs.getString("FKTABLE_NAME")+"-->"+rs.getString("FKCOLUMN_NAME")
						+"   "+rs.getString("PK_NAME")+","+rs.getString("FK_NAME")); 
			} 
			rs.close();
			
			rs = dbMeta.getPrimaryKeys(null, dbMeta.getUserName(), "A");
			while (rs.next()) { 
				System.out.println(rs.getString("TABLE_NAME") + ":" + rs.getString("COLUMN_NAME")
						+","+ rs.getString("PK_NAME")+"-->"+rs.getString("KEY_SEQ")); 
			} 
			rs.close();*/
			
			Date date1 = new Date();
			System.out.println(date1.getTime() - date.getTime());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void test2(){
		try {
			Connection conn = getConnection();
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM kc_sfdjmx";
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsMeta = rs.getMetaData();
			int i = 12;
			System.out.println(rsMeta.getColumnName(i) +":" + rsMeta.getPrecision(i)
					+"," + rsMeta.getScale(i)+"-->"+rsMeta.getColumnDisplaySize(i));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public class Cs{
		public Cs(){
			System.out.println("Cs");
		}
	}
	
	
	public static void test3(){
		int f = 0,d=1,g=1;
		Date date = new Date();
		for(int i=0;i<1000000000;i++){
			f = g==d?s():2;
		}
		
		Date date1 = new Date();
		System.out.println(date1.getTime() - date.getTime());
		
		/*date = new Date();
		for(int i=0;i<1000000;i++){
			f = g==d?s():2;
			if(g==d){
				f = 3;
			}
		}
		
		date1 = new Date();
		System.out.println(date1.getTime() - date.getTime());*/
	}
	
	public static int s(){
		return 3;
	}
	
	public static void main(String[] args) {
		/*for(int i=0;i<20;i++){
			System.out.println("=======  " + i + "  =======");
			test1();
		}*/
		test1();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("com.single.db.structure.test.TableTest");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
