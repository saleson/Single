package com.single.app.view;

import com.single.app.component.metadate.Field;
import com.single.app.component.metadate.MetadataModule;
import com.single.app.view.handler.ViewBuildHandler;

public class MasterTable extends BaseViewTable{

	private Field coderuleField;
	
	public MasterTable(View view) {
		this(view, null);
	}
	
	public MasterTable(View view, MetadataModule metadata) {
		super(view, metadata, ViewTable.MASTERTABLE);
		initCoderuleField();
	}
	
	public void setCoderuleField(Field field){
		this.coderuleField = field;
	}
	
	public Field getCoderuleField(){
		return coderuleField;
	}
	
	public void setMetadataModule(MetadataModule metadataModule){
		this.metadata = metadataModule;
		initCoderuleField();
	}
	
	private void initCoderuleField(){
		if(this.metadata!=null){
			coderuleField = ViewBuildHandler.findCoderuleField(metadata.getDisplayFields());
		}
	}
	
}
