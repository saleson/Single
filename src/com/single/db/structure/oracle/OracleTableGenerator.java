package com.single.db.structure.oracle;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.single.db.structure.*;
import com.single.commons.util.StringUtils;

public class OracleTableGenerator implements TableGenerator {

	private Statement statement;
	private final String segmentedOperators = ";";
	static Logger logger = Logger.getLogger(OracleTableGenerator.class.getName());

	public OracleTableGenerator(Connection conn){
		try {
			statement = conn.createStatement();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Override
	public boolean generateTable(Table table) {
		String sql = generateTableSQL(table);
		String[] ddls = sql.split(segmentedOperators);
		for(String ddl : ddls){
			executeOracleDDLStatement(ddl);
		}
		return true;
	}

	@Override
	public boolean generateColumn(Column column) {
		String sql = generateColumnSQL(column);
		String[] ddls = sql.split(segmentedOperators);
		boolean b = executeOracleDDLStatement(ddls[0]);
		if(!b){
			logger.info("创建字段失败:" + column.getTable().getName() + "." + column.getColumnName());
		}else if(ddls.length==2){
			executeOracleDDLStatement(ddls[1]);
		}
		return b;
	}
	
	@Override
	public boolean generateConstraintKey(Key key) {
		return executeOracleDDLStatement(generateConstraintKeySQL(key).replaceAll(segmentedOperators+"$", ""));
	}

	@Override
	public String generateTableSQL(Table table) {
		StringBuffer sb = new StringBuffer();
		Column[] columns = table.getColumns();
		String[] columnDDLS = new String[columns.length];
		List<String> columnCommentList = new ArrayList<String>();
		for(int i=0;i<columns.length;i++){
			Column column = columns[i];
			columnDDLS[i] = column.getColumnName() + " " + column.getTypeString();
			String columnComments = generateColumnCommentsSQL(column);
			if(StringUtils.isNotEmpty(columnComments)){
				columnCommentList.add(columnComments);
			}
		}
		sb.append("CREATE TABLE ").append(table.getName()).append("(").append(StringUtils.join(columnDDLS, ",")).append(")")
		.append(" PCTFREE 10 INITRANS 1 MAXTRANS 255 STORAGE ( INITIAL 64K NEXT 1M MINEXTENTS 1 )").append(segmentedOperators);
		String comments = table.getComment();
		if(StringUtils.isNotEmpty(comments)){
			sb.append(tableCommentDDL(table.getName(), comments)).append(segmentedOperators);
		}
		sb.append(columnCommentList.size()!=0 ? StringUtils.join(columnCommentList.iterator(), segmentedOperators) + segmentedOperators : "");
		if(table.getPrimaryKey()!=null){
			sb.append(generatePrimaryKeySQL(table.getPrimaryKey()));
		}
		for(ForeignKey foreignKey : table.getForeignKeys()){
			sb.append(generateForeignKeySQL(foreignKey));
		}
		return sb.toString();
	}

	@Override
	public String generateColumnSQL(Column column) {
		Table table = column.getTable();
		StringBuffer sb = new StringBuffer();
		sb.append("ALTER TABLE ").append(table.getName()).append(" ADD ").append(column.getColumnName()).append(" ").append(column.getTypeString());
		String defaultValue = column.getDefault();
		if(StringUtils.isNotEmpty(defaultValue)){
			defaultValue = StringUtils.replace(defaultValue, "'", "''");
			sb.append(" DEFAULT '").append(defaultValue).append("'");
		}
		sb.append(segmentedOperators);
		String columnComments = generateColumnCommentsSQL(column);
		if(StringUtils.isNotEmpty(columnComments)){
			sb.append(columnComments).append(segmentedOperators);
		}
		return sb.toString();
	}

	/**
	 * 返回创建字段的描述信息的语句.
	 * @param column
	 * @return
	 */
	private String generateColumnCommentsSQL(Column column){
		return generateColumnCommentsSQL(column.getTable().getName(), column.getColumnName(), column.getComments());
	}
	
	/**
	 * 返回创建字段的描述信息的语句.
	 * @param table
	 * @param column
	 * @param comments
	 * @return
	 */
	private String generateColumnCommentsSQL(String table, String column, String comments){
		return StringUtils.isEmpty(comments) ? null : 
			"COMMENT ON COLUMN " + table + "." + column + " IS '" +  StringUtils.replace(comments, "'", "''") + "'";
	}
	
	
	@Override
	public String generateConstraintKeySQL(Key key) {
		return Key.PRIMARY_KEY==key.getType() ? generatePrimaryKeySQL((PrimaryKey)key) : 
				Key.FOREIGN_KEY == key.getType() ? generateForeignKeySQL((ForeignKey)key) : null;
	}
	
	/**
	 * 返回创建主键的DDL语句.
	 * @param key
	 * @return
	 */
	public String generatePrimaryKeySQL(PrimaryKey key){
		StringBuffer sb = new StringBuffer();
		Table table = key.getTable();
		sb.append("ALTER TABLE ").append(table.getName()).append(" ADD CONSTRAINT ").append(key.getName()).append(" PRIMARY KEY ");
		Column[] columns = key.getColumns();
		String[] cs = new String[key.getColumns().length];
		for(int i=0;i<columns.length;i++){
			cs[i] = columns[i].getColumnName();
		}
		sb.append("(").append(StringUtils.join(cs, ",")).append(")").append(segmentedOperators);
		return sb.toString();
	}
	
	/**
	 * 返回创建外键的DDL语句
	 * @param key
	 * @return
	 */
	public String generateForeignKeySQL(ForeignKey key){
		StringBuffer sb = new StringBuffer();
		Table table = key.getTable();
		sb.append("ALTER TABLE ").append(table.getName()).append(" ADD CONSTRAINT ").append(key.getName()).append(" FOREIGN KEY ");
		Column[] columns = key.getColumns();
		String[] cs = new String[key.getColumns().length];
		for(int i=0;i<columns.length;i++){
			cs[i] = columns[i].getColumnName();
		}
		sb.append("(").append(StringUtils.join(cs, ",")).append(")");
		columns = key.getReferencingPrimaryKey().getColumns();
		cs = new String[key.getColumns().length];
		for(int i=0;i<columns.length;i++){
			cs[i] = columns[i].getColumnName();
		}
		sb.append(" REFERENCES ").append(key.getReferencingTableName()).append(" (").append(StringUtils.join(cs, ",")).append(")").append(segmentedOperators);
		return sb.toString();
	}

	/**
	 * 执行ddl语句
	 * @param sql
	 * @return
	 */
	private boolean executeOracleDDLStatement(String sql){
		try {
			logger.debug(sql+segmentedOperators);
			statement.execute(sql);
		} catch (SQLException e) {
			logger.warn("Execute ddl statement failed!");
			logger.error(e.getMessage(), e);
			//e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void close() {
		try {
			statement.close();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public boolean dropTable(String tableName) {
		String ddl = "DROP TABLE " + tableName;
		return executeOracleDDLStatement(ddl);
	}

	@Override
	public boolean alterTableComment(String table, String comments) {
		return executeOracleDDLStatement(tableCommentDDL(table, comments));
	}
	
	/**
	 * 返回改变字段的描述信息的DDL语句.
	 * @param table
	 * @param comments
	 * @return
	 */
	private String tableCommentDDL(String table, String comments){
		return "COMMENT ON TABLE "+table+" IS '" + StringUtils.replaceChars(comments==null ? "" : comments, "'", "''") + "'";
	}

	@Override
	public boolean dropColumn(Column column) {
		return executeOracleDDLStatement(dropColumnSQL(column));
	}
	
	public String dropColumnSQL(Column column){
		return "ALTER TABLE " + column.getTable().getName() + " DROP COLUMN " + column.getColumnName();
	}

	@Override
	public boolean modifyColumnType(Column column, String newTypeString) {
		return modifyColumnType(column, newTypeString, OPERATE_MODIFYCOLUMNTYPE_NOTHING, null);
	}

	@Override
	public boolean modifyColumnType(Column column, String newTypeString, int operate, String conversionFunction) {
		//List<String> ddls = new ArrayList<String>();
		String tableName = column.getTable().getName(), columnName = column.getColumnName();
		if(operate==OPERATE_MODIFYCOLUMNTYPE_EMPTY_DATA){
			executeOracleDDLStatement("update " + tableName + " set " + columnName + "=''");
		}else if(operate==OPERATE_MODIFYCOLUMNTYPE_CONVERSION_DATA){
			List<String> backDDLS = new ArrayList<String>();
			String _cn = "temp_" + columnName + String.valueOf(Math.random()*100).split("\\.")[0];
			Column c = column.getTable().addColumn(_cn);
			c.setType(column.getType());
			c.setColumnSize(column.getColumnSize());
			c.setDecimalDigits(column.getDecimalDigits());
			if(generateColumn(c)){
				//backDDLS.add(dropColumnSQL(c));
				executeOracleDDLStatement("update " + tableName + " set " + _cn + "="+ columnName);
				boolean d = executeOracleDDLStatement("update " + tableName + " set " + columnName + "=''");
				if(d && executeOracleDDLStatement("alter table " + tableName + " modify " + column.getColumnName() + " " + newTypeString)){
					backDDLS.add("alter table " + tableName + " modify " + column.getColumnName() + " " + column.getTypeString());
					backDDLS.add("update " + tableName + " set " + columnName + "="+ _cn);
					String f = StringUtils.isEmpty(conversionFunction) ? _cn : StringUtils.replaceOnce(conversionFunction, "?", _cn);
					d = executeOracleDDLStatement("update " + tableName + " set " + columnName + "=" + f);
					if(!d){
						for(String sql : backDDLS)
							executeOracleDDLStatement(sql);
					}
				}else{
					executeOracleDDLStatement("update " + tableName + " set " + columnName + "="+ _cn);
				}
				dropColumn(c);
				return d;
			}
			return false;
		}
		return executeOracleDDLStatement("alter table " + tableName + " modify " + column.getColumnName() + " " + newTypeString);
	}
	
	@Override
	public boolean dropConstraintKey(Key key) {
		String ddl = "ALTER TABLE "+key.getTable().getName()+" DROP CONSTRAINT " + key.getName() 
					+ (Key.FOREIGN_KEY==key.getType() ? "" : " CASCADE");
		return executeOracleDDLStatement(ddl);
	}

	@Override
	public boolean alterTableComment(Table table) {
		return alterTableComment(table.getName(), table.getComment());
	}

	@Override
	public boolean alterColumnComment(String table, String columnName, String comments) {
		return executeOracleDDLStatement(generateColumnCommentsSQL(table, columnName, comments));
	}

	@Override
	public boolean alterColumnComment(Column column) {
		return alterColumnComment(column.getTable().getName(), column.getColumnName(), column.getComments());
	}
	
}
