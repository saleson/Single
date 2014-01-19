package com.single.db.structure;

/***********************************************************************
 * Module:  TableRule.java
 * Author:  Saleson
 * Purpose: Defines the Interface TableRule
 ***********************************************************************/

/**
 * 数据库的一些规则.
 * 比如:
 * 表名的最大长度,字段名的最大长度,字段的类型等.
 * @author Administrator
 *
 */
public interface DataBaseRule {
	
	/**
	 * 获取此数据库允许在用户名称中使用的最大字符数。
	 * @return
	 */
	public int getMaxUserNameLength();
	
	/**
	 * 获取此数据库允许在表名称中使用的最大字符数。
	 * @return
	 */
	public int getMaxTableNameLength();
	
	/**
	 * 字段名称长度限制
	 * @return
	 */
	public int getMaxColumnNameLength();
	
	/**
	 * 键的名称长度限制
	 * @return
	 */
	public int getKeyNameLength();
	
	/**
	 * 索引的名称长度限制
	 * @return
	 */
	public int getIndexNameLength();
	
	
	/**
	 * 以数字罗列的字段类型,
	 * @return
	 */
	public int[] getColumnTypes();
	
	/**
	 * 以字符串罗列的字段类型
	 * @return
	 */
	public String[] getColumnTypeStrings();
	
	/**
	 * 指定字符串字段类型,返回数字字段类型(java.sql.Types).
	 * @param typeString
	 * @return
	 */
	public int getColumnType(String typeString);
	
	/**
	 * 指定字段类型的数字描述,返回该字段类型的字符串描述.
	 * @param type
	 * @return
	 */
	public String getColumnTypeString(int type);
	
	/**
	 * 以java类型罗列的字段类型
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Class[] getColumnTypes2JavaTypes();
	
	/**
	 * 指定字段类型的字符串描述,返回该字段类型的java类型.
	 * @param typeString
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Class getColumnType2JavaType(String typeString);
	
	/**
	 * 指定字段类型的数字描述,返回该字段类型的java类型.
	 * @param typeString
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Class getColumnType2JavaType(int typeString);
	
}