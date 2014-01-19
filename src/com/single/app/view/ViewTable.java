package com.single.app.view;


import com.single.app.component.metadate.Field;
import com.single.app.component.metadate.MetadataModule;

public interface ViewTable {

	/**
	 * 表的类型:主表
	 */
	public final static int MASTERTABLE = 1; 
	
	/**
	 * 表的类型:从表
	 */
	public final static int SLAVETABLE = 2;
	
	/**
	 * 表的类型:列表
	 */
	public final static int LISTTABLE = 9;
	
	
	public String getMetadataModuleName();
	
	public MetadataModule getMetadataModule();
	
	/**
	 * 返回视图
	 * @return
	 */
	public View getView();
	
	/**
	 * 返回主键字段.
	 * @return
	 */
	public Field getPrimaryField();
	
	/**
	 * 返回表的类型.
	 * @return
	 */
	public int getTableType();
}
