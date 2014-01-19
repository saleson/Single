package com.single.db.structure;

import java.io.Serializable;

/***********************************************************************
 * Module:  Column.java
 * Author:  Saleson
 * Purpose: Defines the Interface Column
 ***********************************************************************/

/**
 * 数据库表中的字段.
 * 有字段的名称,字段的类型,字段备注,字段的大小等...
 * @author Saleson
 *
 */
public interface Column extends Serializable{
	
	/**
	 * 字段字段所在的表.
	 * @return
	 */
	public Table getTable();
	
	/**
	 * 返回字段的名称.
	 * @return
	 */
	public String getColumnName();
	
	/**
	 * 返回字段的类型.来自 java.sql.Types 的 SQL 类型
	 * @return	来自 java.sql.Types 的 SQL 类型
	 */
	public int getType();
	
	/**
	 * 设置字段的类型.来自 java.sql.Types 的 SQL 类型
	 * @param type	来自 java.sql.Types 的 SQL 类型
	 */
	public void setType(int type);
	
	/**
	 * 返回字段的大小.
	 * @return
	 */
	public int getColumnSize();
	
	/**
	 * 设置字段的大小.
	 * @param size
	 */
	public void setColumnSize(int size);
	
	/**
	 * 字段小数部分的位数.对于 Decimal Digits 不适用的数据类型，则返回 0.
	 * @return
	 */
	public int getDecimalDigits();
	
	/**
	 * 设置字段小数部分的位数.
	 * @param decimalDigits
	 */
	public void setDecimalDigits(int decimalDigits);
	
	/**
	 * 设置字段的大小.
	 * @param size	字段的大小
	 * @param decimalDigits	字段小数部分的位数
	 */
	public void setColumnSize(int size, int decimalDigits);
	
	/**
	 * 返回以字符串描述的字段类型.
	 * 可以是一类型,如:number,varchar,varchar2,date.
	 * 也可以是一个带大小的类型,如:number(9),number(9,2),varchar2(3),date.
	 * @return
	 */
	public String getTypeString();
	
	/**
	 * 用字符串设置字段的类型.
	 * 可以是一类型,如:number,varchar,varchar2,date.
	 * 也可以是一个带大小的类型,如:number(9),number(9,2),varchar2(3),date.
	 * 方法体自动分析字符串的内容,然后设置字段的类型,字段大小,小数点位数.
	 * @param typeString
	 */
	public void setTypeString(String typeString);
	
	/**
	 * 设置字段的默认值.
	 * @param def
	 */
	public void setDefault(String def);
	
	/**
	 * 返回字段的默认值.
	 * @return
	 */
	public String getDefault();
	
	/**
	 * 设置字段的描述内容(备注).
	 * @param comments
	 */
	public void setComments(String comments);
	
	/**
	 * 返回字段的描述内容(备注).
	 * @return
	 */
	public String getComments();
}