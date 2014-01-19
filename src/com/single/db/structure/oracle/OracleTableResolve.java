package com.single.db.structure.oracle;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import com.single.commons.util.StringUtils;
import com.single.db.structure.*;

public class OracleTableResolve implements TableResolve {

	private static Logger logger = Logger.getLogger(OracleTableResolve.class.getName());
	private DBStructureContext context;
	private Connection conn;
	private DatabaseMetaData dbMeta;
	private ResultSet rs;
	private PreparedStatement statement;
	private String onwer;
	private List<Map<String, String>> foreignMapList;
	
	/**
	 * 查询表的外键的SQL语句
	 */
	private final String foreignKeySQL = "SELECT A.TABLE_NAME FKTABLE_NAME , A.CONSTRAINT_NAME FK_NAME, B.COLUMN_NAME FKCOLUMN_NAME, C.TABLE_NAME PKTABLE_NAME, A.R_CONSTRAINT_NAME PK_NAME,C.COLUMN_NAME PKCOLUMN_NAME "
								  +"FROM USER_CONSTRAINTS A, USER_CONS_COLUMNS B, USER_CONS_COLUMNS C "
								  +"WHERE A.CONSTRAINT_TYPE = 'R' "
								  +"AND A.CONSTRAINT_NAME = B.CONSTRAINT_NAME "
								  +"AND A.R_CONSTRAINT_NAME = C.CONSTRAINT_NAME ";
	
	/**
	 * 查询表和字段的描述的SQL语句 
	 */
	private final String commentsSQL = "SELECT a.table_name, a.comments table_comments, b.column_name, b.COMMENTS column_comments FROM user_tab_comments a JOIN user_col_comments b ON a.TABLE_NAME=b.TABLE_NAME " 
								+"WHERE a.TABLE_TYPE='TABLE' ? ORDER BY a.TABLE_NAME";
	
	/**
	 * 
	 * @param context	
	 * @param scanUserConstraints	是否扫描数据库用户空间中的外键约束.
	 * @throws SQLException 
	 */
	public OracleTableResolve(DBStructureContext context, boolean scanUserConstraints) throws SQLException{
		this.context = context;
		conn = context.getDBStructure().getConnection();
		dbMeta = conn.getMetaData();
		onwer = dbMeta.getUserName();
		if(scanUserConstraints){
			scanForeign();
		}
	}
	
	/**
	 * 查找表的外键
	 * @throws SQLException
	 */
	private void scanForeign() throws SQLException{
		foreignMapList = new ArrayList<Map<String,String>>();
		PreparedStatement statement = conn.prepareStatement(foreignKeySQL);
		rs = statement.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int cc = rsmd.getColumnCount();
		String[] columnNames = new String[cc];
		for(int i=0;i<cc;i++)
			columnNames[i] = rsmd.getColumnName(i+1);
		while(rs.next()){
			foreignMapList.add(takeData(rs, columnNames));
		}
		this.statement = statement;
		closeResultSetAndStatement();
	}
	
	/**
	 * 取结果集目前定位到的行中的数据,通过指定的字段名.
	 * @param rs	从数据库中查询到的数据结果集
	 * @param columnNames	要取数据的字段
	 * @return	map
	 * @throws SQLException
	 */
	private Map<String, String> takeData(ResultSet rs, String[] columnNames) throws SQLException{
		Map<String, String> map = new HashMap<String, String>();
		for(String cn: columnNames){
			map.put(cn, rs.getString(cn));
		}
		return map;
	}
	
	/**
	 * 从整个数据库中的外键信息集合当中查找指定的表的外键信息,并通过Map<String, String>[]数组形式返回.
	 * @param tableName 要查找外键的表
	 * @return	map数组 
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String>[] getForeignMap(String tableName){
		if(StringUtils.isNotEmpty(tableName) && foreignMapList!=null){
			List<Map<String, String>> l = new ArrayList<Map<String,String>>();
			for(Map<String, String> map : foreignMapList){
				if(tableName.equals(map.get("FKTABLE_NAME"))){
					l.add(map);
				}
			}
			return l.size()==0 ? null : l.toArray(new Map[l.size()]);
		}
		return null;
	}
	
	@Override
	public DBStructureContext getDBStructureContext() {
		return context;
	}

	@Override
	public Table resolveTable(String tableName) {
		return resolveTable(tableName, false);
	}

	/**
	 * 解析表的字段
	 * @param table
	 */
	@Override
	public boolean resolveTableColumn(Table table) {
		try {
			rs = dbMeta.getColumns(null, onwer, table.getName().toUpperCase(), "%");
			while (rs.next()) { 
				Column column = table.addColumn(rs.getString("COLUMN_NAME"));
				column.setType(rs.getInt("DATA_TYPE"));
				column.setColumnSize(rs.getInt("COLUMN_SIZE"));
				column.setDecimalDigits(rs.getInt("DECIMAL_DIGITS"));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return false;
		}finally{
			colseResultSet();
		}
		return true;
	}
	
	/**
//	 * 解析表的字段,主键,外键
	 * @param tableName
	 * @param useMap
	 * @return
	 */
	public Table resolveTable(String tableName, boolean useMap) {
		OracleTable table = null;
		try {
			rs = dbMeta.getTables(null, onwer, (tableName = tableName.toUpperCase()), new String[]{"TABLE"});
			if(rs.next()){
				table = new OracleTable(onwer, tableName);
				colseResultSet();
				resolveTableColumn(table);
				resolveTablePrimaryKey(table);
				refreshTableComments(table);
				if(useMap)
					resolveTableForeignKey(table, useMap);
				else
					resolveTableForeignKey(table);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		colseResultSet();
		return table;
	}

	
	/**
	 * 解析表的外键
	 * @param table
	 */
	public boolean resolveTableForeignKey(Table table) {
		String sql = foreignKeySQL +"AND A.TABLE_NAME = ?";
		PreparedStatement statement = null;
		try {
			//conn = context.getDBStructure().getConnection();
			statement = conn.prepareStatement(sql);
			statement.setString(1, table.getName());
			rs = statement.executeQuery();
			//statement = conn.createStatement();
			//statement.executeQuery(StringUtils.combine(sql, '?', "'"+table.getName()+"'"));
			//statement.closeOnCompletion()
			while(rs.next()){
				String fkName = rs.getString("FK_NAME");
				String columnName = rs.getString("FKCOLUMN_NAME");
				String pkTableName = rs.getString("PKTABLE_NAME");
				createForeignKey(table, fkName, columnName, pkTableName);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		/*try {
			if(statement!=null){
				statement.clearParameters();
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		this.statement = statement;
		closeResultSetAndStatement();
		return true;
	}
	
	/**
	 * 为表创建外键
	 * @param table
	 * @param fkName
	 * @param columnName
	 * @param pkTableName
	 * @return
	 */
	public ForeignKey createForeignKey(Table table, String fkName, String columnName, String pkTableName){
		ForeignKey key = null;
		Table pkTable = context.getDBStructure().findTable(pkTableName);
		if(pkTable==null)
			table.createForeignKey(fkName, new String[]{columnName}, pkTableName);
		else{
			Column column = table.getColumn(columnName);
			if(column!=null)
				table.createForeignKey(fkName, new Column[]{column}, pkTable);
			else
				throw new NullPointerException("创建外键失败:[" + fkName + "], 字段" + columnName + "在表中不存在!");
		}
		return key;
	}
	
	/**
	 * 分析表的外键
	 * @param table
	 * @param useMap
	 * @return
	 */
	public boolean resolveTableForeignKey(Table table, boolean useMap) {
		Map<String, String>[] maps;
		if(useMap && (maps = getForeignMap(table.getName()))!=null){
			for(Map<String, String> m : maps){
				String fkName = m.get("FK_NAME");
				String columnName = m.get("FKCOLUMN_NAME");
				String pkTableName = m.get("PKTABLE_NAME");
				createForeignKey(table, fkName, columnName, pkTableName);
			}
		}
		return resolveTableForeignKey(table);
	}

	/**
	 * 关闭数据集
	 */
	private void colseResultSet(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭语句
	 */
	private void closeStatement(){
		if(statement!=null){
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭数据集和语句
	 */
	private void closeResultSetAndStatement(){
		colseResultSet();
		closeStatement();
	}
	
	/**
	 * 解析表的主键
	 * @param table
	 * @return
	 */
	private boolean resolveTablePrimaryKey(Table table) {
		try{
			rs = dbMeta.getPrimaryKeys(null, onwer, table.getName());
			List<Column> columns = new ArrayList<Column>();
			String primaryKeyName = null;
			while (rs.next()) { 
				primaryKeyName = rs.getString("PK_NAME");
				String columnName = rs.getString("COLUMN_NAME");
				Column column = table.getColumn(columnName);
				if(column==null){
					String message = "字段?在表?中没有找到!";
					throw new IllegalArgumentException(StringUtils.combine(message, '?', columnName, table.getName()));
				}
				columns.add(column);
			}
			if(StringUtils.isNotEmpty(primaryKeyName)){
				table.createPrimaryKey(primaryKeyName, columns.toArray(new Column[columns.size()]));
			}
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return false;
		}
		colseResultSet();
		return true;
	}
	
	/**
	 * 重新提取表的描述和字段的描述.
	 * @param table
	 */
	public void refreshTableComments(Table table){
		String sql = StringUtils.combine(commentsSQL, '?', " AND a.table_name=?");
		//PreparedStatement statement = null;
		//ResultSet rs = null;
		
		try {
			statement = conn.prepareStatement(sql);
			statement.setString(1, table.getName());
			rs = statement.executeQuery();
			String tableComments = "";
			while(rs.next()){
				tableComments = rs.getString("table_comments");
				String columnName = rs.getString("column_name");
				Column column = table.getColumn(columnName);
				column.setComments(rs.getString("column_comments"));
			}
			table.setComment(tableComments);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		finally{
			closeResultSetAndStatement();
		}
		
	}
	

	/**
	 * 关闭解析器
	 */
	@Override
	public void close() throws Exception {
		if(foreignMapList!=null){
			foreignMapList.clear();
		}
		if(conn!=null){
			closeResultSetAndStatement();
			conn.close();
		}
	}
}
