package com.single.db.structure;

/***********************************************************************
 * Module:  TableGenerator.java
 * Author:  Administrator
 * Purpose: Defines the Interface TableGenerator
 ***********************************************************************/

/** @pdOid 34686cd4-c57e-48f7-9fd1-ee77233129ac */
public interface TableGenerator {
	
	/**
	 * 改变字段类型时的操作指令:什么也不做,用于没有数据的字段.
	 */
	public final static int OPERATE_MODIFYCOLUMNTYPE_NOTHING = 0;
	
	/**
	 * 改变字段类型时的操作指令:清空字段的数据.使用需谨慎.
	 */
	public final static int OPERATE_MODIFYCOLUMNTYPE_EMPTY_DATA = 2;
	
	/**
	 * 改变字段类型时的操作指令:改字段的数据类型.
	 */
	public final static int OPERATE_MODIFYCOLUMNTYPE_CONVERSION_DATA = 4;
	
	/**
	 * 创建表
	 * @param table
	 * @return
	 */
	public boolean generateTable(Table table);
	
	/**
	 * 删除表
	 * @param tableName
	 * @return
	 */
	public boolean dropTable(String tableName);
	
	/**
	 * 改变表的描述信息
	 * @param table
	 * @param comments
	 * @return
	 */
	public boolean alterTableComment(String table, String comments);
	
	/**
	 * 改变表的描述信息
	 * @param table
	 * @return
	 */
	public boolean alterTableComment(Table table);
	
	
	
	/**
	 * 创建字段
	 * @param column
	 * @return
	 */
	public boolean generateColumn(Column column);
	
	/**
	 * 改变字段的描述信息
	 * @param table
	 * @param columnName
	 * @param comments
	 * @return
	 */
	public boolean alterColumnComment(String table, String columnName, String comments);
	
	/**
	 * 改变字段的描述信息
	 * @param column
	 * @return
	 */
	public boolean alterColumnComment(Column column);
	
	/**
	 * 删除字段
	 * @param column
	 * @return
	 */
	public boolean dropColumn(Column column);
	
	/**
	 * 改变字段的类型.
	 * 默认的改变字段类型时的操作指令是什么也不做,用于没有数据的字段.
	 * @param column
	 * @param newTypeString
	 * @return
	 */
	public boolean modifyColumnType(Column column, String newTypeString);
	
	/**
	 * 改变字段的类型,需设置指令.如果指令是{@TableGenerator.OPERATE_MODIFYCOLUMNTYPE_CONVERSION_DATA} 
	 * 或 {@TableGenerator.OPERATE_MODIFYCOLUMNTYPE_EMPTY_DATA}的话,连接器的提交动作得设置为true<code>@java.sql.Connection.setAutoCommit(true)</code>;
	 * 
	 * @param column
	 * @param newTypeString
	 * @param operate 改变字面类型时,对字段中已有的数据做出的处理指令.
	 * @param conversionFunction 转换函数,当operate为 {@TableGenerator.OPERATE_MODIFYCOLUMNTYPE_CONVERSION_DATA}时用到;
	 * 							   比如日期转变字段:to_char(?,'yyyy-mm-dd hh24:mi:ss');
	 * @return
	 */
	public boolean modifyColumnType(Column column, String newTypeString, int operate, String conversionFunction);
	
	/**
	 * 创建键,例如创建主键,外键,唯一键.
	 * @param key
	 * @return
	 */
	public boolean generateConstraintKey(Key key);
	
	/**
	 * 删除约束键
	 * @param key
	 * @return
	 */
	public boolean dropConstraintKey(Key key);
	
	/**
	 * 返回创建表的ddl语句
	 * @param table
	 * @return
	 */
	public String generateTableSQL(Table table);
	
	/**
	 * 返回创建字段的ddl语句
	 * @param column
	 * @return
	 */
	public String generateColumnSQL(Column column);
	
	/**
	 * 返回创建键的ddl语句
	 * @param key
	 * @return
	 */
	public String generateConstraintKeySQL(Key key);
	
	
	public void close();
}