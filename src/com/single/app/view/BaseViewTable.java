package com.single.app.view;


import com.single.app.component.metadate.Field;
import com.single.app.component.metadate.MetadataModule;

public abstract class BaseViewTable implements ViewTable {

	protected MetadataModule metadata;
	protected View view;
	protected int type;
	
	public BaseViewTable(View view, MetadataModule metadata, int tableType){
		this.view = view;
		this.metadata = metadata;
		this.type = tableType;
	}
	
	public String getMetadataModuleName(){
		return metadata==null ? null : metadata.getName();
	}
	
	public MetadataModule getMetadataModule(){
		return metadata;
	}
	
	/**
	 * 返回视图
	 * @return
	 */
	public View getView(){
		return view;
	}
	
	/**
	 * 返回主键字段.
	 * @return
	 */
	public Field getPrimaryField(){
		return metadata==null ? null : metadata.getPrimaryField();
	}
	
	/**
	 * 返回表的类型.
	 * @return
	 */
	public int getTableType(){
		return type;
	}
	
}
