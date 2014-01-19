package com.single.app.component.metadate.parse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.single.app.component.metadate.ModelFramework;
import com.single.app.component.metadate.mapping.StructureMapping;
import com.single.app.component.metadate.model.assembler.ModelAssembler;
import com.single.app.component.metadate.model.assembler.ModelAssemblerFactory;

public class ModelFrameworkPersistenceListener extends AbstractParsingListener {
	
	private Map<String, ModelAssembler<?>> modelAssembleMap = new HashMap<String, ModelAssembler<?>>(); 
	
	@Override
	public void beforeParse(ParsingProcessContext context) {
	}

	
	
	@Override
	public void invoke(List<ModelFramework> mfList) {
		StructureMapping.getInstance().put(mfList);
		for(ModelFramework mf : mfList){
			if(mf.needAssembly())
				modelAssembleMap.put(mf.getName(), ModelAssemblerFactory.getModelAssembler(mf.getClass().getName()));
		}
	}

	@Override
	public void afterParsed() {
		Iterator<Entry<String, ModelAssembler<?>>> iter = modelAssembleMap.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, ModelAssembler<?>> entry = iter.next();
			entry.getValue().buildUp(entry.getKey());
		}
	}
}
