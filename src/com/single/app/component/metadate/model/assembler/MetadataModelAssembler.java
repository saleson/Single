package com.single.app.component.metadate.model.assembler;

import java.util.Set;
import com.single.app.component.metadate.MetadataModule;


public class MetadataModelAssembler extends AbstractModelAssembler <MetadataModule>{
	private static MetadataModelAssembler assembler = new MetadataModelAssembler();
	
	public static MetadataModelAssembler getInstance(){
		return assembler;
	}
	
	@Override
	public MetadataModule buildUp(String metadataName) {
		MetadataModule metadata = null;
		Set<MetadataModule> set = mapping.getMetadataModuleGroup(metadataName);
		if(set==null || set.size()==0){
			logger.error("没有找到任何跟字段结构名称['" + metadataName + "']有关的字段结构(MetadataModule)!");
		}else{
			metadata = set.iterator().next();
			mapping.putMetadataModule(metadataName, metadata);
		}
		return metadata;
	}

}
