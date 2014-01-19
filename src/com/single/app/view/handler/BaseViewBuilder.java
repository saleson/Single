package com.single.app.view.handler;

import org.apache.log4j.Logger;

import com.single.app.component.metadate.MetadataModule;
import com.single.app.component.metadate.ViewModule;
import com.single.app.component.metadate.mapping.StructureMapping;
import com.single.app.view.View;

public abstract class BaseViewBuilder<V extends View> implements ViewBuilder<V> {

	protected StructureMapping structureMapping = StructureMapping.getInstance();
	protected Logger logger = Logger.getLogger(BaseViewBuilder.class);
	
	@Override
	public V build(String path) {
		ViewModule viewModule = structureMapping.getViewModule(path);
		if(viewModule==null){
			logger.error("没有找到视图模型['" + path + "'].");
			return null;
		}
		return build(viewModule);
	}


	protected MetadataModule findMetadataModule(String metadataName){
		return structureMapping.getMetadataModule(metadataName);
	}
}
