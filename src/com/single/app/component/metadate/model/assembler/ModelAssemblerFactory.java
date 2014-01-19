package com.single.app.component.metadate.model.assembler;

import java.util.HashMap;
import java.util.Map;

import com.single.app.component.metadate.MetadataModule;
import com.single.app.component.metadate.ViewModule;

/**
 * 模型组装器的工厂类
 * @author Saleson. 
 * Computer by Administrator.
 *
 */
public class ModelAssemblerFactory {
	
	private static Map<String, ModelAssembler<?>> assemblerMap = new HashMap<String, ModelAssembler<?>>();
	static{
		assemblerMap.put(ViewModule.class.getName(), ViewModelAssembler.getInstance());
		assemblerMap.put(MetadataModule.class.getName(), MetadataModelAssembler.getInstance());
	}

	
	public static ModelAssembler<?> getModelAssembler(String moduleName){
		return assemblerMap.get(moduleName);
	}
}
